package de.htwg.se.skyjo.model.deckComponent

import de.htwg.se.skyjo.model.cardComponent.cardBaseImpl.Card
import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck

trait DeckInterface {
  def shuffle(): Deck

  def drawCard(): Unit

  def returnCard(): Unit

  def drawHand(): Array[Array[Card]]

  def sumCardInDeck(): Int

  def discardPileTopCard(): Int
}
