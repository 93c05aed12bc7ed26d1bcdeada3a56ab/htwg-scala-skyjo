package de.htwg.se.skyjo.model.cardComponent

trait CardInterface {
  var isUncovered: Boolean
  def getValue: String

  def value: Int
}
