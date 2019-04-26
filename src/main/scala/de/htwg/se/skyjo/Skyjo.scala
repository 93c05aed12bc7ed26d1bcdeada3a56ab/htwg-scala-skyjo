package de.htwg.se.skyjo

import de.htwg.se.skyjo.model.{Card, Deck, Player}

import scala.collection.mutable

object Skyjo {

  // TODO schleife
  // TODO behandlung mit den hand karten weil die ja vom deck kommen müssen vorrübergehend einfach gezogen
  // TODO Fehler behandlungen

  def main(args: Array[String]): Unit = {
    // Ablagestapel als Stack
    val discardPile = new mutable.ArrayStack[Card]
    var deck = Deck()
    var player = Player("Hans")

    println("Projekt: Skyjo")
    println()

    println(player.toString)
    println()

    discardPile.push(deck.drawCard())
    println("Ablagestapel: " + discardPile.top.value)
    println("Karten im Deck: " + deck.cards.length)

    start(player, deck)

    println(player.toString)

    println("Karte " + discardPile.top.value + " vom Ablagestapel nehmen? (y/n)")
    var input = scala.io.StdIn.readLine()

    if (input == "y") {
      println("Karte " + discardPile.top.value + " vom Ablagestapel gezogen.")
      trade(player, discardPile.pop())
    }else {
      discardPile.push(deck.drawCard())
      println("Karte " + discardPile.top.value + " vom Aufnahmestapel gezogen")
      trade(player, discardPile.pop())
    }

    println(player.toString)
    println()
    if (!discardPile.isEmpty){println("Ablagestapel: " + discardPile.top.value)}
    println("Karten im Deck: " + deck.cards.length)

  }

  def start(player: Player, deck: Deck): Unit = {
    println("Zwei Karten aufdecken.")

    println("Zweite Karte: y-Achse eingeben (0,1,2) und x-Achse eingeben (0,1,2,3) zB 00 oder 23")
    var yx = scala.io.StdIn.readLine()
    player.hand.cards(yx.charAt(0).toString.toInt)(yx.charAt(1).toString.toInt) = deck.drawCard()

    println("Zweite Karte: y-Achse eingeben (0,1,2) und x-Achse eingeben (0,1,2,3) zB 00 oder 23")
    yx = scala.io.StdIn.readLine()
    player.hand.cards(yx.charAt(0).toString.toInt)(yx.charAt(1).toString.toInt) = deck.drawCard()
  }

  def trade(player: Player, card: Card): Unit ={
    println("Welche Karte möchten Sie tauschen?")
    println("y-Achse eingeben (0,1,2) und x-Achse eingeben (0,1,2,3) zB 00 oder 23")
    var yx = scala.io.StdIn.readLine()
    player.hand.cards(yx.charAt(0).toString.toInt)(yx.charAt(1).toString.toInt) = card
  }

}