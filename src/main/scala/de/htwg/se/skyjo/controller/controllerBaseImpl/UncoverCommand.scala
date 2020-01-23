package de.htwg.se.skyjo.controller.controllerBaseImpl

import de.htwg.se.skyjo.model.playerComponent.Player
import de.htwg.se.skyjo.util.Command

class UncoverCommand(player: Player, posY: Int, posX: Int) extends Command {
  override def doStep: Unit = player.hand.cards(posY)(posX).isUncovered = true

  override def undoStep: Unit = player.hand.cards(posY)(posX).isUncovered = false

  override def redoStep: Unit = player.hand.cards(posY)(posX).isUncovered = true
}
