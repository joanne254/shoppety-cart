package shopping

class Cart(items: List[String]) {

  private def getItemPrice(itemName: String): Double = {
    Fruit.types.find(_.name.toLowerCase == itemName.toLowerCase) match {
      case Some(fruit) => fruit.price
      case None => throw new IllegalArgumentException(s"Item $itemName not recognised.")
    }
  }

  def checkout(): Unit = {
    val totalCost = items.aggregate(0d)(_ + getItemPrice(_), _+_)
    println(f"The total cost of this shopping cart is £$totalCost%.2f.")
  }

}

object Checkout {

  def main(args: Array[String]) : Unit = {
    val validCart = new Cart(List("Orange", "Orange", "apple", "apple"))
    validCart.checkout()

    val badCart = new Cart(List("Orange", "Orangutan"))
    badCart.checkout()
  }

}
