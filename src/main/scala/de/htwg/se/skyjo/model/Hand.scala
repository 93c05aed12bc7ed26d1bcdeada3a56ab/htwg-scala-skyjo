package de.htwg.se.skyjo.model

case class Hand() {

  var cards = Array.ofDim[Card](3,4)

  var card1 = Card()
  var card2 = Card()
  var card3 = Card()
  var card4 = Card()
  var card5 = Card()
  var card6 = Card()
  var card7 = Card()
  var card8 = Card()
  var card9 = Card()
  var card10 = Card()
  var card11 = Card()
  var card12 = Card()

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
