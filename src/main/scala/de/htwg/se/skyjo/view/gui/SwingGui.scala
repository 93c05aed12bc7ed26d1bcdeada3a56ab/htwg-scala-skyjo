package de.htwg.se.skyjo.view.gui

import de.htwg.se.skyjo.controller._

import scala.swing.Swing.LineBorder
import scala.swing._
import scala.swing.event._

class SwingGui(controller: ControllerInterface) extends Frame {

  playerpanel.visible = true

  def startGui: Unit = {
    var cells = Array.ofDim[CellPanel](3, 4, controller.getPlayerListSize())
    var buttonDiscardPile: Button = Button(controller.getDiscardPileTop) {
      controller.trade = true
    }
    listenTo(controller)

    title = "HTWG Skyjo"
    preferredSize = new Dimension(600, 600)

    var statusline = new TextField("Du bist an der Reihe: " + controller.getPlayerTurnString, 30)

    contents = new BorderPanel {
      add(highlightpanel, BorderPanel.Position.North)
      add(gridPanel, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.South)
      //TODO punkte EAST
    }

    menuBar = new MenuBar {
      contents += new Menu("File") {
        mnemonic = Key.F
        contents += new MenuItem(Action("New") {
          controller.newGame()
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
          controller.load
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
      case event: CandidatesChanged => redraw
      case event: NewRound => newRound
      case event: GameOver => newGame
      case event: Shutdown => System.exit(0)
    }

    def redraw: Unit = {
      buttonDiscardPile.text = controller.getDiscardPileTop

      for {
        row <- 0 until 3
        column <- 0 until 4
        player <- 0 until controller.getPlayerListSize()
      } cells(row)(column)(player).redraw

      statusline.text = "Du bist an der Reihe: " + controller.getPlayerTurnString

      repaint
    }

    def newRound: Unit = {
      controller.newRound
    }

    def newGame: Unit = {
      //TODO statt println soll ein popup kommen
      //TODO erneute frage wie viele spieler
      println("Glückwunsch " + controller.getWinnerString + "! Du hast Gewonnen!")
      playerpanel.visible
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
      val buttonDeck = Button("#") {
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
            val cellPanel = new CellPanel(innerRow, innerColumn, outerRow, controller)
            cells(innerRow)(innerColumn)(outerRow) = cellPanel
            contents += cellPanel
            listenTo(cellPanel)
          }
        }
      }
    }
  }

  def playerpanel: Frame = new Frame() {
    title = "How many Players will play?"
    preferredSize = new Dimension(280, 80)


    val textNumPlayers = new TextField("Wie viele Spieler sollen erstellt werden?", 30)
    val buttonNumPlayers = Button("Erstelle Spieler") {
      controller.newGame()
      for (i <- 0 until textNumPlayers.text.toInt) {
        controller.createPlayer("player" + i) //TODO Name vom spieler eingeben
      }

      startGui
    }
    listenTo(buttonNumPlayers)

    contents = new BorderPanel {
      add(textNumPlayers, BorderPanel.Position.North)
      add(buttonNumPlayers, BorderPanel.Position.Center)
    }
  }


}
