package shopping

class Cart(items: List[String]) {

  val cartItems: CartItems = countItems()

  private def getFruit(itemName: String): Fruit = {
    Fruit.types.find(_.name.toLowerCase == itemName.toLowerCase) match {
      case Some(fruit) => fruit
      case None => throw new IllegalArgumentException(s"$itemName isn't a fruit sold here.")
    }
  }

  private def countItems(): CartItems =
    items.foldLeft(Map[String, Int]().withDefaultValue(0))((acc, itemName) => {
      val fruit = getFruit(itemName)
      acc.updated(fruit.name, acc(fruit.name) + 1)
    })

  private def getItemPrice(itemName: String): Price =
    getFruit(itemName).price

  def checkout(withOffers: Boolean=true): Price = {
    val totalCost = items.aggregate(0d)(_ + getItemPrice(_), _+_)
    if (!withOffers)
      return totalCost

    val updatedCost = Fruit.applyFruitOffers(totalCost, cartItems)
    updatedCost
  }

}

object Checkout {

  def main(args: Array[String]) : Unit = {

    val cart = new Cart(args.toList)
    val costWithoutOffers = cart.checkout(withOffers = false)
    val costWithOffers = cart.checkout()

    println(f"The total cost of this shopping cart is £$costWithOffers%.2f.")
    println(f"Without the offers, it would have been £$costWithoutOffers%.2f.")
  }

}
