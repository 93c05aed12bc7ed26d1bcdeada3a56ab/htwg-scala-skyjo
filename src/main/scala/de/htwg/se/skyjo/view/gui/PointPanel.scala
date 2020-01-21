package de.htwg.se.skyjo.view.gui

import de.htwg.se.skyjo.controller.ControllerInterface

import scala.swing.{BoxPanel, Font, Label, Orientation}

class PointPanel(controller: ControllerInterface) extends BoxPanel(Orientation.Vertical) {

  val label = new Label {
    text = "Punkte"
    font = new Font("Verdana", 1, 32)
  }
  for (i <- 0 until controller.getPlayerListSize()) {
    playerPoints(i) = new Label(pointText(i))
  }
  var playerPoints = Array.ofDim[Label](controller.getPlayerListSize())

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
