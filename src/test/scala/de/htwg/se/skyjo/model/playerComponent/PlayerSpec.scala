
package de.htwg.se.skyjo.model.playerComponent

import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck
import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = playerComponent.Player("Hans", new Deck())
    "have a name"  in {
      player.name should be("Hans")
    }
  }}



}