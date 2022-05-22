package model.logic.player;

/**
 * Represents a player ID
 *
 * @author kayak
 * @version 1.0
 */
public class ID {
    private final int id;

    /**
     * Initializes player ID
     *
     * @param id ID
     */
    public ID(int id) {
        this.id = id;
    }

    public int toInt() {
        return id;
    }
}
