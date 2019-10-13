package de.htwg.se.skyjo.model

import de.htwg.se.skyjo.controller.Controller
import de.htwg.se.skyjo.util.Observer

case class Player(name: String, deck: Deck) extends Observer {

  var hand = Hand()
  val controller = new Controller(deck, Player.this)
  controller.add(this)
  controller.notifyObservers
  var stillMyTurn = false
  var canDrawCard = true

  override def update: Boolean = {
    print(hand.toString)
    println("Summe der Hand: " + hand.summarize())
    true
  }

}