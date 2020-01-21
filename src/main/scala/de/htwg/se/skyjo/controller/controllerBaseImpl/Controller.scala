package de.htwg.se.skyjo.controller.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.skyjo.SkyjoModule
import de.htwg.se.skyjo.controller.{BoardChanged, CandidatesChanged, ControllerInterface, GameOver, NewRound, Shutdown}
import de.htwg.se.skyjo.model.cardComponent.CardInterface
import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck
import de.htwg.se.skyjo.model.fileIoComponent.FileIOInterface
import de.htwg.se.skyjo.model.gameBoardComponent.GameBoardInterface
import de.htwg.se.skyjo.model.playerComponent
import de.htwg.se.skyjo.model.playerComponent.Player
import de.htwg.se.skyjo.util.{Observer, UndoManager}


class Controller @Inject()(var gameBoard: GameBoardInterface) extends ControllerInterface with Observer {

  var injector = Guice.createInjector(new SkyjoModule)
  var undoManager = new UndoManager
  val fileIo = injector.getInstance(classOf[FileIOInterface])
  var winner = -1
  var trade = false

  def newGame(): Unit = {
    subscribers = Vector()
    injector = Guice.createInjector(new SkyjoModule)
    undoManager = new UndoManager
    winner = -1
    trade = false
    gameBoard = injector.getInstance(classOf[GameBoardInterface])
    add(this)
    gameBoard.deck.shuffle()
    gameBoard.deck.drawCard()
  }

  override def createPlayer(name: String): Unit = {
    gameBoard.players += playerComponent.Player(name, gameBoard.deck)
    gameBoard.players.last.hand.cards = gameBoard.deck.drawHand()
    add(gameBoard.players.last)
    publish(new CandidatesChanged)
  }

  override def undo(): Unit = {
    undoManager.undoStep
    gameBoard.players(gameBoard.turn).stillMyTurn = true
    gameBoard.players(gameBoard.turn).canDrawCard = true
    notifyObservers
    publish(new BoardChanged)
  }

  override def redo(): Unit = {
    undoManager.redoStep
    gameBoard.players(gameBoard.turn).stillMyTurn = true
    gameBoard.players(gameBoard.turn).canDrawCard = true
    notifyObservers
    publish(new BoardChanged)
  }

  override def drawCard(): Unit = {

    if (gameBoard.players(gameBoard.turn).hand.sumUncovered() > 1 && gameBoard.players(gameBoard.turn).canDrawCard == true) {
      undoManager.doStep(new DrawCommand(gameBoard.players(gameBoard.turn)))
      gameBoard.players(gameBoard.turn).stillMyTurn = true
      gameBoard.players(gameBoard.turn).canDrawCard = false
      notifyObservers
      publish(new BoardChanged)
    } else {
      gameBoard.players(gameBoard.turn).stillMyTurn = true
    }
  }

  override def update: Boolean = {
    whichTurn()
    checkEnd()
    true
  }

  def whichTurn(): Unit = {
    if (!gameBoard.players(gameBoard.turn).stillMyTurn) {
      gameBoard.turn += 1
    }
    if (gameBoard.turn == gameBoard.players.length) {
      gameBoard.turn = 0
    }
    publish(new BoardChanged)
  }

  def checkEnd(): Unit = {
    if (checkUncovered()) {
      val doublePoint = lowestPoints
      for (i <- 0 until gameBoard.players.length) {
        if (doublePoint && i == gameBoard.turn) {
          gameBoard.players(i).points += (gameBoard.players(i).hand.summarizeAll() * 2)
        } else {
          gameBoard.players(i).points += gameBoard.players(i).hand.summarizeAll()
        }
        //TODO punkte werden nicht richtig zusammen gezählt
        //TODO erst von allen spielern die punkte zsm zählen dann prüfen ob einer über 100
        //TODO die hand muss vom kartenstapel gezogen werden
        if (gameBoard.players(i).points >= 100) {
          return endGame
        }
      }
      publish(new NewRound)
    }
  }

