package de.htwg.se.skyjo.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.skyjo.model.fileIoComponent.FileIOInterface
import de.htwg.se.skyjo.model.gameBoardComponent.GameBoardInterface
import play.api.libs.json._

import scala.io.Source

class FileIO extends FileIOInterface {

  override def load: GameBoardInterface = {
    var gameBoard: GameBoardInterface = null
    val source: String = Source.fromFile("gameBoard.json").getLines.mkString
    val json: JsValue = Json.parse(source)

    gameBoard
  }

  override def save(gameBoard: GameBoardInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameBoard.json"))
    pw.write(Json.prettyPrint(gridToJson(gameBoard)))
    pw.close
  }

  def gridToJson(grid: GameBoardInterface) = {
    Json.obj()
  }

}
