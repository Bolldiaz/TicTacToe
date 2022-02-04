**TicTacToe**

-----------------------------
-  Implementation details
-----------------------------

Choosing the clever strategy:
This player strategy is filling the board column by column from left to right.

Implementing the whatever strategy:
This player strategy is filling the board randomly, by choosing randomly a blank square on uniform
distribution.

-----------------------------
- Game Result
-----------------------------

Games ran using Board.SIZE=6 and Board.WIN_STREAK=4:

java Tournament 500 none whatever whatever
=== player 1: 242 | player 2: 255 | Draws: 3 ===

java Tournament 500 none whatever clever
=== player 1: 34 | player 2: 466 | Draws: 0 ===

java Tournament 500 none whatever snartypamts
=== player 1: 26 | player 2: 474 | Draws: 0 ===

java Tournament 500 none clever clever
=== player 1: 250 | player 2: 250 | Draws: 0 ===

java Tournament 500 none clever snartypamts
=== player 1: 0 | player 2: 500 | Draws: 0 ===

java Tournament 500 none snartypamts snartypamts
=== player 1: 251 | player 2: 244 | Draws: 5 ===

java Tournament 10000 none whatever whatever
=== player 1: 5056 | player 2: 4894 | Draws: 50 ===
