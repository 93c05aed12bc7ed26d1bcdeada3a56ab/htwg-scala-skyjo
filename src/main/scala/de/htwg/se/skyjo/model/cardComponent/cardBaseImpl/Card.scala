package de.htwg.se.skyjo.model.cardComponent.cardBaseImpl

import de.htwg.se.skyjo.model.cardComponent.CardInterface

case class Card(value: Int = Card.valUndefined) extends CardInterface {
  var isUncovered: Boolean = false

  override def getValue:String = {
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