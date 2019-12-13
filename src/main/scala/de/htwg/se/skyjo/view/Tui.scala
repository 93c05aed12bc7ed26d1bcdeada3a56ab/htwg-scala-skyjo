package de.htwg.se.skyjo.view

import java.io.BufferedReader

import de.htwg.se.skyjo.controller.{BoardChanged, CandidatesChanged, ControllerInterface, GameOver, NewRound, Shutdown}

import scala.swing.Reactor

class Tui(controller: ControllerInterface) extends Reactor {

  var stopProcessingInput = false
  listenTo(controller)

  def start(input: BufferedReader): Unit = {
    println("How many Players will play?")
    val num_players = input.readLine().toInt
    // TODO error handling

    for (i <- 0 until num_players){
      println("Name of Player " + (i + 1) + "?")
      val name = input.readLine()
      controller.createPlayer(name)
    }
    processInput(input)
  }

  def start(num_players: Int, input: BufferedReader): Unit = {
    for (i <- 0 until num_players){
      println("Name of Player " + (i + 1) + "?")
      val name = input.readLine()
      controller.createPlayer(name)
    }
    processInput(input)
  }

  def processInput(input: BufferedReader): Unit = {

    println("Du bist an der Reihe: " + controller.getPlayerTurnString)
    while (!stopProcessingInput) {
      if (input.ready()) {
        val line = input.readLine()
        processInputLine(line)
        if (!stopProcessingInput) {
          println("Du bist an der Reihe: " + controller.getPlayerTurnString)
        }
      } else {
        Thread.sleep(200)
      }
    }
  }

  //TODO die karte vom ablagestapel tauschen mit dem spielfeld(offen oder verdeckte) oder
  //TODO karte ziehen und die tauschen mit dem spielfeld(offen oder verdeckte) oder
  //TODO eine karte umdrehen

  //TODO nach den zwei aufgedeckten karten darf der mit der höchsten augensumme anfangen
  //TODO der spieler der die aktuelle spielrunde beendet hat darf die nächste runde anfangen

  //TODO sonder regel wenn in einer senktrechten reihe alle karten gleich sind kann man die ganze reihe auf den ablagestape legen (kann auch beim auswerten passieren)

  def processInputLine(input: String): Unit = {
    input match {
      case "n" => {
        controller.newGame()
        start(new BufferedReader(Console.in))
      }
      case "q" => controller.shutdown
      case "u" => controller.undo
      case "r" => controller.redo
      case "s" => controller.save
      case "l" => controller.load
      case "c" => controller.drawCard
      case "t" => controller.trade = true
      case "a" => controller.uncoverAll
      case _ =>
        input.toList.filter(c => c != ' ').map(c => c.toString) match {
          case posY :: posX :: Nil => {
            controller.doMove(posY.toInt, posX.toInt, controller.getTurn)
          }
          case _ =>
        }
    }
  }

  reactions += {
    case event: BoardChanged => println(controller.boardToString)
    case event: CandidatesChanged => println(controller.boardToString)
    case event: NewRound => newRound
    case event: GameOver => newGame
    case event: Shutdown => stopProcessingInput = true
  }

  def newRound: Unit = {
    controller.newRound
    println("Neue Runde:")
    println("Du bist an der Reihe: " + controller.getPlayerTurnString)
  }

  def newGame: Unit = {
    println("Glückwunsch " + controller.getWinnerString + "! Du hast Gewonnen!")
    println("Neues Spiel:")
    controller.newGame()
    start(new BufferedReader(Console.in))
  }


}
