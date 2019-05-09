package de.htwg.se.skyjo.model

case class Hand() {

  var cards: Array[Array[Card]] = Array.ofDim[Card](Hand.ROWS, Hand.COLUMNS)
  var posX: Int = 0;
  var posY: Int = 0;

  for {
      i <- 0 until Hand.ROWS
      j <- 0 until Hand.COLUMNS
  } cards(i)(j) = Card()

  def summarize(): Int = {
    var sum: Int = 0

    for {
      i <- 0 until Hand.ROWS
      j <- 0 until Hand.COLUMNS
    } if (cards(i)(j).isUncovered){
      sum += cards(i)(j).getValue.toInt
    }
      sum
  }

  override def toString: String = {
    val coordsX = Array(2, 5, 8, 11)
    val coordsY = Array(2, 4, 6)
    var ret: String = ""

    var grid = Array(
      Array(' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '\n'),
      Array(' ', '┌', '─', '─', '┬', '─', '─', '┬', '─', '─', '┬', '─', '─', '┐', '\n'),
      Array(' ', '│', '0', '0', '│', '1', '0', '│', '2', '0', '│', '3', '0', '│', '\n'),
      Array(' ', '├', '─', '─', '┼', '─', '─', '┼', '─', '─', '┼', '─', '─', '┤', '\n'),
      Array(' ', '│', '0', '1', '│', '1', '1', '│', '2', '1', '│', '3', '1', '│', '\n'),
      Array(' ', '├', '─', '─', '┼', '─', '─', '┼', '─', '─', '┼', '─', '─', '┤', '\n'),
      Array(' ', '│', '0', '2', '│', '1', '2', '│', '2', '2', '│', '3', '2', '│', '\n'),
      Array(' ', '└', '─', '─', '┴', '─', '─', '┴', '─', '─', '┴', '─', '─', '┘', '\n')
    )

    grid(0)(coordsX(posX)) = '#'
    grid(coordsY(posY))(0) = '#'

    grid.foreach(row => row.foreach(c => ret += c))

    ret
  }

  def move(dir: String): Unit ={

    dir match {
      case _ =>
      case "right" => posX += 1
      case "left" => posX -= 1
      case "up" => posY -= 1
      case "down" => posY += 1
    }

    posX = clamp(posX, 0, Hand.COLUMNS - 1)
    posY = clamp(posY, 0, Hand.ROWS - 1)
  }

  def clamp(value: Int, min: Int, max: Int): Int ={
    if ( value < min) min else if (value > max) max else value
  }
}

object Hand{
  val ROWS = 3
  val COLUMNS = 4
}
