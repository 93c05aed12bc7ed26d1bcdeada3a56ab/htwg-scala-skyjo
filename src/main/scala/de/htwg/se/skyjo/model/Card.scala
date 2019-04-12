package de.htwg.se.skyjo.model

case class Card(value: Int = 0) {

  var isUncovered: Boolean = false

  override def toString:String = value.toString
}

object Card {
  val possibleValues = Array(-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)

}
