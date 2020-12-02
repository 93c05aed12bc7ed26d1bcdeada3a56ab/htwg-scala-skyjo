package de.htwg.se.skyjo.aview.gui

import de.htwg.se.skyjo.controller._

import scala.io.Source
import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event._

class SwingGui(controller: ControllerInterface) extends Frame {

  playerpanel.visible = true

  def startGui: Frame = new Frame() {

    var cells = Array.ofDim[CellPanel](controller.getPlayerListSize(), 3, 4)

    var pointPanel: PointPanel = new PointPanel(controller)

    var buttonDiscardPile: Button = Button(controller.getDiscardPileTop) {
      controller.trade = true
    }
    listenTo(controller)

    title = "HTWG Skyjo"
    preferredSize = new Dimension(700, 900)

    var statusline = new TextField("Du bist an der Reihe: " + controller.getPlayerTurnString, 30)

    contents = new BorderPanel {
      add(highlightpanel, BorderPanel.Position.North)
      add(gridPanel, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.South)
      add(pointPanel, BorderPanel.Position.East)
    }

    menuBar = new MenuBar {
      contents += new Menu("File") {
        mnemonic = Key.F
        contents += new MenuItem(Action("New") {
          newGame
        })
        contents += new MenuItem(Action("Quit") {
          controller.shutdown
        })
      }
      contents += new Menu("Edit") {
        mnemonic = Key.E
        contents += new MenuItem(Action("Undo") {
          controller.undo
        })
        contents += new MenuItem(Action("Redo") {
          controller.redo
        })
        contents += new MenuItem(Action("Save") {
          controller.save
        })
        contents += new MenuItem(Action("Load") {
          controller.load(Source.fromFile("gameBoard.json").getLines.mkString)
        })

      }
      contents += new Menu("Cheats") {
        mnemonic = Key.S
        contents += new MenuItem(Action("Uncover All") {
          controller.uncoverAll
        })
      }
    }

    visible = true
    redraw


    reactions += {
      case event: BoardChanged => redraw
      case event: NewRound => newRound
      case event: GameOver => newGame
      case event: Shutdown => System.exit(0)
    }

    def redraw: Unit = {
      buttonDiscardPile.text = controller.getDiscardPileTop
      //TODO wieso auch immer beim click ist cells nur noch 1 player groß
      for {
        player <- 0 until controller.getPlayerListSize()
        row <- 0 until 3
        column <- 0 until 4
      } {
        cells(player)(row)(column).redraw
      }
      pointPanel.redraw
      statusline.text = "Du bist an der Reihe: " + controller.getPlayerTurnString

      repaint
    }

    def newRound: Unit = {
      controller.newRound
      repaint
    }

    def newGame: Unit = {
      if (controller.winner != -1) {
        Dialog.showMessage(contents.head, "Glückwunsch " + controller.getWinnerString + "! Du hast Gewonnen!", title = "We have a Winner!")
      }
      playerpanel.visible = true
      close()
    }

    def highlightpanel: FlowPanel = new FlowPanel {
      contents += new Label("Ablagestapel:")
      buttonDiscardPile = Button(controller.getDiscardPileTop) {
        controller.trade = true
      }
      buttonDiscardPile.preferredSize_=(new Dimension(30, 30))
      contents += buttonDiscardPile
      listenTo(buttonDiscardPile)

      contents += new Label("Deck:")
      var buttonDeck = Button("#") {
        controller.drawCard
      }
      buttonDeck.preferredSize_=(new Dimension(30, 30))
      contents += buttonDeck
      listenTo(buttonDeck)
    }

    def gridPanel: GridPanel = new GridPanel(controller.getPlayerListSize(), 1) {
      border = LineBorder(java.awt.Color.BLACK, 2)
      //TODO eventuel nicht nur eine zeile sondern mehr
      for {
        outerRow <- 0 until controller.getPlayerListSize()
        outerColumn <- 0 until 1
      } {
        contents += new GridPanel(3, 4) {
          border = LineBorder(java.awt.Color.BLACK, 2)
          for {
            innerRow <- 0 until 3
            innerColumn <- 0 until 4
          } {
            var cellPanel = new CellPanel(innerRow, innerColumn, outerRow, controller)
            cells(outerRow)(innerRow)(innerColumn) = cellPanel
            contents += cellPanel
            listenTo(cellPanel)
          }
        }
      }
    }

  }


  def playerpanel: Frame = new Frame() {
    title = "Wie viele Spieler wollen spielen?"
    preferredSize = new Dimension(320, 80)


    var textNumPlayers = new TextField("Gebe die Namen der Spieler ein. (Mit ',' getrennt)", 30)
    var buttonNumPlayers = Button("Erstelle Spieler") {
      var playerArray = textNumPlayers.text.split(",")
      controller.newGame()

      for (i <- 0 until playerArray.length) {
        controller.createPlayer(playerArray(i))
      }
      startGui
      close()
    }
    listenTo(buttonNumPlayers)

    contents = new BorderPanel {
      add(textNumPlayers, BorderPanel.Position.North)
      add(buttonNumPlayers, BorderPanel.Position.Center)
    }
  }


}
