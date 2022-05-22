package model.logic.player;

import errors.*;
import model.logic.*;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static commands.CommandParser.PRINT_SEPARATOR;

/**
 * A class representing a player
 *
 * @author kayak
 * @version 1.0
 */
public class Player {
    private final Name name;
    private final ID id;
    private final Health health;
    private GodPower godPower;
    private final BattleElements battleElements;
    private final EnumMap<GodFavor, Integer> godFavor; // type of god favor and the favor's level
    private boolean hasRolled;
    private boolean hasChosenGodFavor;
    private boolean alive;

    /**
     * Initializes a player's name, id, health, and god power
     *
     * @param name name
     * @param id id
     * @param health health
     * @param godPower god power
     */
    public Player(Name name, ID id, Health health, GodPower godPower) {
        this.name = name;
        this.id = id;
        this.health = health;
        this.godPower = godPower;
        this.godFavor = initializeGodFavor();
        this.battleElements = new BattleElements();
        this.hasRolled = false;
        this.hasChosenGodFavor = false;
        this.alive = true;
    }

    /**
     * Heals the player (for Idun god favor)
     *
     * @param hp hp to heal
     */
    public void heal(int hp) {
        health.changeHealth(hp);
        if (health.toInt() > health.getMaxHp()) health.setHp(health.getMaxHp());
    }

    /**
     * Takes axe and bow damage from an opponent according to the roll phase
     *
     * @param axeDamage axe damage
     * @param bowDamage bow damage
     */
    public void takeAxeBowDamage(int axeDamage, int bowDamage) {
        takeAxeDamage(axeDamage);
        takeBowDamage(bowDamage);
    }

    /**
     * Take thor damage (thor favor)
     *
     * @param damage damage
     */
    public  void takeThorDamage(int damage) {
        health.changeHealth(-1 * damage);
        if (health.toInt() <= 0) alive = false;
    }

    /**
     * Take thrymr debuff (lowers favor level)
     *
     * @param levelChange amount to decrease god favor level
     */
    public void takeThrymrDebuff(int levelChange) {
        godFavor.replace(getGodFavor(), Math.max(godFavor.get(getGodFavor()) - levelChange, 0));
    }

    /**
     * Changes god power
     *
     * @param addGodPower god power to add (or subtract)
     */
    public void changeGodPower(int addGodPower) {
        godPower = new GodPower(godPower.toInt() + addGodPower);
    }

    /**
     * Calculates what a player rolled in the roll phase
     *
     * @param ids of the dice sides
     * @throws SyntaxException wrong id
     * @throws IllegalMoveException already rolled
     * @throws PlayerException invalid player build
     */
    public void calculateBattleElements(List<String> ids) throws SyntaxException, IllegalMoveException, PlayerException
    {
        if (hasRolled) throw new IllegalMoveException(Errors.ALREADY_ROLLED);

        battleElements.changeBattleElements(ids);
        hasRolled = true;
    }

    /**
     * Choose a god favor
     *
     * @param id god id
     * @param level god favor level
     * @throws SyntaxException wrong id
     * @throws IllegalMoveException already chose a god
     */
    public void chooseGod(String id, int level) throws SyntaxException, IllegalMoveException {
        if (hasChosenGodFavor) throw new IllegalMoveException(Errors.ALREADY_CHOSEN);
        if (level < 0 || level > 3) throw new IllegalMoveException(Errors.INVALID_LEVEL);

        godFavor.remove(getGodFavor());
        godFavor.put(GodFavor.getGodFavor(id), level);
        hasChosenGodFavor = true;
    }

    /**
     * Resets booleans
     */
    public void resetBooleans() {
        hasRolled = false;
        hasChosenGodFavor = false;
    }

    /**
     * Gets player info
     *
     * @return player info
     */
    public String getPlayerInfo() {
        return name + PRINT_SEPARATOR + health + PRINT_SEPARATOR + godPower;
    }

    /**
     * Gets name
     *
     * @return name
     */
    public Name getName() {
        return name;
    }

    /**
     * Gets id
     *
     * @return id
     */
    public ID getId() {
        return id;
    }

    /**
     * Gets god power
     *
     * @return god power
     */
    public GodPower getGodPower() {
        return godPower;
    }

    /**
     * Sets god power
     *
     * @param godPower new god power
     */
    public void setGodPower(GodPower godPower) {
        this.godPower = godPower;
    }

    /**
     * Gets god favor map
     *
     * @return god favor map
     */
    public Map<GodFavor, Integer> getGodFavorEnumMap() {
        return godFavor;
    }

    /**
     * Gets the god favor
     *
     * @return god favor
     */
    public GodFavor getGodFavor() {
        for (GodFavor gf : GodFavor.values()) {
            if (godFavor.containsKey(gf)) return gf;
        }
        return null;
    }

    /**
     * Get if the player has rolled
     *
     * @return true = rolled; false = has not
     */
    public boolean getHasRolled() {
        return hasRolled;
    }

    /**
     * Gets if the player has chosen
     *
     * @return true = chosen; false = has not
     */
    public boolean getHasChosenGodFavor() {
        return hasChosenGodFavor;
    }

    /**
     * Gets battle elements
     *
     * @return battle elements
     */
    public BattleElements getBattleElements() {
        return battleElements;
    }

    /**
     * Gets if player is alive
     *
     * @return true = alive; false = dead
     */
    public boolean isAlive() {
        return alive;
    }


    // PRIVATE METHODS


    private EnumMap<GodFavor, Integer> initializeGodFavor() {
        EnumMap<GodFavor, Integer> initGodFavor = new EnumMap<>(GodFavor.class);
        initGodFavor.put(GodFavor.GODLESS, null);
        return initGodFavor;
    }

    private void takeAxeDamage(int damage) {
        if (battleElements.getAxeProtection() - damage >= 0) return;

        health.changeHealth(battleElements.getAxeProtection() - damage);
        if (health.toInt() <= 0) alive = false;
    }

    private void takeBowDamage(int damage) {
        if (damage * (-1) - battleElements.getBowProtection() <= 0) return;

        health.changeHealth(damage * (-1) - battleElements.getBowProtection());
        if (health.toInt() <= 0) alive = false;
    }

}
