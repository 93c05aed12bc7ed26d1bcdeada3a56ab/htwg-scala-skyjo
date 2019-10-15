package de.htwg.se.skyjo.model

import de.htwg.se.skyjo.model.cardComponent.cardBaseImpl.Card
import org.scalatest._

class CardSpec extends WordSpec with Matchers{
  "A Card" when { "new" should {
    var card = Card()
    "have a card"  in {
      card.value should be(Card.valUndefined)
    }
    "be able to be printed uncovered" in {
      card = Card(10)
      card.isUncovered = true
      card.getValue should be("10")
    }
    "be able to be printed covered" in {
      card = Card(10)
      card.isUncovered = false
      card.getValue should be("#")
    }
  }}
}