  override def boardToString(): String = {
    val sb = new StringBuilder()
    if (gameBoard.deck.discardPile.nonEmpty) {
      sb.append("Ablagestapel: " + gameBoard.deck.discardPileTopCard() + "\n")
    }
    sb.append("Karten im Deck: " + gameBoard.deck.sumCardInDeck() + "\n")
    for (i <- 0 until gameBoard.players.length) {
      sb.append(gameBoard.players(i).playerString)
    }
    sb.append("-------------------------------------------------\n")
    sb.toString()
  }

  def checkUncovered(): Boolean = {
    if (gameBoard.players(gameBoard.turn).hand.sumUncovered() == 12) {
      true
    } else {
      false
    }
  }

  def lowestPoints(): Boolean = {
    val lowest = gameBoard.players(gameBoard.turn).hand.summarizeAll()
    var bool = false
    if (lowest > 0) {
      for (i <- 0 until gameBoard.players.length) {
        gameBoard.players(i).hand.uncoverAll()
        if (!(i == gameBoard.turn)) {
          if (gameBoard.players(i).hand.summarizeAll() <= lowest) {
            bool = true
          }
        }
      }

    }
    bool
  }

  def endGame(): Unit = {
    var winnerPoints = 100
    for (i <- 0 until gameBoard.players.length) {
      if (gameBoard.players(i).points < winnerPoints) {
        winnerPoints = gameBoard.players(i).points
        winner = i
      }
    }
    publish(new GameOver)
  }

  override def doMove(posY: Int, posX: Int, player: Int): Unit = {
    if (player == gameBoard.turn) {
      if (trade) {
        if (gameBoard.players(gameBoard.turn).hand.sumUncovered() > 1) {
          tradeCard(gameBoard.players(gameBoard.turn), posY, posX)
          trade = false
        } else {
          gameBoard.players(gameBoard.turn).stillMyTurn = true
          trade = false
        }
      } else {
        uncoverCard(gameBoard.players(gameBoard.turn), posY, posX)
      }
    }
  }

  override def uncoverCard(player: Player, posY: Int, posX: Int): Unit = {
    undoManager.doStep(new UncoverCommand(player, posY, posX))
    player.stillMyTurn = false
    player.canDrawCard = true
    publish(new BoardChanged)
    notifyObservers
  }

  override def tradeCard(player: Player, posY: Int, posX: Int): Unit = {
    undoManager.doStep(new TradeCommand(player, posY, posX))
    player.stillMyTurn = false
    player.canDrawCard = true
    publish(new BoardChanged)
    notifyObservers
  }

  override def newRound: Unit = {
    gameBoard.deck = injector.getInstance(classOf[Deck])
    gameBoard.deck.shuffle()
    gameBoard.deck.drawCard()
    gameBoard.turn = 0

    for (i <- 0 until gameBoard.players.length) {
      gameBoard.players(i).hand.cards = gameBoard.deck.drawHand()
    }
  }

  override def uncoverAll(): Unit = {
    gameBoard.players(gameBoard.turn).hand.uncoverAll()
    publish(new BoardChanged)
    notifyObservers
  }

  override def getPlayerTurnString: String = {
    gameBoard.players(gameBoard.turn).name
  }

  override def getWinnerString: String = {
    gameBoard.players(winner).name
  }

  override def getTurn: Int = {
    gameBoard.turn
  }

  override def getCard(posY: Int, posX: Int, player: Int): String = {
    gameBoard.players(player).hand.cards(posY)(posX).getValue
  }

  override def getCardAsCard(posY: Int, posX: Int, player: Int): CardInterface = {
    gameBoard.players(player).hand.cards(posY)(posX)
  }

  override def getCardIsUncovered(posY: Int, posX: Int, player: Int): Boolean = {
    gameBoard.players(player).hand.cards(posY)(posX).isUncovered
  }

  override def getPlayerListSize(): Int = {
    gameBoard.players.length
  }

  override def getPlayerName(player: Int): String = {
    gameBoard.players(player).name
  }

  override def getDiscardPileTop(): String = {
    gameBoard.deck.discardPileTopCard().toString
  }

  override def getPlayerPoints(player: Int): String = {
    gameBoard.players(player).points.toString
  }

  override def save: Unit = {
    fileIo.save(gameBoard)
    publish(new BoardChanged)
  }

  override def load: Unit = {
    gameBoard = fileIo.load
    publish(new BoardChanged)
  }

  override def shutdown: Unit = {
    publish(new Shutdown)
  }
}
