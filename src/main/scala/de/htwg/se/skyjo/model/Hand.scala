package de.htwg.se.skyjo.model

case class Hand() {

  val row: Int = 3
  val column: Int = 4

  var cards: Array[Array[Card]] = Array.ofDim[Card](row, column)

  for {
      i <- 0 until 3
      j <- 0 until 4
  } cards(i)(j) = Card()

  def summarize(): Int = {
    var sum: Int = 0

    for {
      i <- 0 until 3
      j <- 0 until 4
    } if (cards(i)(j).isUncovered){
      sum += cards(i)(j).getValue.toInt
    }
      sum
  }
}
