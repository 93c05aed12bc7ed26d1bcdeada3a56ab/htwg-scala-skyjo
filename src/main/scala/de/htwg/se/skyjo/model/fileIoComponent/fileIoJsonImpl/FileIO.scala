package de.htwg.se.skyjo.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import de.htwg.se.skyjo.SkyjoModule
import de.htwg.se.skyjo.model.cardComponent.CardInterface
import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck
import de.htwg.se.skyjo.model.fileIoComponent.FileIOInterface
import de.htwg.se.skyjo.model.gameBoardComponent.GameBoardInterface
import de.htwg.se.skyjo.model.playerComponent.Player
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {

  override def load(source: String, game: GameBoardInterface): GameBoardInterface = {
    val json: JsValue = Json.parse(source)

    if(source.contains("gameBoard")){

        var gameBoard: GameBoardInterface = null
        val injector = Guice.createInjector(new SkyjoModule)

        gameBoard = injector.getInstance(classOf[GameBoardInterface])

        val numPlayer = (json \\ "numPlayer").head.as[Int]
        val numDiscardPile = (json \\ "numDiscardPile").head.as[Int]
        val numCards = (json \\ "numCards").head.as[Int]

        //TODO die arrays richtig loaden
        //TODO if drum herum um zu schauen ob es mit gameboard anfängt oder mit zB Move und dann nur einen move laden (für webtec)

        val deck = new Deck
        //deck.discardPile
        //deck.cards
        gameBoard.deck = deck

        for (i <- 0 until numPlayer) {
          val name = (json \\ "name") (i).as[String]
          val player = new Player(name, deck)
          player.points = (json \\ "points") (i).as[Int]
          player.stillMyTurn = (json \\ "stillMyTurn") (i).as[Boolean]
          player.canDrawCard = (json \\ "canDrawCard") (i).as[Boolean]
          player.hand.cards
          gameBoard.players.+=(player)
        }

        gameBoard.turn = (json \\ "turn").head.as[Int]
        gameBoard
      } else {

      if(source.contains("uncover")) {
        val player = (json \\ "player").head.as[Int]
        val x = (json \\ "x").head.as[Int]
        val y = (json \\ "y").head.as[Int]

        game.players(player).hand.cards(x)(y).isUncovered = true

      }

      game
    }

  }

  override def save(gameBoard: GameBoardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameBoard.json"))
    pw.write(Json.prettyPrint(gridToJson(gameBoard)))
    pw.close
  }

  implicit val cardWrites = new Writes[CardInterface] {
    def writes(card: CardInterface) = Json.obj(
      "value" -> card.value,
      "isUncovered" -> card.isUncovered
    )
  }

  def gridToJson(gameBoard: GameBoardInterface) = {
    Json.obj(
      "gameBoard" -> Json.obj(
        "deck" -> Json.obj(
          "discardPile" -> Json.toJson(
            for (i <- 0 until gameBoard.deck.discardPile.length) yield {
              "card" -> Json.toJson(gameBoard.deck.discardPile(i))
            }
          ),
          "cards" -> Json.toJson(
            for (i <- 0 until gameBoard.deck.cards.length) yield {
              "card" -> Json.toJson(gameBoard.deck.cards(i))
            }
          ),
        ),
        "player" -> Json.arr(
          for (i <- 0 until gameBoard.players.length) yield {
            Json.obj(
              "name" -> JsString(gameBoard.players(i).name),
              "points" -> JsNumber(gameBoard.players(i).points),
              "hand" -> Json.toJson(
                for {
                  row <- 0 until gameBoard.players(i).hand.Hand.ROWS;
                  col <- 0 until gameBoard.players(i).hand.Hand.COLUMNS
                } yield {
                  Json.obj(
                    "card" -> Json.toJson(gameBoard.players(i).hand.cards(row)(col))
                  )
                }
              ),
              "stillMyTurn" -> JsBoolean(gameBoard.players(i).stillMyTurn),
              "canDrawCard" -> JsBoolean(gameBoard.players(i).canDrawCard),
            )
          }
        ),
        "turn" -> JsNumber(gameBoard.turn),
        "numPlayer" -> JsNumber(gameBoard.players.length),
        "numDiscardPile" -> JsNumber(gameBoard.deck.discardPile.length),
        "numCards" -> JsNumber(gameBoard.deck.cards.length),
      )
    )
  }

}
