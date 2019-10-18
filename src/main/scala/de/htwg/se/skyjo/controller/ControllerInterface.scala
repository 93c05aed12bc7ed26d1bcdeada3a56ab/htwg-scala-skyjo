package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.model.playerComponent.Player
import de.htwg.se.skyjo.util.Observable

trait ControllerInterface extends Observable {

  def createPlayer(name: String): Unit

  def moveCursor(dir: String, player: Player): Unit

  def setCursor(posX: Int, posY: Int, player: Player): Unit

  def uncoverCard(player: Player): Unit

  def undo(player: Player): Unit

  def redo(player: Player): Unit

  def tradeCard(player: Player): Unit

  def drawCard(player: Player): Unit

  def checkFullUncovered(): Boolean

  def boardToString: String

}
