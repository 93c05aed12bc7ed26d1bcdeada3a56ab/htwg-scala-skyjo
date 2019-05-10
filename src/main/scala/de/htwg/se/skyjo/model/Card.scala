package de.htwg.se.skyjo.model

case class Card(value: Int = Card.valUndefined) {
  var isUncovered: Boolean = false

  def getValue:String = {
    if(isUncovered){
      value.toString
    } else {
      "#"
    }
  }
}

object Card {
  val possibleValues = Array(-2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
  val valUndefined: Int = -3
}