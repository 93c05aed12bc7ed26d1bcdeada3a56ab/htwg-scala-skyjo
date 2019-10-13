package de.htwg.se.skyjo.view

import de.htwg.se.skyjo.Skyjo.deck
import de.htwg.se.skyjo.model.Player

class Tui(players: scala.collection.mutable.ArrayBuffer[Player]) {

  var shutdown = false

  def processInput(): Unit = {

    var turn: Int = 0
    deck.drawCard()

    do {
      if (deck.discardPile.nonEmpty) {
        println("Ablagestapel: " + deck.discardPile.top.value)
      }
      println("Karten im Deck: " + deck.cards.length)

      turn = playerTurn(turn)

      if (turn == players.length) {
        turn = 0
      }
    }
    while (shutdown != true)
  }

  //TODO die karte vom ablagestapel tauschen mit dem spielfeld oder
  //TODO karte ziehen und die tauschen mit dem spielfeld oder
  //TODO eine karte umdrehen

  //TODO spielrunden ende wenn einer alle karten aufgedeckt hat (sumUncovered == 12) dann dürfen alle nocheinmal eine runde spielen
  //TODO der spieler der zu gemacht hat muss die wenigsten punkte haben sonst werden seine punkte addiert
  //TODO wenn Punkte über 100 ist, ist das spiel vorbei und der mit den wenigsten punkten gewinnt das spiel
  //TODO sonder regel wenn in einer senktrechten reihe alle karten gleich sind kann man die ganze reihe auf den ablagestape legen (kann auch beim auswerten passieren)

  def playerTurn(turn: Int): Int = {
    println("Du bist an der Reihe: " + players(turn).name)
    val input = scala.io.StdIn.readLine()
    processInputLine(input, players(turn))

    if (players(turn).stillMyTurn) {
      turn
    } else {
      turn + 1
    }
  }

  def processInputLine(input: String, player: Player): Unit = {
    input match {
      case "q" => shutdown = true
      case "w" => player.controller.moveCursor("up")
      case "a" => player.controller.moveCursor("left")
      case "s" => player.controller.moveCursor("down")
      case "d" => player.controller.moveCursor("right")
      case "e" => {
        if (player.hand.cards(player.hand.posY)(player.hand.posX).isUncovered) {
          if (player.hand.sumUncovered() > 1) {
            player.controller.tradeCard
          } else player.stillMyTurn = true
        } else {
          player.controller.uncoverCard()
        }
      }
      case "u" => player.controller.undo
      case "r" => player.controller.redo
      case "c" => {
        if (player.hand.sumUncovered() > 1 && player.canDrawCard == true) {
          player.controller.drawCard
        } else player.stillMyTurn = true
      }
      case _ => player.stillMyTurn = true
    }
  }
}
