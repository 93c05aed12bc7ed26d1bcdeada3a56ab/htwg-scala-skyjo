case class Cell(x:Int, y:Int)

val cell1 = Cell(4,5)
cell1.x
cell1.y

case class Field(cells: Array[Cell])

val field1 = Field(Array.ofDim[Cell](1))
field1.cells(0)=cell1
field1.cells(0).x
field1.cells(0).y

case class Card(value: Int)

val card1 = Card(5)

case class Hand(cards: Array[Card])

val hand = Hand(Array.ofDim[Card](1))
hand.cards(0) = card1
hand.cards(0).value

case class Player(name: String, hand: Hand)

val player = Player("Hans", hand)

player.name
player.hand.cards(0).value