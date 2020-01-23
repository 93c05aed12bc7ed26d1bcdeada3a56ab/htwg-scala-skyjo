package de.htwg.se.skyjo

import com.google.inject.AbstractModule
import de.htwg.se.skyjo.controller.ControllerInterface
import de.htwg.se.skyjo.model.deckComponent.DeckInterface
import de.htwg.se.skyjo.model.fileIoComponent.FileIOInterface
import de.htwg.se.skyjo.model.gameBoardComponent.GameBoardInterface
import net.codingwell.scalaguice.ScalaModule

class SkyjoModule extends AbstractModule with ScalaModule {

  override def configure(): Unit = {
    bind[ControllerInterface].to[controller.controllerBaseImpl.Controller]
    bind[DeckInterface].to[model.deckComponent.deckBaseImpl.Deck]
    bind[GameBoardInterface].to[model.gameBoardComponent.gameBoardImpl.GameBoard]
    bind[FileIOInterface].to[model.fileIoComponent.fileIoJsonImpl.FileIO]
  }
}
