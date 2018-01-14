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

  def checkout(): Unit = {
    val totalCost = items.aggregate(0d)(_ + getItemPrice(_), _+_)
    println(f"The total cost of this shopping cart is £$totalCost%.2f.")

    val updatedCost = Fruit.applyFruitOffers(totalCost, cartItems)
    println(f"After applying offers, the cost of the shopping cart is now £$updatedCost%.2f.")
  }

}

object Checkout {

  def main(args: Array[String]) : Unit = {
    val validCart = new Cart(List("Orange", "Orange", "apple", "apple"))
    validCart.checkout()

    val biggerCart = new Cart(List("Orange", "Orange", "apple", "apple", "Apple", "Orange", "orange", "ORANGE", "APPLE"))
    biggerCart.checkout()

//    val badCart = new Cart(List("Orange", "Orangutan"))
//    badCart.checkout()
  }

}
