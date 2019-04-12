package de.htwg.se.skyjo.model

case class Deck() {
  var cards: Array[Card] = Array.ofDim[Card]()
}
