# Poker hands
## Problem 54 ([Resource](https://projecteuler.net/problem=54))

---
### Technologies:
* Java 18
* Maven
* Junit 5

### How the solution works:

Main idea:
* Input is read from txt file (input.txt)  
* Run main program in Main class  
* Output is shown in console window

Diagram:
<p align="center">
  <img src="PokerDiagram.png" alt="Solution of Poker hands diagram"/>
</p>

---
### OOP ideas:
* abstraction 
  * interface
* encapsulation
  * private members
* polymorphism
  * multiple implementations of HandType interface
* aggregation
  * Match -> 2 Hands
  * Hand -> 5 Cards
  * no circular dependencies
* composition
  * Combined class is created from 2 other hand type classes
---
### What I like and do not like about my solution:
#### Like:
* ranks can be compared as numbers, so no need for rank-specific logic to know which hand wins
* no need to write specific logic for every rank, most of the code only deals with `HandType` interface
* no royal flush rank
#### Not like:
* it was difficult to test parsing from `List<List<Card>>` to `List<Match>` without any getters to expose card lists
* should probably split card lines into separate hands in `InputParser`, not in `PlayPoker`
* too many rank classes
* `HandType` could have been an abstract class to not duplicate `getRank` and `getHighestCardValue` method implementations
---
### New to me:
* poker rules
* some Stream features
* `Comparator.comparing`, `Collections.singletonList` classes and methods
* Java has record classes, like case classes in Scala
