package de.htwg.se.skyjo.model.handComponent

trait HandInterface {
  def summarize(): Int

  def summarizeAll(): Int

  def sumUncovered(): Int

}
