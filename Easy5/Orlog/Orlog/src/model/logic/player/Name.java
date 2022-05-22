package model.logic.player;

/**
 * Class representing a player name
 *
 * @author kayak
 * @version 1.0
 */
public class Name {
    private final String name;

    /**
     * Initializes a player name
     *
     * @param name name
     */
    public Name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
