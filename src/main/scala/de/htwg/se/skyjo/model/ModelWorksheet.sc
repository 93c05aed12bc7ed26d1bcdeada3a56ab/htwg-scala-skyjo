
case class Card(value: Int)

val card1 = Card(1)
val card2 = Card(2)
val card3 = Card(3)
val card4 = Card(4)
val card5 = Card(5)
val card6 = Card(6)
val card7 = Card(7)
val card8 = Card(8)
val card9 = Card(9)

case class Hand(cards: Array[Array[Card]])

val hand = Hand(Array.ofDim[Card](3, 4))
hand.cards(0) = Array(card1, card2, card3)
hand.cards(1) = Array(card4, card5, card6)
hand.cards(2) = Array(card7, card8, card9)

case class Player(name: String, hand: Hand)

val player = Player("Hans", hand)
player.name
player.hand.cards(0)(0).value
player.hand.cards(0)(1).value
player.hand.cards(0)(2).value
player.hand.cards(1)(0).value
player.hand.cards(1)(1).value
player.hand.cards(1)(2).value
player.hand.cards(2)(0).value
player.hand.cards(2)(1).value
player.hand.cards(2)(2).value

