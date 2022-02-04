/**
 * The Game Board class is a generic platform to manage a one game session on a given board with given
 * players.
 * In this version, the implementaion is restricted to a specific version of TIC Tac Toe.
 * After a game instance is created, it could get run using the run method which manage all the game
 * session by the board rules.
 * @author Matan Dizitser
 */
public class Game {
    private final Renderer renderer;
    private final Player playerX;
    private final Player playerO;

    /**
     * Constructor
     * @param renderer print the game board
     * @param playerX the player of the X moves
     * @param playerO the player of the O moves
     */
    Game(Renderer renderer, Player playerX, Player playerO) {
        this.renderer = renderer;
        this.playerX = playerX;
        this.playerO = playerO;
    }

    /**
     * run moves from both sides, each turn is flipped from one player to another.
     * the run is ended after one defeat the other, or a draw took place
     * @return the winner identity - X / 0, if draw took place - BLANK
     */
    public Mark run() {
        Board board = new Board();
        while (!board.gameEnded()) {
            renderer.renderBoard(board);
            playerX.playTurn(board, Mark.X);
            if (board.gameEnded()) {
                break;
            }
            renderer.renderBoard(board);
            playerO.playTurn(board, Mark.O);
        }
        renderer.renderBoard(board);
        return board.getWinner();
    }
}
