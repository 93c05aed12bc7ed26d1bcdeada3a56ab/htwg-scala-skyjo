package de.htwg.se.skyjo

import com.google.inject.Guice
import de.htwg.se.skyjo.controller.controllerBaseImpl.Controller
import de.htwg.se.skyjo.view.Tui


object Skyjo {

  val injector = Guice.createInjector(new SkyjoModule)
  val controller = injector.getInstance(classOf[Controller])
  val tui = new Tui(controller)

  def main(args: Array[String]): Unit = {
    if (args.length > 0) {
      tui.start(args(0).toInt)
      // TODO error handling
    }else {
      tui.start()
    }
  }

}