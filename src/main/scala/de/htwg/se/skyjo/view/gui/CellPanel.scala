package de.htwg.se.skyjo.view.gui

import de.htwg.se.skyjo.controller.{BoardChanged, ControllerInterface}
import de.htwg.se.skyjo.model.cardComponent.CardInterface

import scala.swing._
import scala.swing.event._

class CellPanel(row: Int, column: Int, player: Int, controller: ControllerInterface) extends FlowPanel {
  val givenCellColor = new Color(255, 255, 255)
  val cellColor = new Color(0, 0, 0)
  val label =
    new Label {
      text = cellText
      font = new Font("Verdana", 1, 32)
    }
  val cell = new BoxPanel(Orientation.Vertical) {
    contents += label
    preferredSize = new Dimension(80, 80)
    background = if (controller.getCardIsUncovered(row, column, player)) givenCellColor else cellColor
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    listenTo(controller)
    reactions += {
      case e: BoardChanged => {
        label.text = cellText
        repaint
      }
      case MouseClicked(src, pt, mod, clicks, pops) => {
        controller.doMove(row, column, player)
        repaint
      }
    }
  }

  def myCell: CardInterface = controller.getCardAsCard(row, column, player)

  def redraw: Unit = {
    contents.clear()
    label.text = cellText
    setBackground(cell)
    contents += cell
    repaint
  }

  def cellText: String = if (controller.getCardIsUncovered(row, column, player)) " " + controller.getCard(row, column, player) else " "

  def setBackground(p: Panel): Unit = p.background = if (controller.getCardIsUncovered(row, column, player)) givenCellColor
  else cellColor
}
