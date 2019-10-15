package de.htwg.se.skyjo.model.playerComponent

import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck
import de.htwg.se.skyjo.model.handComponent.handBaseImpl.Hand
import de.htwg.se.skyjo.util.Observer

case class Player(name: String, deck: Deck) extends Observer {

  val playerString = name + "\n" +
    "Summe der Hand: " + hand.summarize() + "\n" +
    hand.toString
  var hand = Hand()
  var stillMyTurn = false
  var canDrawCard = true

  override def update: Boolean = {
    //println(playerString)
    true
  }

}
