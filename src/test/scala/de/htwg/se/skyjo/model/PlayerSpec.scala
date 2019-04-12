
package de.htwg.se.skyjo.model

import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Hans")
    "have a name"  in {
      player.name should be("Hans")
    }
    "have a nice String representation" in {
      player.toString should be("Hans")
    }
  }}


}