import java.util.Scanner;

/**
 * The HumanPlayer class, implements the Player interface rules.
 * This player asks for an input from the user and use it to commit a valid move in the
 * board game, restricted to the board rules.
 * @author Matan Dizitser
 */
public class HumanPlayer implements Player {
    /**
     * An informative message about the invalid numeric values for indexes were entered.
     */
    private static final String INVALID_NUMERIC_INPUT = "Please enter two consecutive digits: " +
            "<digit1><digit2>.";

    /**
     * An informative message about the invalid square indexes were entered.
     */
    private static final String INVALID_SQUARE_INDEXES =
            "Each coordinate must be blank and in range of: 1 < <digit> <= %s%n";

    /**
     * get an input from the user, and ensure it's 2 consecutive digits
     * @return an array of 2 integers - contain the 2 valid digits
     */
    private static int[] getPairOfDigits() {
        Scanner in = new Scanner(System.in);
        while (!in.hasNextInt()) {
            System.out.println(INVALID_NUMERIC_INPUT);
            in.nextLine();
        }
        int number =  in.nextInt();
        int[] rowCol = new int[2];
        rowCol[0] = number / 10 - 1;
        rowCol[1] = number % 10 - 1;

        return rowCol;
    }

    /**
     * commit a valid turn, to make a move it's asks for valid input of the user.
     * @param board the board to play on
     * @param mark symbolizes the player identity
     */
    @Override
    public void playTurn(Board board, Mark mark) {

        int[] index = getPairOfDigits();
        boolean flag = board.putMark(mark, index[0], index[1]);
        while (!flag) {
            System.out.printf(INVALID_SQUARE_INDEXES, Board.SIZE);
            index = getPairOfDigits();
            flag = board.putMark(mark, index[0], index[1]);
        }
    }
}
