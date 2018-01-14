package shopping

object Fruit {
  val types = List(Orange, Apple)
}

trait Fruit
{
  val name: String
  val price: Double
}

object Orange
  extends Fruit
{
  val name = "Orange"
  val price = 0.25
}

object Apple
  extends Fruit
{
  val name = "Apple"
  val price = 0.60
}
