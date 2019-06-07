package de.htwg.se.skyjo.controller

import de.htwg.se.skyjo.model.{Deck, Hand, Player}
import de.htwg.se.skyjo.util.{Observable, UndoManager}


class Controller(var deck: Deck, player: Player) extends Observable {

  private val undoManager = new UndoManager
  createDeck()
  player.hand.cards = deck.drawHand()

  def createDeck(): Unit = {
    deck = new Deck
    deck.shuffle()
    notifyObservers
  }

  def moveCursor(dir: String): Unit = {
    player.hand.move(dir)
    notifyObservers
  }

  def uncoverCard(): Unit = {
    undoManager.doStep(new UncoverCommand(player))
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }
}
