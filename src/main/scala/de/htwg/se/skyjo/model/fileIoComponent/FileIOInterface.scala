package de.htwg.se.skyjo.model.fileIoComponent

import de.htwg.se.skyjo.controller.ControllerInterface
import de.htwg.se.skyjo.model.gameBoardComponent.GameBoardInterface

trait FileIOInterface {
  def load(source: String, game: GameBoardInterface, controller: ControllerInterface): GameBoardInterface

  def save(gameBoard: GameBoardInterface): Unit
}
