package de.htwg.se.skyjo.model.handComponent

import de.htwg.se.skyjo.model.cardComponent.CardInterface

trait HandInterface {

  var cards: Array[Array[CardInterface]]

  def summarize(): Int

  def summarizeAll(): Int

  def sumUncovered(): Int

  object Hand {
    val ROWS: Int = 3
    val COLUMNS: Int = 4
  }
}
