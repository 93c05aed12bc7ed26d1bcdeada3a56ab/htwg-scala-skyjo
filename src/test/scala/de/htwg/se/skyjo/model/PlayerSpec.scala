
package de.htwg.se.skyjo.model

import org.scalatest._

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Your Name")
    "have a name"  in {
      player.name should be("Your Name")
    }
    "have a hand" in {
      player.hand should be(Hand())
    }
    "have a nice String representation from name" in {
      player.printName should be("Your Name")
    }
    "have a nice String representation from hand" in {
      player.printHand should be("#,#,#,#\n#,#,#,#\n#,#,#,#")
    }
    "have a nice String representation" in {
      player.toString should be("Your Name\n#,#,#,#\n#,#,#,#\n#,#,#,#")
    }
  }}
}