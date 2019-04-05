package de.htwg.se.skyjo

import de.htwg.se.skyjo.model.{Hand, Player}

object Skyjo {
  def main(args: Array[String]): Unit = {
    println("Projekt: Skyjo")
    var player = Player("Hans")
    println(player.toString)
  }
}