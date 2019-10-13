package de.htwg.se.skyjo.model

import de.htwg.se.skyjo.Skyjo.{deck}
import de.htwg.se.skyjo.controller.Controller
import de.htwg.se.skyjo.view.Tui

case class Player(name: String, deck: Deck) {

  var hand = Hand()
  val controller = new Controller(deck, Player.this)
  val tui = new Tui(controller, Player.this)
  controller.notifyObservers
  var stillMyTurn = false
}