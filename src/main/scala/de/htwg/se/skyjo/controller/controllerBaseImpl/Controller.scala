package de.htwg.se.skyjo.controller.controllerBaseImpl

import com.google.inject.{Guice, Inject}
import de.htwg.se.skyjo.SkyjoModule
import de.htwg.se.skyjo.controller.{BoardChanged, CandidatesChanged, ControllerInterface, GameOver, NewRound, Shutdown}
import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck
import de.htwg.se.skyjo.model.playerComponent
import de.htwg.se.skyjo.model.playerComponent.Player
import de.htwg.se.skyjo.util.{Observer, UndoManager}


class Controller @Inject() extends ControllerInterface with Observer {

  var injector = Guice.createInjector(new SkyjoModule)

  var undoManager = new UndoManager
  var deck = injector.getInstance(classOf[Deck])
  deck.shuffle()
  var players = scala.collection.mutable.ArrayBuffer.empty[Player]
  var turn = 0
  var winner = -1
  add(this)

  def newGame(): Unit = {
    subscribers = Vector()
    injector = Guice.createInjector(new SkyjoModule)
    undoManager = new UndoManager
    deck = injector.getInstance(classOf[Deck])
    deck.shuffle()
    players = scala.collection.mutable.ArrayBuffer.empty[Player]
    turn = 0
    add(this)
  }

  override def createPlayer(name: String): Unit = {
    players += playerComponent.Player(name, deck)
    players.last.hand.cards = deck.drawHand()
    add(players.last)
    publish(new CandidatesChanged)
  }

  override def undo(): Unit = {
    undoManager.undoStep
    players(turn).stillMyTurn = true
    players(turn).canDrawCard = true
    notifyObservers
    publish(new BoardChanged)
  }

  override def redo(): Unit = {
    undoManager.redoStep
    players(turn).stillMyTurn = true
    players(turn).canDrawCard = true
    notifyObservers
    publish(new BoardChanged)
  }

  override def drawCard(): Unit = {

    if (players(turn).hand.sumUncovered() > 1 && players(turn).canDrawCard == true) {
      undoManager.doStep(new DrawCommand(players(turn)))
      players(turn).stillMyTurn = true
      players(turn).canDrawCard = false
      notifyObservers
      publish(new BoardChanged)
    } else {
      players(turn).stillMyTurn = true
    }
  }

  override def update: Boolean = {
    whichTurn()
    checkEnd()
    true
  }

  def whichTurn(): Unit = {
    if (!players(turn).stillMyTurn) {
      turn += 1
    }
    if (turn == players.length) {
      turn = 0
    }
  }

  def checkEnd(): Unit = {
    if (checkUncovered()) {
      val doublePoint = lowestPoints
      for (i <- 0 until players.length) {
        if (doublePoint && i == turn) {
          players(i).points += (players(i).hand.summarizeAll() * 2)
        } else {
          players(i).points += players(i).hand.summarizeAll()
        }
        if (players(i).points >= 100) {
          return endGame
        }
      }
      publish(new NewRound)
    }
  }

  override def boardToString(): String = {
    val sb = new StringBuilder()
    if (deck.discardPile.nonEmpty) {
      sb.append("Ablagestapel: " + deck.discardPileTopCard() + "\n")
    }
    sb.append("Karten im Deck: " + deck.sumCardInDeck() + "\n")
    for (i <- 0 until players.length) {
      sb.append(players(i).playerString)
    }
    sb.append("-------------------------------------------------\n")
    sb.toString()
  }

  def checkUncovered(): Boolean = {
    if (players(turn).hand.sumUncovered() == 12) {
      true
    } else {
      false
    }
  }

  def lowestPoints(): Boolean = {
    val lowest = players(turn).hand.summarizeAll()
    var bool = false
    if (lowest > 0) {
      for (i <- 0 until players.length) {
        if (!(i == turn)) {
          if (players(i).hand.summarizeAll() <= lowest) {
            bool = true
          }
        }
      }

    }
    bool
  }

  def endGame(): Unit = {
    var winnerPoints = 100
    for (i <- 0 until players.length) {
      if (players(i).points < winnerPoints) {
        winnerPoints = players(i).points
        winner = i
        publish(new GameOver)
      }
    }
  }

  override def doMove(posY: Int, posX: Int, player: Int): Unit = {
    if (player == turn) {
      if (players(turn).hand.cards(posY)(posX).isUncovered) {
        if (players(turn).hand.sumUncovered() > 1) {
          tradeCard(players(turn), posY, posX)
        } else {
          players(turn).stillMyTurn = true
        }
      } else {
        uncoverCard(players(turn), posY, posX)
      }
    }
  }

  override def uncoverCard(player: Player, posY: Int, posX: Int): Unit = {
    undoManager.doStep(new UncoverCommand(player, posY, posX))
    player.stillMyTurn = false
    player.canDrawCard = true
    notifyObservers
    publish(new BoardChanged)
  }

  override def tradeCard(player: Player, posY: Int, posX: Int): Unit = {
    undoManager.doStep(new TradeCommand(player, posY, posX))
    player.stillMyTurn = false
    player.canDrawCard = true
    notifyObservers
    publish(new BoardChanged)
  }

  override def newRound: Unit = {
    deck = injector.getInstance(classOf[Deck])
    deck.shuffle()
    turn = 0

    for (i <- 0 until players.length) {
      players(i).hand.cards = deck.drawHand()
    }
  }

  override def uncoverAll(): Unit = {
    players(turn).hand.uncoverAll()
    notifyObservers
    publish(new BoardChanged)
  }

  override def shutdown: Unit = {
    publish(new Shutdown)
  }
}
