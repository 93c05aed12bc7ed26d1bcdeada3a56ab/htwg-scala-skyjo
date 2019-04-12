package de.htwg.se.skyjo.model

import scala.collection.mutable.ListBuffer

case class Deck() {
  var cards = new ListBuffer[Card]()

  for(i <- 0 until 150) {
    cards += Card(Card.possibleValues(i / 10))
  }
  cards = scala.util.Random.shuffle(cards)

  def printDeck() = {
    for(i <- cards) {
      println(i.value)
    }
  }
}


