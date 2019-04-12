package de.htwg.se.skyjo

import de.htwg.se.skyjo.model.{Card, Deck, Player}

import scala.collection.mutable

object Skyjo {
  def main(args: Array[String]): Unit = {

    // Ablagestapel als Stack
    val discardPile = new mutable.ArrayStack[Card]
    var deck = Deck()
    var player = Player("Hans")
    var x = 0
    var y = 0

    println("Projekt: Skyjo")
    println()

    println(player.name)
    println(player.printHand)
    println()

    discardPile.push(deck.drawCard())
    println("Ablagestapel: " + discardPile.top.value)
    println("Karten im Deck: " + deck.cards.length)

    // TODO methoden schreiben + schleife
    // TODO behandlung mit den hand karten weil die ja vom deck kommen müssen vorrübergehend einfach gezogen
    // TODO x und y gleichzeitig angeben weniger readLines
    // TODO Fehler behandlungen
    println("Zwei Karten aufdecken.")
    println("Erste Karte y-Achse (0,1,2)")
    y = scala.io.StdIn.readInt()
    println("Erste Karte x-Achse (0,1,2,3)")
    x = scala.io.StdIn.readInt()
    player.hand.cards(y)(x) = deck.drawCard()


    println("Zweite Karte y-Achse (0,1,2)")
    y = scala.io.StdIn.readInt()
    println("Zweite Karte x-Achse (0,1,2,3)")
    x = scala.io.StdIn.readInt()
    player.hand.cards(y)(x) = deck.drawCard()

    println(player.printHand)

    println("Karte vom Ablagestapel nehmen? (y/n)")
    var input = scala.io.StdIn.readLine()

    if (input == "y") {
      println("Welche Karte möchten Sie tauschen?")
      println("y-Achse eingeben (0,1,2)")
      y = scala.io.StdIn.readInt()
      println("x-Achse eingeben (0,1,2,3)")
      x = scala.io.StdIn.readInt()

      player.hand.cards(y)(x) = discardPile.pop()
    }else {
      discardPile.push(deck.drawCard())
      println("Karte " + discardPile.top.value + " gezogen")
      println("Welche Karte möchten Sie tauschen?")
      println("y-Achse eingeben (0,1,2)")
      y = scala.io.StdIn.readInt()
      println("x-Achse eingeben (0,1,2,3)")
      x = scala.io.StdIn.readInt()

      player.hand.cards(y)(x) = discardPile.pop()
    }

    println(player.printHand)
    println(deck.cards.length)

  }
}