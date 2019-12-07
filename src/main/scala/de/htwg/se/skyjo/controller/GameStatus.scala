package de.htwg.se.skyjo.controller

object GameStatus extends Enumeration {

  type GameStatus = Value
  val FIRSTROUND, IDLE, NEW, UNDO, REDO, SAVED, LOADED, UNCOVER, TRADE, LASTROUND, GAMEOVER = Value

}
