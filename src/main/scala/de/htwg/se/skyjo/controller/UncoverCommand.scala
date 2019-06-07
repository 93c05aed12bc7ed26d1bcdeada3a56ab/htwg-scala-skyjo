package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.util.Command
import de.htwg.se.skyjo.model.Player

class UncoverCommand(player: Player) extends Command {
  override def doStep: Unit = player.hand.cards(player.hand.posY)(player.hand.posX).isUncovered = true

  override def undoStep: Unit = player.hand.cards(player.hand.posY)(player.hand.posX).isUncovered = false

  override def redoStep: Unit = player.hand.cards(player.hand.posY)(player.hand.posX).isUncovered = true
}
