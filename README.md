# Java-Tic-Tac-Toe

[![Coverage Status](https://coveralls.io/repos/github/Gabbendorf/Java-Tic-Tac-Toe/badge.svg?branch=master)](https://coveralls.io/github/Gabbendorf/Java-Tic-Tac-Toe?branch=master)

Classic game played in the terminal with noughts (O) and crosses (X).
The player must put 3 or 4 (depending on the size of the grid) marks in a row to win.

## Game options
The player can select one of the following game options:

* player vs. another human player
* player vs. unbeatable computer
* unbeatable computer vs. unbeatable computer.

The player can play:

* on a grid size 3x3
* on a grid size 4x4

## Computer player
Computer player never loses. The worst it can do against a human player is obtaining a draw result.
Two computer against each other will always end up with a tie game.

## How to run the game
You need to have Gradle installed:

```
git clone git@github.com:Gabbendorf/Java-Tic-Tac-Toe.git

cd Java-Tic-Tac-Toe

gradle clean

gradle jar

java -jar build/libs/JavaTicTacToe.jar
```

## How to run the tests

```
gradle clean test
```
