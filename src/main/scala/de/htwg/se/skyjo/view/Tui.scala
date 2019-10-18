package de.htwg.se.skyjo.view

import de.htwg.se.skyjo.controller.controllerBaseImpl.Controller
import de.htwg.se.skyjo.model.playerComponent.Player

class Tui(controller: Controller) {

  def start(): Unit = {
    println("How many Players will play?")
    var num_players = scala.io.StdIn.readLine().toInt
    // TODO error handling

    for (i <- 0 until num_players){
      println("Name of Player " + (i + 1) + "?")
      val name = scala.io.StdIn.readLine()
      controller.createPlayer(name)
    }
    processInput()
  }

  def start(num_players : Int): Unit = {
    for (i <- 0 until num_players){
      println("Name of Player " + (i + 1) + "?")
      val name = scala.io.StdIn.readLine()
      controller.createPlayer(name)
    }
    processInput()
  }

  def processInput(): Unit = {

    controller.deck.drawCard()

    do {

      playerTurn(controller.turn)

      println(controller.boardToString())

      if (controller.turn == controller.players.length) {
        controller.turn = 0
      }
    }
    while (controller.shutdown != true)
  }

  //TODO die karte vom ablagestapel tauschen mit dem spielfeld oder
  //TODO karte ziehen und die tauschen mit dem spielfeld oder
  //TODO eine karte umdrehen

  //TODO spielrunden ende wenn einer alle karten aufgedeckt hat (sumUncovered == 12) dann dürfen alle nocheinmal eine runde spielen
  //TODO der spieler der zu gemacht hat muss die wenigsten punkte haben sonst werden seine punkte addiert
  //TODO wenn Punkte über 100 ist, ist das spiel vorbei und der mit den wenigsten punkten gewinnt das spiel
  //TODO sonder regel wenn in einer senktrechten reihe alle karten gleich sind kann man die ganze reihe auf den ablagestape legen (kann auch beim auswerten passieren)

  def playerTurn(turn: Int): Unit = {
    println("Du bist an der Reihe: " + controller.players(turn).name)
    val input = scala.io.StdIn.readLine()
    processInputLine(input, controller.players(turn))

    if (!controller.players(turn).stillMyTurn) {
      controller.turn += 1
    }
  }

  def processInputLine(input: String, player: Player): Unit = {
    input match {
      case "q" => controller.shutdown = true
      case "w" => controller.moveCursor("up", player)
      case "a" => controller.moveCursor("left", player)
      case "s" => controller.moveCursor("down", player)
      case "d" => controller.moveCursor("right", player)
      case "e" => {
        if (player.hand.cards(player.hand.posY)(player.hand.posX).isUncovered) {
          if (player.hand.sumUncovered() > 1) {
            controller.tradeCard(player)
          } else player.stillMyTurn = true
        } else {
          controller.uncoverCard(player)
        }
      }
      case "u" => controller.undo(player)
      case "r" => controller.redo(player)
      case "c" => {
        if (player.hand.sumUncovered() > 1 && player.canDrawCard == true) {
          controller.drawCard(player)
        } else player.stillMyTurn = true
      }
      case _ =>
        input.toList.filter(c => c != ' ').map(c => c.toString) match {
          case posX :: posY :: Nil          => controller.setCursor(posX.toInt, posY.toInt, player)
          //TODO ErrorHandling
          case posX :: posY :: input :: Nil => {
            controller.setCursor(posX.toInt, posY.toInt, player)
            processInputLine(input, player)
          }
          //TODO ErrorHandling
          case _                            => player.stillMyTurn = true
        }
    }
    //println(controller.boardToString())
  }

}
