package de.htwg.se.skyjo.model

case class Player(name: String) {

  //var hand: Hand = Hand(Array.ofDim[Array[Card]](3))
  var hand: Hand = Hand()

  override def toString:String = name


  def printHand:String = {

    String.format(s"${hand(0)(0).value}, ${hand(0)(1).value, $hand(0)(2).value, $hand(0)(3).value\n" +
      s"$hand(1)(0).value, $hand(1)(1).value, $hand(1)(2).value, $hand(1)(3).value\n" +
      s"$hand(2)(0).value, $hand(2)(1).value, $hand(2)(2).value, $hand(2)(3).value\n")

    ret
    /*
    var str = ""

    str.format("%s %s %s %s\n%s %s %s %s\n%s %s %s %s",
      hand.cards(0)(0), hand.cards(0)(1), hand.cards(0)(2), hand.cards(0)(3),
      hand.cards(1)(0), hand.cards(1)(1), hand.cards(1)(2), hand.cards(1)(3),
      hand.cards(2)(0), hand.cards(2)(1), hand.cards(2)(2), hand.cards(2)(3)
    )
    str
    */
     */
  }

  def test:String = {
    "test123"
  }

}
