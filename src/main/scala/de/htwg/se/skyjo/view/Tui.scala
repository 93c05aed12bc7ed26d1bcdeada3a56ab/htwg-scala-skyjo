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
      case "e" => {
        if (player.hand.cards(player.hand.posY)(player.hand.posX).isUncovered){
          controller.tradeCard
        } else {
          controller.uncoverCard()
        }
      }
      case "u" => controller.undo
      case "r" => controller.redo
      case "c" => controller.drawCard
      case _ =>
    }
  }

  override def update: Boolean = {println(player.hand.toString); true}
}
