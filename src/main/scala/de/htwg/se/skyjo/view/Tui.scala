package de.htwg.se.skyjo.view

import de.htwg.se.skyjo.controller.Controller
import de.htwg.se.skyjo.model.Player
import de.htwg.se.skyjo.util.Observer

class Tui(controller: Controller, player: Player) extends Observer {

  controller.add(this)

  def processInput(input: String): Unit ={
    input match {
      case "q" =>
      case "w" => controller.moveCursor("up")
      case "a" => controller.moveCursor("left")
      case "s" => controller.moveCursor("down")
      case "d" => controller.moveCursor("right")
    }
  }

  override def update: Unit = println(player.hand.toString)
}
