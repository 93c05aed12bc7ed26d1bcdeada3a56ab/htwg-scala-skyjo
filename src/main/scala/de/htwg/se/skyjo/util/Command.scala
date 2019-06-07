package de.htwg.se.skyjo.util

trait Command {

  def doStep: Unit

  def undoStep: Unit

  def redoStep: Unit

}
