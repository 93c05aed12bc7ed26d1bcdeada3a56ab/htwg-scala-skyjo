package de.htwg.se.skyjo.model.gameBoardComponent

import de.htwg.se.skyjo.model.deckComponent.DeckInterface
import de.htwg.se.skyjo.model.playerComponent.Player

import scala.collection.mutable.ArrayBuffer

trait GameBoardInterface {

  var deck: DeckInterface
  var players: ArrayBuffer[Player]
  var turn: Int

}
