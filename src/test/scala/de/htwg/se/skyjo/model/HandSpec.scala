package de.htwg.se.skyjo.model

import org.scalatest._

class HandSpec extends WordSpec with Matchers{
  "A Hand" when { "new" should {
    val hand = Hand()
    "have cards"  in {
      hand.cards should be(Array(Array(Card(), Card(), Card(), Card()), Array(Card(), Card(), Card(), Card()), Array(Card(), Card(), Card(), Card())))
    }
    "cards should be initialized to 0" in {
      for {
        i <- 0 until 3
        j <- 0 until 4
      } hand.cards(i)(j).value should be (Card.valUndefined)
    }
    "should be a hand" in {
      hand should be(Hand())
    }
  }}
}

