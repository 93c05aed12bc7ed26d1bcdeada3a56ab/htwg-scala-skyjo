package de.htwg.se.skyjo

import com.google.inject.AbstractModule
import de.htwg.se.skyjo.controller.ControllerInterface
import de.htwg.se.skyjo.model.deckComponent.DeckInterface
import de.htwg.se.skyjo.model.handComponent.HandInterface
import net.codingwell.scalaguice.ScalaModule

class SkyjoModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bind[ControllerInterface].to[controller.controllerBaseImpl.Controller]
    bind[HandInterface].to[model.handComponent.handBaseImpl.Hand]
    bind[DeckInterface].to[model.deckComponent.deckBaseImpl.Deck]
  }
}
