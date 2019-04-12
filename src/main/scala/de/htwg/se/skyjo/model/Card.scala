package de.htwg.se.skyjo.model

case class Card(value: Int = 0) {
  override def toString:String = value.toString
}
