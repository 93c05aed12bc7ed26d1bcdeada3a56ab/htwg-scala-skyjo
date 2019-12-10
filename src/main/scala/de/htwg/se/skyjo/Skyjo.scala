package de.htwg.se.skyjo

import java.io.BufferedReader

import com.google.inject.Guice
import de.htwg.se.skyjo.controller.ControllerInterface
import de.htwg.se.skyjo.view.Tui


object Skyjo {

  val injector = Guice.createInjector(new SkyjoModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new Tui(controller)
  controller.newGame()

  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      tui.start(args(0).toInt, new BufferedReader(Console.in))
      // TODO error handling
    }else {
      tui.start(new BufferedReader(Console.in))
    }
  }

}