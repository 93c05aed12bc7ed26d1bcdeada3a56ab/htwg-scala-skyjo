package de.htwg.se.skyjo.controller.controllerBaseImpl

import de.htwg.se.skyjo.model.playerComponent.Player
import de.htwg.se.skyjo.util.Command

class TradeCommand(player: Player, posY: Int, posX: Int) extends Command {
  override def doStep: Unit = {
    val draw = player.deck.discardPile.pop()
    val selectedCard = player.hand.cards(posY)(posX)

    player.hand.cards(posY)(posX) = draw
    player.deck.discardPile.push(selectedCard)
  }

  override def undoStep: Unit ={
    val draw = player.deck.discardPile.pop()
    val selectedCard = player.hand.cards(posY)(posX)

    player.hand.cards(posY)(posX) = draw
    player.deck.discardPile.push(selectedCard)
  }

  override def redoStep: Unit ={
    val draw = player.deck.discardPile.pop()
    val selectedCard = player.hand.cards(posY)(posX)

    player.hand.cards(posY)(posX) = draw
    player.deck.discardPile.push(selectedCard)
  }
}
