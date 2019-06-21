package de.htwg.se.skyjo

import de.htwg.se.skyjo.model.{Card, Deck, Player}


object Skyjo {

  val deck = Deck()

  def main(args: Array[String]): Unit = {

    var players = scala.collection.mutable.ArrayBuffer.empty[Player]
    var num_players : Int = 0
    var turn : Int = 0
    var input: String = ""

    if (args.length > 0) {
      num_players = args(0).toInt
      // TODO error handling
    }

    println("How many Players will play?")
    num_players = scala.io.StdIn.readLine().toInt
    // TODO error handling

    for (i <- 0 until num_players){
        println("Name of Player " + (i+1) + "?")
        var name = scala.io.StdIn.readLine()
        players += new Player(name, deck.shuffle())
    }

    deck.drawCard()

    do {

      // TODO wenn redo/undo der selbe player nochmal
      // TODO wenn auf aufgedeckte karte 'e' dann tauschen mit discardpile
      // TODO karte ziehen mit einer taste, dann der selbe player nochmal

      if (deck.discardPile.nonEmpty){println("Ablagestapel: " + deck.discardPile.top.value)}
      println("Karten im Deck: " + deck.cards.length)

      turn = playerTurn(turn, players)

      if (turn == num_players) {
        turn = 0
      }

    }
    while (input != "q")
  }

  def playerTurn(turn: Int, players : scala.collection.mutable.ArrayBuffer[Player]): Int ={
    println("Your Turn: " + players(turn).name)
    val input = scala.io.StdIn.readLine()
    players(turn).tui.processInput(input)

    turn + 1
  }
}