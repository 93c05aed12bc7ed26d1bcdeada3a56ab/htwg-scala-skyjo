package de.htwg.se.skyjo.model.gameBoardComponent.gameBoardImpl

import com.google.inject.Guice
import de.htwg.se.skyjo.SkyjoModule
import de.htwg.se.skyjo.model.deckComponent.DeckInterface
import de.htwg.se.skyjo.model.gameBoardComponent.GameBoardInterface
import de.htwg.se.skyjo.model.playerComponent.Player

import scala.collection.mutable.ArrayBuffer

class GameBoard extends GameBoardInterface {
  val injector = Guice.createInjector(new SkyjoModule)
  override var deck = injector.getInstance(classOf[DeckInterface])
  override var players: ArrayBuffer[Player] = ArrayBuffer.empty[Player]
  override var turn: Int = 0
}
