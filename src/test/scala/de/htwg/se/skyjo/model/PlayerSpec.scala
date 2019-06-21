
package de.htwg.se.skyjo.model

import de.htwg.se.skyjo.model.{Card, Player}
import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Hans", new Deck())
    "have a name"  in {
      player.name should be("Hans")
    }
    "have a nice hand representation" in {
      player.printHand should be ("#,#,#,#\n#,#,#,#\n#,#,#,#\nSumme der Hand: 0")
    }
    "has cards uncovered" in {
      player.hand.cards(0)(0) = Card(2)
      player.hand.cards(0)(0).isUncovered = true
      player.printHand should be("2,#,#,#\n#,#,#,#\n#,#,#,#\nSumme der Hand: 2")
    }
    "has a nice string representation" in {
      player.toString should be("Hans\n2,#,#,#\n#,#,#,#\n#,#,#,#\nSumme der Hand: 2")
    }
  }}



}