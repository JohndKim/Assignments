package model.logic.player;

/**
 * A class representing a player's health
 *
 * @author kayak
 * @version 1.0
 */
public class Health {
    private final int maxHp;
    private int hp;

    /**
     * Initializes a player's health and max health
     *
     * @param hp health
     */
    public Health(int hp) {
        this.hp = hp;
        this.maxHp = hp;
    }

    /**
     * Increases health
     * @param health health
     */
    public void changeHealth(int health) {
        this.hp += health;
    }

    /**
     * Sets health
     * @param hp health
     */
    public void setHp(int hp) {
        this.hp = hp;
    }

    /**
     * Gets player's health
     *
     * @return player health
     */
    public int toInt() {
        return hp;
    }

    /**
     * Gets player's max hp
     * @return max hp
     */
    public int getMaxHp() {
        return maxHp;
    }

    @Override
    public String toString() {
        return String.valueOf(hp);
    }
}
