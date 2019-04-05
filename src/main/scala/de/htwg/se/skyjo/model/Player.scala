package de.htwg.se.skyjo.model

class Player(name: String/*, hand: Hand*/) {
  //def this(name: String) = this(name, Hand(Array.ofDim[Array[Card]](3)))

  override def toString:String = name
}
