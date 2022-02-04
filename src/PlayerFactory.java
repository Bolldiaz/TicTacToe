/**
 * This factory creates instances of Player implemented classes.
 * @author Matan Dizitser
 */
public class PlayerFactory {

    /**
     * The types name, for the user input check
     */
    public static final String PLAYERS_TYPES = "{human, whatever, clever, snartypamts}";

    /**
     * The type name of the HumanPlayer
     */
    public static final String HUMAN = "human";

    /**
     * The type name of the WhateverPlayer
     */
    public static final String WHATEVER = "whatever";

    /**
     * The type name of the CleverPlayer
     */
    public static final String CLEVER = "clever";

    /**
     * The type name of the SnartypamtsPlayer
     */
    public static final String SNARTYPAMTS = "snartypamts";

    /**
     * Create a Player instance - Human/Whatever/Clever/Snartypamts, according the player-type
     * @param playerType the name specifier for each ClassPlayer
     * @return a Human/Whatever/Clever/Snartypamts instance
     */
    Player buildPlayer(String playerType) {
        switch (playerType) {
            case HUMAN:
                return new HumanPlayer();
            case WHATEVER:
                return new WhateverPlayer();
            case CLEVER:
                return new CleverPlayer();
            case SNARTYPAMTS:
                return new SnartypamtsPlayer();
            default:
                return null;
        }
    }
}
