package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.controller.Controller
import de.htwg.se.skyjo.model.{Deck, Player}
import de.htwg.se.skyjo.util.Observer
import org.scalatest._

class ControllerSpec extends WordSpec with Matchers {
  "A Controller" when { "new" should {
    val controller = new Controller(new Deck(), new Player("Hans", new Deck()))
    val observer = new Observer{
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      override def update: Boolean = {updated = true; updated}
    }
    controller.add(observer)
    "notify its observer after createDeck" in {
      observer.updated should be(true)
      controller.deck.cards.size should be(150)
    }
  }}
}
