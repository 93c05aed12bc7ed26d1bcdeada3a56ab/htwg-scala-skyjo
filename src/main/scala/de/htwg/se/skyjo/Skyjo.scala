package de.htwg.se.skyjo

import de.htwg.se.skyjo.controller.Controller
import de.htwg.se.skyjo.model.{Deck, Player}
import de.htwg.se.skyjo.view.Tui


object Skyjo {

  def main(args: Array[String]): Unit = {
    val controller = new Controller
    val tui = new Tui(controller)

    if (args.length > 0) {
      tui.start(args(0).toInt)
      // TODO error handling
    }else {
      tui.start()
    }
  }

}