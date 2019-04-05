package de.htwg.se.skyjo.model

case class Hand() {

  var cards = Array.ofDim[Card](3,4)

  var card1 = Card(1)
  var card2 = Card(2)
  var card3 = Card(3)
  var card4 = Card(4)
  var card5 = Card(5)
  var card6 = Card(6)
  var card7 = Card(7)
  var card8 = Card(8)
  var card9 = Card(9)
  var card10 = Card(10)
  var card11 = Card(11)
  var card12 = Card(12)

  cards(0)(0) = card1
  cards(0)(1) = card2
  cards(0)(2) = card3
  cards(0)(3) = card4

  cards(1)(0) = card5
  cards(1)(1) = card6
  cards(1)(2) = card7
  cards(1)(3) = card8

  cards(2)(0) = card9
  cards(2)(1) = card10
  cards(2)(2) = card11
  cards(2)(3) = card12

}
