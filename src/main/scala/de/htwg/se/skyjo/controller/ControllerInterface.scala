package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.model.playerComponent.Player
import de.htwg.se.skyjo.util.Observable

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher {

  var winner: Int

  var trade: Boolean

  def newGame(): Unit

  def createPlayer(name: String): Unit

  def uncoverCard(player: Player, posX: Int, posY: Int): Unit

  def undo: Unit

  def redo: Unit

  def tradeCard(player: Player, posX: Int, posY: Int): Unit

  def drawCard: Unit

  def boardToString: String

  def doMove(posY: Int, posX: Int, player: Int): Unit

  def shutdown: Unit

  def uncoverAll: Unit

  def newRound: Unit

  def getPlayerTurnString: String

  def getWinnerString: String

  def getTurn: Int

  def getCard(posY: Int, posX: Int, player: Int): String

  def getPlayerListSize(): Int

  def getPlayerName(player: Int): String

  def save: Unit

  def load: Unit
}

import scala.swing.event.Event

class BoardChanged extends Event

class CandidatesChanged extends Event

class NewRound extends Event

class GameOver extends Event

class Shutdown extends Event

