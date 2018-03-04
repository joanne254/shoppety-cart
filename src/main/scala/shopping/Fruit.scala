package shopping

object Fruit {
  val types = List(Orange, Apple)

  def applyFruitOffers(cost: Double, items: CartItems): Double = {
    var updatedCost = cost
    if (items.contains(Apple.name))
      updatedCost = Apple.buyOneGetOneFree(updatedCost, items(Apple.name))
    if (items.contains(Orange.name))
      updatedCost = Orange.threeForTwo(updatedCost, items(Orange.name))
    updatedCost
  }
}

trait Fruit
{
  val name: String
  val price: Price
}

object Orange
  extends Fruit
{
  val name = "Orange"
  val price = 0.25

  def threeForTwo(cost: Double, numberOranges: Int): Double =
    cost - (numberOranges / 3) * price
}

object Apple
  extends Fruit
{
  val name = "Apple"
  val price = 0.60

  def buyOneGetOneFree(cost: Double, numberApples: Int): Double =
    cost - (numberApples / 2) * price
}
