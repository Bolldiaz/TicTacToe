/**
 * The CleverPlayer class, implements the player rules.
 * This player strategy is:
 * if WIN_STREAK > SIZE/2:
 *      1. filling the middle row from left to right.
 *      2. filling the middle column from up to down.
 *      3. filling randomly.
 * else:
 *      1. fill all the (WIN_STREAK * i, 1) squares (for every legal i > 0)
 *      2. filling the up-right diagonal of (WIN_STREAK, 1)
 *      3. filling randomly.
 * @author Matan Dizitser
 */
public class SnartypamtsPlayer implements Player {
    /**
     * Use WhateverPlayer instance, for randomize filling
     */
    private final Player whatever = new WhateverPlayer();

    /**
     * commit a valid turn of the player.
     * @param board the board to play on
     * @param mark symbolizes the player identity
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        if (Board.WIN_STREAK > Board.SIZE / 2) {
            int initRow = Board.SIZE / 2;
            int initCol = Board.SIZE / 2 - 1;

            // filling the middle row:
            for (int i = 0; i < Board.SIZE; i++) {
                if (board.putMark(mark, initRow, i)) {
                    return;
                }
            }

            // filling the middle column:
            for (int i = 0; i < Board.SIZE; i++) {
                if (board.putMark(mark, i, initCol)) {
                    return;
                }
            }

            // filling randomly:
            whatever.playTurn(board, mark);

        }
        else {

            int initRow = Board.WIN_STREAK-1;
            int initCol = 0;

            // all the (WIN_STREAK * i, 1) squares
            for (int i=0; i < Board.SIZE / Board.WIN_STREAK; i++)
                if (board.putMark(mark, initRow + i*Board.WIN_STREAK, initCol))
                    return;

            // up-right diagonal of (WIN_STREAK, 1)
            for (int i=0; i<Board.WIN_STREAK; i++)
                if (board.putMark(mark, initRow-i, initCol+i))
                    return;

            // filling randomly:
            whatever.playTurn(board, mark);
        }
    }
}

