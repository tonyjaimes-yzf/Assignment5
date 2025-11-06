Part 1

For part 1, I wrote up 4 specification based tests to test the specified behavior of the Barnes and Nobles Project

I was able to achieve 100% class, method, line coverage. I also managed to get 75% 9/12 branch coverage. These tests mainly focused on the logic of placing an order for a book and the functionality of the book purchase process.
I wanted to ensure that my spec tests:
- Returned null if the order was null
- Successfully returned the correct purchase price based on book price and quantity of books purchased
- Properly handled an out of stock order where a customer attempted to buy a higher quantity of a book than what was in stock. It will only charge for the quantity that was in stock.
- Successfully returned the correct purchase price of an order with multiple individual books.

My structural based tests then needed to cover the missed 3 branches. To be specific, I needed to test the branches of the equals() method in the Book class.
I was able to test both branches of:
if (o == null || getClass() != o.getClass()) return false;
But I was not able to figure out how to test both branches of:
if (this == o) return true;
I tried writing up several different tests to test the "false" branch, but I was not able to figure it out. In the end, I managed to get 91% 11/12 branch coverage.

-------------

Part 2
[![SE333_CI](https://github.com/tonyjaimes-yzf/Assignment5/actions/workflows/SE333_CI.yml/badge.svg)](https://github.com/tonyjaimes-yzf/Assignment5/actions/workflows/SE333_CI.yml)
I had a lot of failed attempts on getting my workflow to function as noted by my multiple commits. The workflow would not trigger. I managed to get it triggered and successfully run, but then I came across the issue of not getting any artifacts to generate.
I went ahead and fixed it. The fix allowed the workflow to procduce the checkstyle artifact. I then also fixed it further so the Jacoco artifact was produced aswell. The build badge above shows that my workflow is working correctly.

-----------

Part 3 

Same routine as part 1 for AmazonUnitTest. In my specification based tests, I tested for a null/ empty order, a successful order, an order with multiple items in it. There are 4 different criteria based on number of ites that effect what the total cost of delivery will be:
-0 items
-1-3 items
-4-10 items
-11+ items

I tested each of these criteria ensuring that the delivery cost increases accordingly to the number of items. Overall, my spec tests reached 75% class coverage, 39% method coverage, 41% line coverage, 62% branch coverage.

This allows me to build my structural tests to get better coverage. I made pretty simple tests. A name getter to get an item name, a test that ensures an item is properly added and stored in the shopping cart, and a test that ensures there is no extra cost for an electronic item.
This allowed me to get to 75% class coverage, 46% method coverage, 45% line coverage, and 66% branch coverage.
For the logic handling the delivery cost, I was not able to figure out how to get the if statements in line 15 and 17 to equal false. It's hard to explain, but making one of the conditions false would automatically make the other one true. I tested all possible conditions, but in the end I missed 2 branches. The rest of the missing coverage is from the unimplemented AmazonIntegrationTest which I will explain below

For the AmazonIntegrationTest, I kept the same structure as my AmazonUnitTest. I found I was able to get good coverage with less extensive tests in a way. I properly set up the Database before each test and cleaned up properly after each test. I tested for an empty cart, a successful cart, a successful order, and a mixed item order, just like I did for the Unit tests. This gave me good coverage on my Database and ShoppingCartAdaptor.
My structural test,I tested the numberOfItItems() method in the ShoppingCartAdaptor class. 

These tests gave me the best coverage I could achieve in my AmazonIntegrationTest. The only things that I struggled with was with the Database class. Specifically, I was not able to get the connection to = null, which caused me to miss 2 branch coverage. Also, I was not able to throw a RunTimeException which caused me to miss a line coverage. I think there is a solution to these through more intensive testing and test methods, but I was not able to get around it. Overall, I got as close to full coverage as possible.

