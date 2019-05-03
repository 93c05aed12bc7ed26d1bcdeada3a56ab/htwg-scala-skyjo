package de.htwg.se.skyjo.model

import scala.collection.mutable.ListBuffer

case class Deck() {
  var cards = new ListBuffer[Card]()

  for(i <- 0 until 150) {
    cards += Card(Card.possibleValues(i / 10))
  }

  def shuffle(): Deck = {
    cards = scala.util.Random.shuffle(cards)
    this
  }

  def drawCard(): Card = {
    val draw = cards.head
    draw.isUncovered = true
    cards.remove(0)
    draw
  }
}


