package de.htwg.se.skyjo.aview.gui

import de.htwg.se.skyjo.controller.ControllerInterface

import scala.swing.{BoxPanel, Font, Label, Orientation}

class PointPanel(controller: ControllerInterface) extends BoxPanel(Orientation.Vertical) {

  val label = new Label {
    text = "Punkte"
    font = new Font("Verdana", 1, 32)
  }
  var playerPoints = Array.ofDim[Label](controller.getPlayerListSize())
  for (i <- 0 until controller.getPlayerListSize()) {
    playerPoints(i) = new Label(pointText(i))
  }

  def redraw: Unit = {

    contents.clear()
    contents += label
    for (i <- 0 until controller.getPlayerListSize()) {
      playerPoints(i).text = pointText(i)
      contents += playerPoints(i)
    }
    repaint
  }

  def pointText(player: Int): String = controller.getPlayerName(player) + ": " + controller.getPlayerPoints(player)

}
