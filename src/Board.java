/**
 * Represents a TIC Tac Toe game board
 * The board have a certain SIZE and WIN_STREAK constants, each square in the box can be accessed and get
 * marked, in order to put a legal mark, the square must be blank and the square index must be a valid one.
 * At the end of the game, the winner will be declared ( or draw if a defeat hasn't taken place)
 * @author Matan Dizitser
 */
public class Board {

    public static int SIZE = 5;
    public static int WIN_STREAK = 3;

    // Symbolize all the winningCheck legal directions:
    private static final int DOWN = 1;
    private static final int UP = -1;
    private static final int RIGHT = 1;
    private static final int LEFT = -1;
    private static final int STAY_IN_PLACE = 0;

    // enum describes the current status of the game:
    public enum GameStatus { IN_PROGRESS ,DRAW, X_WIN, O_WIN }

    // the board fields:
    private final Mark[][] board;
    private GameStatus gameStatus;
    private int emptyCellsNum;
    

    /**
     * Constructor
     */
    Board() {
        this.board = new Mark[SIZE][SIZE];
        this.gameStatus = GameStatus.IN_PROGRESS;
        emptyCellsNum = SIZE*SIZE;
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = Mark.BLANK;
            }
        }
    }

    /**
     * @param row an integer - symbolize the index row number
     * @param col an integer - symbolize the index col number
     * @return True if row & col both are valid, else false
     */
    private boolean isSquareValid(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    /**
     * put the desired mark on the given index and update the board
     * @param mark the player's mark - X / O
     * @param row an integer - symbolize the index row number
     * @param col an integer - symbolize the index col number
     * @return True if putMark process is legal - legal and blank index
     */
    public boolean putMark(Mark mark, int row, int col) {
        if (!isSquareValid(row, col))
            return false;
        
        if ( board[row][col] != Mark.BLANK ) {
            return false;
        }

        board[row][col] = mark;
        emptyCellsNum--;
        if (!winningMove(mark, row, col) && emptyCellsNum == 0) {
            gameStatus = GameStatus.DRAW;
        }
        return true;
    }

    /**
     * a getter to the given index value
     * @param row an integer - symbolize the index row number
     * @param col an integer - symbolize the index col number
     * @return the index mark if index is valid, else BLANK
     */
    public Mark getMark(int row, int col) {
        if (!isSquareValid(row, col))
            return Mark.BLANK;
        return board[row][col];
    }

    /**
     * calculate the longest legal streak of the mark in the given direction
     * @param row an integer - symbolize the index row number
     * @param col an integer - symbolize the index col number
     * @param rowDelta an integer - 1/-1 symbolize down/up direction move
     * @param colDelta an integer - 1/-1 symbolize right/left direction move
     * @param mark the player's mark - X / O
     * @return the calculated streak
     */
    private int countMarkInDirection(int row, int col, int rowDelta, int colDelta, Mark mark) {
        int count = 0;
        while(row < SIZE && row >= 0 && col < SIZE && col >= 0 && board[row][col] == mark) {
            count++;
            row += rowDelta;
            col += colDelta;
        }
        return count;
    }

    /**
     * check if there is a legal streak in length of WIN_STREAK on the left/right of the given coordinate
     * @param mark the player's mark - X / O
     * @param row an integer - symbolize the index row number
     * @param col an integer - symbolize the index col number
     * @return true if |streak| >= WIN_STREAK, else false
     */
    private boolean horizontalCheck(Mark mark, int row, int col) {
        int rightTimesMark = countMarkInDirection(row, col, STAY_IN_PLACE, RIGHT, mark);
        int leftTimesMark = countMarkInDirection(row, col, STAY_IN_PLACE, LEFT, mark);
        return WIN_STREAK <= rightTimesMark + leftTimesMark - 1;
    }

    /**
     * check if there is a legal streak in length of WIN_STREAK on the up/down of the given coordinate
     * @param mark the player's mark - X / O
     * @param row an integer - symbolize the index row number
     * @param col an integer - symbolize the index col number
     * @return true if |streak| >= WIN_STREAK, else false
     */
    private boolean verticalCheck(Mark mark, int row, int col) {
        int upTimesMark = countMarkInDirection(row, col, UP, STAY_IN_PLACE, mark);
        int downTimesMark = countMarkInDirection(row, col, DOWN, STAY_IN_PLACE, mark);
        return WIN_STREAK <= upTimesMark + downTimesMark - 1;
    }

    /**
     * check if there is a legal streak in length of WIN_STREAK on the both diagonal directions, of the given
     * coordinate
     * @param mark the player's mark - X / O
     * @param row an integer - symbolize the index row number
     * @param col an integer - symbolize the index col number
     * @return true if |streak| >= WIN_STREAK, else false
     */
    private boolean diagonalCheck(Mark mark, int row, int col) {
        int rightDownTimesMark = countMarkInDirection(row, col, DOWN, RIGHT, mark);
        int leftUpTimesMark = countMarkInDirection(row, col, UP, LEFT, mark);
        int rightUpTimesMark = countMarkInDirection(row, col, UP, RIGHT, mark);
        int leftDownTimesMark = countMarkInDirection(row, col, DOWN, LEFT, mark);
        return (WIN_STREAK <= rightDownTimesMark + leftUpTimesMark - 1) || 
               (WIN_STREAK <= rightUpTimesMark + leftDownTimesMark - 1);
    }

    /**
     * check if the given move was a winning one, by a having a WIN_STREAK consecutive marks of the same sign
     * on the board in diagonal/ horizontal/ vertical direction
     * @param mark the player's mark - X / O
     * @param row an integer - symbolize the index row number
     * @param col an integer - symbolize the index col number
     * @return True if the move was a winning one, else False
     */
    private boolean winningMove(Mark mark, int row, int col) {
        if (horizontalCheck(mark, row, col) || verticalCheck(mark, row, col) || diagonalCheck(mark, row, col)) {
            if (mark == Mark.O) {
                gameStatus = GameStatus.O_WIN;
            } else {
                gameStatus = GameStatus.X_WIN;
            }
            return true;
        }
        return false;
    }

    /**
     * check if the gameStatus is one of DRAW / X_WIN / Y_WIN, if so the game has ended
     * @return True if the game ended, else False
     */
    public boolean gameEnded() {
        return gameStatus != GameStatus.IN_PROGRESS;
    }

    /**
     * return if X / O player one the game in case of defeat
     * @return the winner identity - X / O in a case of defeat, and BLANK for draw/in progress
     */
    public Mark getWinner() {
        if (gameStatus == GameStatus.O_WIN) {
            return Mark.O;
        } else if (gameStatus == GameStatus.X_WIN) {
            return Mark.X;
        } else {
            return Mark.BLANK;
        }
    }
}
