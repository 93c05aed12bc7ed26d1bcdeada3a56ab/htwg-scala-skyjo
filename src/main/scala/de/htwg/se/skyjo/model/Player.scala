package de.htwg.se.skyjo.model

case class Player(name: String) {

  var hand = Hand()

  //TODO eventuell sofort printen statt String zurück
  def printHand:String = {
    String.format(s"${hand.cards(0)(0).getValue},${hand.cards(0)(1).getValue},${hand.cards(0)(2).getValue},${hand.cards(0)(3).getValue}\n"
      + s"${hand.cards(1)(0).getValue},${hand.cards(1)(1).getValue},${hand.cards(1)(2).getValue},${hand.cards(1)(3).getValue}\n"
      + s"${hand.cards(2)(0).getValue},${hand.cards(2)(1).getValue},${hand.cards(2)(2).getValue},${hand.cards(2)(3).getValue}\n"
      + s"Summe der Hand: ${hand.summarize}")
  }

  override def toString:String = String.format("%s\n%s", name, printHand)
}