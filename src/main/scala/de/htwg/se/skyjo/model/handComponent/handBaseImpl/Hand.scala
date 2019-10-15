package de.htwg.se.skyjo.model.handComponent.handBaseImpl

import de.htwg.se.skyjo.model.cardComponent.cardBaseImpl.Card
import de.htwg.se.skyjo.model.handComponent.HandInterface

case class Hand() extends HandInterface {


  var cards: Array[Array[Card]] = Array.ofDim[Card](Hand.ROWS, Hand.COLUMNS)
  var posX: Int = 0
  var posY: Int = 0

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

  def sumUncovered() : Int = {
    var sumUncovered: Int = 0

    for {
      i <- 0 until Hand.ROWS
      j <- 0 until Hand.COLUMNS
    } if (cards(i)(j).isUncovered){
      sumUncovered += 1
    }
    sumUncovered
  }

  override def toString: String = {
    val curCoordsX = Array(2, 5, 8, 11)
    val curCoordsY = Array(2, 4, 6)
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

    grid(0)(curCoordsX(posX)) = '#'
    grid(curCoordsY(posY))(0) = '#'


    for {
      i <- 0 until Hand.ROWS
      j <- 0 until Hand.COLUMNS
    } {

      val card: String = cards(i)(j).getValue

      var str = "##"

      card match {
        case x if x == "#" =>
        case x if x.toInt >= 0 && x.toInt < 10 => {
          str = "0" + x
        }
        case _ => str = card
      }
      grid(curCoordsY(i))(curCoordsX(j)) = str(0)
      grid(curCoordsY(i))(curCoordsX(j) + 1) = str(1)
    }

    grid.foreach(row => row.foreach(c => ret += c))

    ret
  }

  def move(dir: String): Unit ={

    dir match {
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