package de.htwg.se.skyjo.model.playerComponent

import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck
import de.htwg.se.skyjo.model.handComponent.handBaseImpl.Hand
import de.htwg.se.skyjo.util.Observer

case class Player(name: String, deck: Deck) extends Observer {

  var points = 0
  var hand = Hand()
  var stillMyTurn = false
  var canDrawCard = true
  var playerString: String = name + "\n" +
    "Summe der Hand: " + hand.summarize() + "\n" +
    "Punkte: " + points + "\n" +
    hand.toString()

  override def update: Boolean = {
    playerString = name + "\n" +
      "Summe der Hand: " + hand.summarize() + "\n" +
      "Punkte: " + points + "\n" +
      hand.toString()

    true
  }

}
