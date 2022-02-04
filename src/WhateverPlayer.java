import java.util.Random;

/**
 * The WhateverPlayer class, implements the Player interface rules.
 * This player strategy is filling the board randomly.
 * @author Matan Dizitser
 */
public class WhateverPlayer implements Player {
    private final Random rand = new Random();

    /**
     * commit a valid turn of the player.
     * @param board the board to play on
     * @param mark symbolizes the player identity
     */
    @Override
    public void playTurn(Board board, Mark mark) {

        int randomRow = rand.nextInt(Board.SIZE);
        int randomCol = rand.nextInt(Board.SIZE);
        while (!board.putMark(mark, randomRow, randomCol)) {
            randomRow = rand.nextInt(Board.SIZE);
            randomCol = rand.nextInt(Board.SIZE);
        }
    }
}
