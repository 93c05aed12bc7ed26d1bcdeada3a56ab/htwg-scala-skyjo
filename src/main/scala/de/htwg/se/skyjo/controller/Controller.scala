package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.model.{Deck, Hand, Player}
import de.htwg.se.skyjo.util.Observable

class Controller(var deck: Deck, player: Player) extends Observable {

  createDeck()
  player.hand.cards = deck.drawHand()

  def createDeck(): Unit = {
    deck = new Deck
    deck.shuffle()
    notifyObservers
  }

  def moveCursor(dir: String): Unit ={
    player.hand.move(dir)
    notifyObservers
  }

  def uncoverCard(): Unit = {
    player.hand.cards(player.hand.posY)(player.hand.posX).isUncovered = true
    notifyObservers
  }
}
