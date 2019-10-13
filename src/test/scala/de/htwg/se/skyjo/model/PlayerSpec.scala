
package de.htwg.se.skyjo.model

import de.htwg.se.skyjo.model.{Card, Player}
import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Hans", new Deck())
    "have a name"  in {
      player.name should be("Hans")
    }
  }}



}