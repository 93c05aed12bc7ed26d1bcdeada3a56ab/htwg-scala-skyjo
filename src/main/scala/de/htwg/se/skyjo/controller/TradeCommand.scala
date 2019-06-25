package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.model.Player
import de.htwg.se.skyjo.util.Command

class TradeCommand(player: Player) extends Command{
  override def doStep: Unit = {
    val draw = player.deck.discardPile.pop()
    val selectedCard = player.hand.cards(player.hand.posY)(player.hand.posX)

    player.hand.cards(player.hand.posY)(player.hand.posX) = draw
    player.deck.discardPile.push(selectedCard)
  }

  override def undoStep: Unit ={
    val draw = player.deck.discardPile.pop()
    val selectedCard = player.hand.cards(player.hand.posY)(player.hand.posX)

    player.hand.cards(player.hand.posY)(player.hand.posX) = draw
    player.deck.discardPile.push(selectedCard)
  }

  override def redoStep: Unit ={
    val draw = player.deck.discardPile.pop()
    val selectedCard = player.hand.cards(player.hand.posY)(player.hand.posX)

    player.hand.cards(player.hand.posY)(player.hand.posX) = draw
    player.deck.discardPile.push(selectedCard)
  }
}
