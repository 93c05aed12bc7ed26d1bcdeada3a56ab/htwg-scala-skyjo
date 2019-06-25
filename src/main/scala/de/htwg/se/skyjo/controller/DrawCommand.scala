package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.model.Player
import de.htwg.se.skyjo.util.Command

class DrawCommand(player: Player) extends Command {
  override def doStep: Unit = player.deck.drawCard()

  override def undoStep: Unit = player.deck.returnCard()

  override def redoStep: Unit = player.deck.drawCard()
}
