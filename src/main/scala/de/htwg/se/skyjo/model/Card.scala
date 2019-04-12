package de.htwg.se.skyjo.model

case class Card(value: Int = Card.valUndefined) {
  var isUncovered: Boolean = false


  override def toString:String = value.toString

  def getValue:String = {
    if(isUncovered){
      value.toString
    } else {
      "#"
    }
  }
}
object Card {
  val valUndefined: Int = -3
}

