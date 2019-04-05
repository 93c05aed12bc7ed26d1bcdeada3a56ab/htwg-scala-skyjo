package de.htwg.se.skyjo.model

import org.scalatest._

class CardSpec extends WordSpec with Matchers{
  "A Card" when { "new" should {
    val card = Card()
    "have a card"  in {
      card.value should be("#")
    }
  }}
}
