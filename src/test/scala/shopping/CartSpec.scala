package shopping

import org.scalatest.{FlatSpec, Matchers}


class CartSpec extends FlatSpec with Matchers {

  "Given a non valid cart" should "throw an error" in {
    val thrown = the [IllegalArgumentException] thrownBy new Cart(List("pineapple")).checkout()
    thrown.getMessage should equal (s"Unknown fruit: pineapple")
    val thrownAgain = the [IllegalArgumentException] thrownBy new Cart(List("pineapple")).checkout(withOffers = false)
    thrownAgain.getMessage should equal (s"Unknown fruit: pineapple")
  }

  "Given an empty cart" should "return a cost of 0" in {
    new Cart(List()).checkout() shouldEqual 0
    new Cart(List()).checkout(withOffers = false) shouldEqual 0
  }

  "Given a cart with one item" should "return the price of that item" in {
    new Cart(List("apple")).checkout(withOffers = false) shouldEqual 0.60
    new Cart(List("aPpLe")).checkout(withOffers = false) shouldEqual 0.60
    new Cart(List("orange")).checkout(withOffers = false) shouldEqual 0.25
    new Cart(List("OrAnGe")).checkout(withOffers = false) shouldEqual 0.25
    new Cart(List("apple")).checkout() shouldEqual 0.60
    new Cart(List("aPpLe")).checkout() shouldEqual 0.60
    new Cart(List("orange")).checkout() shouldEqual 0.25
    new Cart(List("OrAnGe")).checkout() shouldEqual 0.25
  }

  "Given a cart with multiple items, without offers" should "return the cart total" in {
    new Cart(List("Orange", "Orange", "apple", "apple"))
      .checkout(withOffers = false) shouldEqual 0.60 * 2 + 0.25 * 2 +- 1E-10
    new Cart(List("Orange", "Orange", "ORANGE", "apple"))
      .checkout(withOffers = false) shouldEqual 0.60 + 0.25 * 3 +- 1E-10
    new Cart(List("aPPLE", "apple", "apple"))
      .checkout(withOffers = false) shouldEqual 0.60 * 3 +- 1E-10
    new Cart(List("Orange", "Orange", "apple", "apple", "Apple", "Orange", "orange", "ORANGE", "APPLE"))
      .checkout(withOffers = false) shouldEqual 5 * 0.25 + 4 * 0.6 +- 1E-10

  }

  "Given a valid cart" should "return the cart cost after applying \"three for two\" on oranges" in {
    new Cart(List("Orange", "Orange", "Orange", "orange"))
      .checkout() shouldEqual 3 * 0.25
    new Cart(List("Orange", "Orange", "Orange", "orange", "ORANGE"))
      .checkout() shouldEqual 4 * 0.25
    new Cart(List("Orange", "Orange", "Orange", "orange", "ORANGE", "orange"))
      .checkout() shouldEqual 4 * 0.25
  }

  "Given a valid cart" should "return the cart cost after applying \"buy one get one free\" on apples" in {
    new Cart(List("apple", "apple", "Apple"))
      .checkout() shouldEqual 2 * 0.6 +- 1E-10
    new Cart(List("apple", "apple", "Apple", "APPLE"))
      .checkout() shouldEqual 2 * 0.6 +- 1E-10
    new Cart(List("apple", "apple", "Apple", "APPLE", "aPPLE"))
      .checkout() shouldEqual 3 * 0.6 +- 1E-10
  }

  "Given a valid cart" should "return the cart cost after applying all the offers" in {
    new Cart(List("Orange", "Orange", "apple", "apple", "Apple", "Orange", "orange", "ORANGE", "APPLE"))
      .checkout() shouldEqual 4 * 0.25 + 2 * 0.6 +- 1E-10
    new Cart(List("Orange", "Orange", "apple", "orange", "ORANGE", "APPLE"))
      .checkout() shouldEqual 3 * 0.25 + 1 * 0.6 +- 1E-10
    new Cart(List("Orange", "Orange", "orange", "aPPle", "APPLE"))
      .checkout() shouldEqual 2 * 0.25 + 1 * 0.6 +- 1E-10
  }

}
