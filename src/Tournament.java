import java.util.Scanner;

/**
 * The Tournament class is a generic platform to manage a series of game sessions on a given board with given
 * players.
 * In this version, the implementation is restricted to a specific version of TIC Tac Toe.
 * After a Tournament instance is created, it could get run using the playTournament method which run all
 * the game sessions, and declare the winner of it.
 * @author Matan Dizitser
 */
public class Tournament {
    /* ********************************************** const *********************************************** */
    /**
     * Number of players to play the Tournament.
     */
    private static final int PLAYERS_NUM = 2;

    /**
     * A usage error message of the main run.
     */
    private static final String USAGE_ERROR_MESSAGE = "Error - Invalid usage, please follow the pattern:\n" +
    "java Tournament [round count] [render target:console/none] [player: human/clever/whatever/...] X 2";

    /**
     * A rounds number error message of the main run.
     */
    private static final String ROUNDS_NUMBER_ERROR = "Error - Invalid rounds count, must be an integer " +
                                                      "greater then 0";

    /**
     * A renderer-type error message of the main run.
     */
    private static final String RENDERER_TYPE_ERROR = "Error - Invalid renderer-type, please enter a valid" +
            " option: ";

    /**
     * A player-type error message of the main run.
     */
    private static final String PLAYER_TYPE_ERROR = "Error - Invalid player-type, please enter a valid" +
            " option: ";

    /* ********************************************* fields *********************************************** */
    private int rounds;
    private Renderer renderer;
    private Player[] players = new Player[PLAYERS_NUM];

    /* **************************************** methods/constructor *************************************** */

    /**
     * constructor
     * @param rounds number of rounds to play
     * @param renderer the renderer of the Tournament
     * @param players the players to play it.
     */
    Tournament(int rounds, Renderer renderer, Player[] players) {
        this.rounds = rounds;
        this.renderer = renderer;
        System.arraycopy(players, 0, this.players, 0, PLAYERS_NUM);
    }

    /**
     * The main method of Tournament, run all the game sessions, at the of each session, declares the
     * current result.
     */
    public void playTournament() {
        int firstWinsCount = 0 , secondWinsCount = 0, drawsCount=0;
        for (int i=0; i<rounds ; i++) {

            Game game = new Game(renderer, players[i % 2], players[(i + 1) % 2]);
            Mark winnerMark = game.run();
            if (winnerMark == Mark.X) {
                if (i % 2 == 0) {
                    firstWinsCount++;
                } else {
                    secondWinsCount++;
                }
            } else if (winnerMark == Mark.O) {
                if (i % 2 == 0) {
                    secondWinsCount++;
                } else {
                    firstWinsCount++;
                }
            } else {
                drawsCount++;
            }
            System.out.printf("=== player 1: %d | player 2: %d | Draws: %d ===\r",
                              firstWinsCount, secondWinsCount, drawsCount);
        }
    }


    /* *********************************************** main *********************************************** */
    /**
     * the main method, the mechanical process which check input validity, initiate the Tournament process
     * and runs it.
     */
    public static void main(String[] argv) {
        Scanner scanner = new Scanner(System.in);

        if (argv.length != 4) {
            System.err.printf("%s%n", USAGE_ERROR_MESSAGE);
            return;
        }

        // rounds number validity check
        int rounds = Integer.parseInt(argv[0]);
        while (rounds < 1) {
            System.err.printf("%s%n", ROUNDS_NUMBER_ERROR);
            rounds = scanner.nextInt();
            scanner.nextLine();
        }

        // renderer validity check:
        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderer = rendererFactory.buildRenderer(argv[1]);
        while (renderer == null) {
            System.err.printf("%s%s%n", RENDERER_TYPE_ERROR, RendererFactory.RENDERERS_TYPES);
            String renderer_type = scanner.nextLine();
            renderer = rendererFactory.buildRenderer(renderer_type);
        }

        // players validity check:
        PlayerFactory playerFactory = new PlayerFactory();
        Player player1 = playerFactory.buildPlayer(argv[2]);
        while (player1 == null) {
            System.err.printf("%s%s%n", PLAYER_TYPE_ERROR, PlayerFactory.PLAYERS_TYPES);
            String player_type = scanner.nextLine();
            player1 = playerFactory.buildPlayer(player_type);
        }

        Player player2 = playerFactory.buildPlayer(argv[3]);
        while (player2 == null) {
            System.err.printf("%s%s%n", PLAYER_TYPE_ERROR, PlayerFactory.PLAYERS_TYPES);
            String player_type = scanner.nextLine();
            player2 = playerFactory.buildPlayer(player_type);
        }
        Player[] players = {player1, player2};

        // Initiate & run the Tournament
        Tournament tournament = new Tournament(rounds, renderer, players);
        tournament.playTournament();
    }
}
