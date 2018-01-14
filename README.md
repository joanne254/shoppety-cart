# shoppety-cart
_A very basic shopping cart implementation in Scala_ :apple: :orange:

Step 1: Shopping cart
* Checkout system for a shop that only sells apples (60p) and oranges (25p).
* Take a list of items as strings and return the shopping cart total cost.

Step 2: Simple offers
* Add two offers to the total cost calculation:
  * Apples: Buy one, get one free
  * Oranges: 3 for the price of 2
  
My solution is very simple, although it does use some traits, objects and generic functions. This is to help updates to the code, such as adding fruits or offers.

Some simple improvements that could be made:
* Nicely printing cart contents (`toString` method to the `cartItems` type),
* Having an `applyOffers` function for each fruit, so they don't have to be hardcoded in the `Fruit` object,
* Applying offers while calculating the total cost (decided against here in favour of readibility, and to follow the implementation steps for this test).
