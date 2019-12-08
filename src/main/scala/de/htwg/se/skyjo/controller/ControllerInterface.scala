package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck
import de.htwg.se.skyjo.model.playerComponent.Player
import de.htwg.se.skyjo.util.Observable

import scala.swing.Publisher

trait ControllerInterface extends Observable with Publisher {

  var turn: Int

  var deck: Deck

  var players: scala.collection.mutable.ArrayBuffer[Player]

  var winner: Int

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
}

import scala.swing.event.Event

class BoardChanged extends Event

class CandidatesChanged extends Event

class NewRound extends Event

class GameOver extends Event

class Shutdown extends Event

