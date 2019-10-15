package de.htwg.se.skyjo.model.deckComponent.deckBaseImpl

import de.htwg.se.skyjo.model.cardComponent.cardBaseImpl.Card
import de.htwg.se.skyjo.model.deckComponent.DeckInterface
import de.htwg.se.skyjo.model.handComponent.handBaseImpl.Hand

import scala.collection.mutable.ListBuffer

case class Deck() extends DeckInterface {
  val discardPile = new scala.collection.mutable.ArrayStack[Card]
  var cards = new ListBuffer[Card]()

  for (i <- 0 until 150) {
    cards += Card(Card.possibleValues(i / 10))
  }

  def shuffle(): Deck = {
    cards = scala.util.Random.shuffle(cards)
    this
  }

  def drawCard(): Unit = {
    val draw = cards.head
    draw.isUncovered = true
    cards.remove(0)
    discardPile.push(draw)
  }

  def returnCard(): Unit = {
    val draw = discardPile.pop()
    draw.isUncovered = false
    cards.insert(0, draw)
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

  def sumCardInDeck(): Int = {
    cards.length
  }

  def discardPileTopCard(): Int = {
    discardPile.top.value
  }

}
