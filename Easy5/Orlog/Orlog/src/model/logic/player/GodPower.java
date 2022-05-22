package model.logic.player;

/**
 * A class representing a player's god power
 *
 * @author kayak
 * @version 1.0
 */
public class GodPower {
    private final int godPower;

    /**
     * Initializes player's god power
     *
     * @param godPower god power
     */
    public GodPower(int godPower) {
        this.godPower = godPower;
    }

    /**
     * Gets a player's god power
     * @return god power
     */
    public int toInt() {
        return godPower;
    }

    @Override
    public String toString() {
        return String.valueOf(godPower);
    }
}
