
package de.htwg.se.skyjo.model

import de.htwg.se.skyjo.model.cardComponent.cardBaseImpl.Card
import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck
import org.scalatest._

class DeckSpec extends WordSpec with Matchers {
  "A Deck" when { "new" should {
    val deck = Deck()
    "is the correct type" in {
      deck shouldBe a [Deck]
    }
    "has all cards" in {
      deck.cards.length should be (150)
/*    }
    "can draw a card"  in {
      deck.drawCard() shouldBe a [Card]*/
    }
    "has less cards after drawing" in {
      deck.drawCard()
      deck.cards.length should be < 150
    }
    /*
    "can print its deck" in {
      deck.printDeck() should be("")
    }
    */
  }}



}