package model.logic;

import errors.Errors;
import errors.SyntaxException;

import java.util.Arrays;

/**
 * An enum storing all the dice sides
 */
public enum Dice {
    /**
     * Axe side
     */
    AXE("MA", 0, 1, 0, 0, 0, 0),
    /**
     * Helmet side
     */
    HELMET("MD", 0, 0, 1, 0, 0, 0),
    /**
     * God power helmet side
     */
    GOD_POWER_HELMET("GMD", 1, 0, 1, 0, 0, 0),
    /**
     * Bow side
     */
    BOW("RA", 0, 0, 0, 1, 0, 0),
    /**
     * God power bow side
     */
    GOD_POWER_BOW("GRA", 1, 0, 0, 1, 0, 0),
    /**
     * Shield side
     */
    SHIELD("RD", 0, 0, 0, 0, 1, 0),
    /**
     * God power shield side
     */
    GOD_POWER_SHIELD("GRD", 1, 0, 0, 0, 1, 0),
    /**
     * Steal side
     */
    STEAL("ST", 0, 0, 0, 0, 0, 1),
    /**
     * God power steal side
     */
    GOD_POWER_STEAL("GST", 1, 0, 0, 0, 0, 1);


    private final String identifier;
    private final int gPGained;
    private final int axeDMG;
    private final int axeProtection;
    private final int bowDMG;
    private final int bowProtection;
    private final int godPowerStolen;

    /**
     * Initializes id, gp gained, axe damage, axe protection, bow damage, bow protection, and god power stolen
     *
     * @param identifier id
     * @param gPGained gp gained
     * @param axeDMG axe damage
     * @param axeProtection axe protection
     * @param bowDMG bow damage
     * @param bowProtection bow protection
     * @param godPowerStolen god power stolen
     */
    Dice(String identifier, int gPGained, int axeDMG, int axeProtection, int bowDMG, int bowProtection,
         int godPowerStolen) {
        this.identifier = identifier;
        this.gPGained = gPGained;
        this.axeDMG = axeDMG;
        this.axeProtection = axeProtection;
        this.bowDMG = bowDMG;
        this.bowProtection = bowProtection;
        this.godPowerStolen = godPowerStolen;
    }

    /**
     * Gets id
     * @return id
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets axe damage
     * @return axe damage
     */
    public int getAxeDMG() {
        return axeDMG;
    }

    /**
     * Gets bow damage
     * @return bow damage
     */
    public int getBowDMG() {
        return bowDMG;
    }

    /**
     * Gets gp gained
     * @return gp gained
     */
    public int getGPGained() {
        return gPGained;
    }

    /**
     * Gets axe protection
     * @return axe protection
     */
    public int getAxeProtection() {
        return axeProtection;
    }

    /**
     * Gets bow protection
     * @return bow protection
     */
    public int getBowProtection() {
        return bowProtection;
    }

    /**
     * Gets gp stolen
     * @return gp stolen
     */
    public int getGodPowerStolen() {
        return godPowerStolen;
    }

    /**
     * Gets the Dice side with matching id
     *
     * @param id id
     * @return Dice
     * @throws SyntaxException wrong id
     */
    public static Dice getRoll(String id) throws SyntaxException {
        return Arrays.stream(Dice.values()).filter(i -> i.getIdentifier().equals(id))
                .findFirst().orElseThrow(() -> new SyntaxException(Errors.INVALID_IDENTIFIER));
    }
}
