package de.htwg.se.skyjo.model.deckComponent

import de.htwg.se.skyjo.model.cardComponent.CardInterface
import de.htwg.se.skyjo.model.deckComponent.deckBaseImpl.Deck

import scala.collection.mutable.{ArrayStack, ListBuffer}

trait DeckInterface {

  val discardPile: ArrayStack[CardInterface]

  var cards: ListBuffer[CardInterface]

  def shuffle(): Deck

  def drawCard(): Unit

  def returnCard(): Unit

  def drawHand(): Array[Array[CardInterface]]

  def sumCardInDeck(): Int

  def discardPileTopCard(): Int
}
