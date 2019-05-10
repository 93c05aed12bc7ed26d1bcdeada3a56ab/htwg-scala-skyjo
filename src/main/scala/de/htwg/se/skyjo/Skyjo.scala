package de.htwg.se.skyjo

import de.htwg.se.skyjo.controller.Controller
import de.htwg.se.skyjo.model.{Card, Deck, Player}
import de.htwg.se.skyjo.view.Tui


object Skyjo {

  val player = new Player("Hans")
  val controller = new Controller(new Deck(), player)
  val tui = new Tui(controller, player)
  controller.notifyObservers

  def main(args: Array[String]): Unit = {

    var input: String = ""

    do{
      input = readLine()
      tui.processInput(input)
    }
    while(input != "q")

  }
}