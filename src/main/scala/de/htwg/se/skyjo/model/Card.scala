package de.htwg.se.skyjo.model

case class Card() {
  var value = "#"

  def isSet: Boolean = value != "#"
}
