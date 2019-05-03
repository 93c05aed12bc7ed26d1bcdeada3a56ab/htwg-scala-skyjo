package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.model.{Deck, Player}
import de.htwg.se.skyjo.util.Observable

class Controller(var deck: Deck, player: Player) extends Observable {
  def createDeck(): Unit = {
    deck = new Deck
    deck.shuffle()
    notifyObservers
  }

  def moveCursor(dir: String): Unit ={
    player.hand.move(dir)
    notifyObservers
  }
}
