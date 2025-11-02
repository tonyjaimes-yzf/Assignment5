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
