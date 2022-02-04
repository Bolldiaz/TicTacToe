/**
 * The CleverPlayer class, implements the Player interface rules.
 * This player strategy is filling the board column by column from left to right.
 * @author Matan Dizitser
 */
public class CleverPlayer implements Player {

    /**
     * commit a valid turn of the player.
     * @param board the board to play on
     * @param mark symbolizes the player identity
     */
    @Override
    public void playTurn(Board board, Mark mark) {
        int initRow = 0, initCol = 0;
        if (!board.putMark(mark, initRow, initCol)) {
           for (int i=0; i<Board.SIZE; i++) {
               for (int j=0; j<Board.SIZE; j++) {
                   if (board.putMark(mark, initRow +j, initCol +i)) {
                       return;
                   }
               }
           }
       }
    }
}

