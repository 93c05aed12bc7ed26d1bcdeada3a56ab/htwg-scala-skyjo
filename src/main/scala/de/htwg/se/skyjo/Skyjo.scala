package de.htwg.se.skyjo

import de.htwg.se.skyjo.model.{Deck, Player}
import de.htwg.se.skyjo.view.Tui


object Skyjo {

  val deck = Deck()

  def main(args: Array[String]): Unit = {

    var players = scala.collection.mutable.ArrayBuffer.empty[Player]
    var num_players : Int = 0

    if (args.length > 0) {
      num_players = args(0).toInt
      // TODO error handling
    } else {
      println("How many Players will play?")
      num_players = scala.io.StdIn.readLine().toInt
      // TODO error handling
    }

    for (i <- 0 until num_players){
      println("Name of Player " + (i + 1) + "?")
      val name = scala.io.StdIn.readLine()
      players += Player(name, deck.shuffle())
    }

    val tui = new Tui(players)
    tui.processInput()

  }

}