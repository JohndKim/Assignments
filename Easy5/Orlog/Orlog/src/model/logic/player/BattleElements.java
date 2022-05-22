package model.logic.player;

import errors.Errors;

import errors.PlayerException;
import errors.SyntaxException;
import model.logic.Dice;

import java.util.List;

import static model.logic.Dice.getRoll;

/**
 * A class representing a player's battle elements (what the character rolls)
 *
 * @author kayak
 * @version 1.0
 */
public class BattleElements {
    private int axeDamage;
    private int axeProtection;
    private int bowDamage;
    private int bowProtection;
    private int godPowerGained;
    private int godPowerStolen;
    private int thorDamage;
    private int thrymrDebuff;

    /**
     * Resets all rolls
     */
    public BattleElements() {
        resetBE();
    }

    /**
     * Changes battle elements based on what the player rolled
     *
     * @param ids IDs of what the player rolled on the dice
     * @throws SyntaxException invalid dice ID entered
     * @throws PlayerException can't gain more than 4 GP in a single roll
     */
    public void changeBattleElements(List<String> ids) throws SyntaxException, PlayerException {
        resetBE();
        for (String id : ids) {
            Dice dice = getRoll(id);

            axeDamage += dice.getAxeDMG();
            axeProtection += dice.getAxeProtection();
            bowDamage += dice.getBowDMG();
            bowProtection += dice.getBowProtection();
            godPowerStolen += dice.getGodPowerStolen();
            godPowerGained += dice.getGPGained();
        }

        if (godPowerGained + godPowerStolen > 4) {
            resetBE();
            throw new PlayerException(Errors.INVALID_PLAYER_BUILD);
        }
    }

    private void resetBE() {
        this.axeDamage = 0;
        this.axeProtection = 0;
        this.bowDamage = 0;
        this.bowProtection = 0;
        this.godPowerGained = 0;
        this.godPowerStolen = 0;
        this.thorDamage = 0;
        this.thrymrDebuff = 0;
    }

    /**
     * Gets axe damage
     * @return axe damage
     */
    public int getAxeDamage() {
        return axeDamage;
    }

    /**
     * Gets axe protection
     * @return axe protection
     */
    public int getAxeProtection() {
        return axeProtection;
    }

    /**
     * Gets bow damage
     * @return bow damage
     */
    public int getBowDamage() {
        return bowDamage;
    }

    /**
     * Gets bow protection
     * @return bow protection
     */
    public int getBowProtection() {
        return bowProtection;
    }

    /**
     * Gets god power gained
     * @return god power gained
     */
    public int getGodPowerGained() {
        return godPowerGained;
    }

    /**
     * Gets god power stolen
     * @return god power stolen
     */
    public int getGodPowerStolen() {
        return godPowerStolen;
    }

    /**
     * Gets thor damage
     * @return thor damage
     */
    public int getThorDamage() {
        return thorDamage;
    }

    /**
     * gets thrymr debuff
     * @return thrymr debuff
     */
    public int getThrymrDebuff() {
        return thrymrDebuff;
    }

    /**
     * Sets thor damage
     *
     * @param thorDamage thor damage
     */
    public void setThorDamage(int thorDamage) {
        this.thorDamage = thorDamage;
    }

    /**
     * Sets thrymr debug
     * @param thrymrDebuff thrymr debuff
     */
    public void setThrymrDebuff(int thrymrDebuff) {
        this.thrymrDebuff = thrymrDebuff;
    }
}
