package de.htwg.se.skyjo

import org.scalatest.{Matchers, WordSpec}

class SkyjoSpec extends WordSpec with Matchers {

  "The Sudoku main class" should {
    "accept text input as argument without readline loop, to test it from command line " in {
      Skyjo.main(Array[String]("s"))
    }
  }

}