package de.htwg.se.skyjo.model

import de.htwg.se.skyjo.controller.Controller
import de.htwg.se.skyjo.util.Observer

case class Player(name: String, deck: Deck) extends Observer {

  var hand = Hand()
  var stillMyTurn = false
  var canDrawCard = true

  val playerString = name + "\n" +
                        "Summe der Hand: " + hand.summarize() + "\n" +
                        hand.toString

  override def update: Boolean = {
    //println(playerString)
    true
  }

}