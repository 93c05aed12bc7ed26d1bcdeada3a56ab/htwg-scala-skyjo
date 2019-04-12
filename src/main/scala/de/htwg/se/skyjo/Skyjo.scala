package de.htwg.se.skyjo

import de.htwg.se.skyjo.model.Player

object Skyjo {
  def main(args: Array[String]): Unit = {
    println("Projekt: Skyjo")

    var player = Player("Hans")
    println(player.name)
    println(player.printHand)
    println(player.hand.cards(0)(0))
  }
}