package de.htwg.se.skyjo.model

case class Hand() {

  var cards: Array[Array[Card]] = Array.ofDim[Card](3, 4)

  for {
      i <- 0 until 3
      j <- 0 until 4
  } cards(i)(j) = Card()
}
