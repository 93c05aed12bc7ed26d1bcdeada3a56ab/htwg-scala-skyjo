package de.htwg.se.skyjo

import java.io.BufferedReader

import com.google.inject.{Guice, Injector}
import de.htwg.se.skyjo.aview.Tui
import de.htwg.se.skyjo.aview.gui.SwingGui
import de.htwg.se.skyjo.controller.ControllerInterface
import de.htwg.se.skyjo.controller.controllerBaseImpl.Controller

object Skyjo {

  val injector: Injector = Guice.createInjector(new SkyjoModule)
  val controller: ControllerInterface = injector.getInstance(classOf[Controller])
  val tui = new Tui(controller)
  val gui = new SwingGui(controller)
  controller.newGame()

  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      tui.start(args(0).toInt, new BufferedReader(Console.in))
      // TODO error handling
    }else {
      //tui.start(new BufferedReader(Console.in))
    }
  }

}