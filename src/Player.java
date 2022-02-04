/**
 * This interface used for the TIC-TAC Toe exercise, ensure each Player implemented class is obligated to
 * playTurn method used for a single turn in TIC-TAC Toe game.
 * @author Matan Dizitser
 */
public interface Player {
    void playTurn(Board board, Mark mark);
}
