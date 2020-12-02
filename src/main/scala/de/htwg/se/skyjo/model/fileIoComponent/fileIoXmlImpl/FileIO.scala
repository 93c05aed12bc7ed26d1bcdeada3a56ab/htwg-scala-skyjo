package de.htwg.se.skyjo.model.fileIoComponent.fileIoXmlImpl

import de.htwg.se.skyjo.model.fileIoComponent.FileIOInterface
import de.htwg.se.skyjo.model.gameBoardComponent.GameBoardInterface

import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {
  override def load(source: String, game: GameBoardInterface): GameBoardInterface = ???

  override def save(gameBoard: GameBoardInterface): Unit = saveString(gameBoard)

  def saveString(gameBoard: GameBoardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameBoard.xml"))
    val prettyPrinter = new PrettyPrinter(120, 4)
    val xml = prettyPrinter.format(gameBoardToXml(gameBoard))
    pw.write(xml)
    pw.close
  }

  def gameBoardToXml(gameBoard: GameBoardInterface) = {
    <gameBoard turn={gameBoard.turn.toString} numPlayer={gameBoard.players.length.toString} numDiscardPile={gameBoard.deck.discardPile.length.toString} numCards={gameBoard.deck.cards.length.toString}>
      <deck>
        <discardPile>
          {for (i <- 0 until gameBoard.deck.discardPile.length) yield {
          discardCardToXml(gameBoard, i)
        }}
        </discardPile>
        <cards>
          {for (i <- 0 until gameBoard.deck.cards.length) yield {
          deckCardToXml(gameBoard, i)
        }}
        </cards>
      </deck>
      <players>
        {for (i <- 0 until gameBoard.players.length) yield {
        playerToXml(gameBoard, i)
      }}
      </players>
    </gameBoard>
  }

  def discardCardToXml(gameBoard: GameBoardInterface, i: Int) = {
    <card isUncovered={gameBoard.deck.discardPile(i).isUncovered.toString}>
      {gameBoard.deck.discardPile(i).value.toString}
    </card>
  }

  def deckCardToXml(gameBoard: GameBoardInterface, i: Int) = {
    <card isUncovered={gameBoard.deck.cards(i).isUncovered.toString}>
      {gameBoard.deck.cards(i).value.toString}
    </card>
  }

  def playerToXml(gameBoard: GameBoardInterface, i: Int) = {
    <player name={gameBoard.players(i).name} points={gameBoard.players(i).points.toString} stillMyTurn={gameBoard.players(i).stillMyTurn.toString} canDrawCard={gameBoard.players(i).canDrawCard.toString}>
      {for {
      row <- 0 until gameBoard.players(i).hand.Hand.ROWS;
      col <- 0 until gameBoard.players(i).hand.Hand.COLUMNS
    } yield {
      playerCardToXml(gameBoard, i, row, col)
    }}
    </player>
  }

  def playerCardToXml(gameBoard: GameBoardInterface, i: Int, row: Int, col: Int) = {
    <card isUncovered={gameBoard.players(i).hand.cards(row)(col).isUncovered.toString}>
      {gameBoard.players(i).hand.cards(row)(col).value.toString}
    </card>
  }

  def saveXML(gameBoard: GameBoardInterface): Unit = {
    scala.xml.XML.save("gameBoard.xml", gameBoardToXml(gameBoard))
  }
}
