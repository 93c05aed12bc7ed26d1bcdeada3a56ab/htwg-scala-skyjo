package de.htwg.se.skyjo

import de.htwg.se.skyjo.model.Player

object YourGame {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
