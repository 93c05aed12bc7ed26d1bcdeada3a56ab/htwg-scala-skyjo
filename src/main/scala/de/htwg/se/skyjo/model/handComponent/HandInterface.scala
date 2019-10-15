package de.htwg.se.skyjo.model.handComponent

trait HandInterface {
  def summarize(): Int

  def sumUncovered(): Int

  def move(dir: String): Unit

  def clamp(value: Int, min: Int, max: Int): Int
}
