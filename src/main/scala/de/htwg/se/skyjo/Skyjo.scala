package de.htwg.se.skyjo

import de.htwg.se.skyjo.model.Player

object Skyjo {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
