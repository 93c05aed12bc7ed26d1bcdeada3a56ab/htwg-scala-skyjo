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

  def drawHand(): Array[Array[Card]] = {
    var hand: Array[Array[Card]] = Array.ofDim[Card](Hand.ROWS, Hand.COLUMNS)

    for {
      i <- 0 until Hand.ROWS
      j <- 0 until Hand.COLUMNS
    } {
      hand(i)(j) = cards.head
      cards.remove(0)
    }

    hand
  }
}