package de.htwg.se.skyjo.model

case class Player(name: String) {

  var hand = Hand()

  def printHand:String = {
  String.format(s"${hand.cards(0)(0).value},${hand.cards(0)(1).value},${hand.cards(0)(2).value},${hand.cards(0)(3).value}\n"
   + s"${hand.cards(1)(0).value},${hand.cards(1)(1).value},${hand.cards(1)(2).value},${hand.cards(1)(3).value}\n"
   + s"${hand.cards(2)(0).value},${hand.cards(2)(1).value},${hand.cards(2)(2).value},${hand.cards(2)(3).value}")
  }

  def printName:String = name

  override def toString:String = String.format("%s\n%s", printName, printHand)
}