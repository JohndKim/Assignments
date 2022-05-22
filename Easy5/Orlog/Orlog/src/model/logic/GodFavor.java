package model.logic;

import errors.Errors;
import errors.IllegalMoveException;
import errors.SyntaxException;
import model.logic.player.GodPower;
import model.logic.player.Player;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * An enum representing a God's Favor (Thor, Idun, or Thrymr)
 */
public enum GodFavor {
    /**
     * Damages opponent
     */
    THOR("TS", 1, thorLevelCostEffect()) {
        @Override
        public void executeFavor(Player player, Player opponent) {
            int effectOfFavor;

            try {
                effectOfFavor = getEffectOfFavor(player, opponent, thorLevelCostEffect());
            } catch (IllegalMoveException e) {
                return;
            }

            player.getBattleElements().setThorDamage(effectOfFavor);
            opponent.takeThorDamage(player.getBattleElements().getThorDamage());
        }
    },
    /**
     * Heals self
     */
    IDUN("IR", 2, idunLevelCostEffect()) {
        @Override
        public void executeFavor(Player player, Player opponent) {
            int effectOfFavor;

            try {
                effectOfFavor = getEffectOfFavor(player, opponent, idunLevelCostEffect());
            } catch (IllegalMoveException e) {
                return;
            }

            player.heal(effectOfFavor);
        }
    },
    /**
     * Lowers opponent's god favor level
     */
    THRYMR("TT", 0, thrymrLevelCostEffect()) {
        @Override
        public void executeFavor(Player player, Player opponent) {
            int effectOfFavor;

            try {
                effectOfFavor = getEffectOfFavor(player, opponent, thrymrLevelCostEffect());
            } catch (IllegalMoveException e) {
                return;
            }

            player.getBattleElements().setThrymrDebuff(effectOfFavor);
            opponent.takeThrymrDebuff(player.getBattleElements().getThrymrDebuff());
        }
    },
    /**
     * No god favor
     */
    GODLESS("", -1, null) {
        @Override
        public void executeFavor(Player player, Player opponent) {
            // You should not be able to execute anything when godless
        }
    };

    private static final int MAX_LEVEL = 3;
    private static final int EFFECT = 1;
    private final String name;
    private final int orderPriority;
    private final TreeMap<Integer, ArrayList<Integer>> godLevelCostEffect;

    /**
     * Initializes the favor's name
     *
     * @param id name
     */
    GodFavor(String id, int orderPriority, TreeMap<Integer, ArrayList<Integer>> godLevelCostEffect) {
        this.name = id;
        this.orderPriority = orderPriority;
        this.godLevelCostEffect = godLevelCostEffect;
    }


    // I didn't want to hard code this and instead wanted it so that I had only one method that takes the
    // cost and effects multipliers as the parameters T-T

    /**
     * A treemap creating thor's level, costs, and effects
     *
     * @return thor map
     */
    private static TreeMap<Integer, ArrayList<Integer>> idunLevelCostEffect() {
        TreeMap<Integer, ArrayList<Integer>> levelCostEffect = new TreeMap<>();
        for (int i = 0; i <= MAX_LEVEL; i++) {
            ArrayList<Integer> costEffect = new ArrayList<>();
            if (i == 0) { // level 0 = 0 cost and 0 effect
                costEffect.add(0);
                costEffect.add(0);
                levelCostEffect.put(i, costEffect);
                continue;
            }
            costEffect.add(3 * i + 1); // the cost for this level
            costEffect.add(2 * i); // the effect for this level
            levelCostEffect.put(i, costEffect); // i = level
        }
        return levelCostEffect;
    }

    /**
     * A treemap creating idun's level, costs, and effects
     *
     * @return idun map
     */
    private static TreeMap<Integer, ArrayList<Integer>> thorLevelCostEffect() {
        TreeMap<Integer, ArrayList<Integer>> levelCostEffect = new TreeMap<>();
        for (int i = 0; i <= MAX_LEVEL; i++) {
            ArrayList<Integer> costEffect = new ArrayList<>();
            if (i == 0) {
                costEffect.add(0);
                costEffect.add(0);
                levelCostEffect.put(i, costEffect);
                continue;
            }
            costEffect.add(4 * i);
            costEffect.add(3 * i - 1);
            levelCostEffect.put(i, costEffect);
        }
        return levelCostEffect;
    }

    /**
     * A treemap creating thrymr's level, costs, and effects
     *
     * @return thrymr map
     */
    private static TreeMap<Integer, ArrayList<Integer>> thrymrLevelCostEffect() {
        TreeMap<Integer, ArrayList<Integer>> levelCostEffect = new TreeMap<>();
        for (int i = 0; i <= MAX_LEVEL; i++) {
            ArrayList<Integer> costEffect = new ArrayList<>();
            if (i == 0) {
                costEffect.add(0);
                costEffect.add(0);
                levelCostEffect.put(i, costEffect);
                continue;
            }
            costEffect.add(3 * i);
            costEffect.add(i);
            levelCostEffect.put(i, costEffect);
        }
        return levelCostEffect;
    }

    /**
     * Gets the effect of a favor (e.g. how much damage Thor's Strike does)
     *
     * @param player player using god favor
     * @param opponent opponent
     * @param levelCostEffect the levels, costs, and effects of the god favor
     * @return numerical effect
     * @throws IllegalMoveException cost > player's god power (poor)
     */
    private static int getEffectOfFavor(Player player, Player opponent, TreeMap<Integer, ArrayList<Integer>>
            levelCostEffect) throws IllegalMoveException {

        // a player has a hashmap; key: god favor, value: level; this retrieves the level
        int levelOfFavor = player.getGodFavorEnumMap().get(player.getGodFavor());
        int cost = player.getGodFavor().getGodLevelCostEffect() // gets cost from the treemap
                .get(levelOfFavor + opponent.getBattleElements().getThrymrDebuff()).get(0);
        if (cost > player.getGodPower().toInt()) throw new IllegalMoveException(Errors.YOU_ARE_POOR);

        player.setGodPower(new GodPower(player.getGodPower().toInt() - cost));
        return levelCostEffect.get(levelOfFavor).get(EFFECT);
    }

    /**
     * Gets the name of a god favor
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the order priority of a god favor (e.g. thor goes before idun)
     *
     * @return order priority
     */
    public int getOrderPriority() {
        return orderPriority;
    }


    /**
     * Gets the god's level, cost, and effect
     *
     * @return sortedmap
     */
    public SortedMap<Integer, ArrayList<Integer>> getGodLevelCostEffect() {
        return godLevelCostEffect;
    }

    /**
     * Gets a GodFavor favor from the enum when inputted a string matching its name
     *
     * @param id god favor name
     * @return GodFavor favor
     * @throws SyntaxException wrong syntax
     */
    public static GodFavor getGodFavor(String id) throws SyntaxException {
        for (GodFavor favor : GodFavor.values()) {
            if (favor.getName().equalsIgnoreCase(id)) return favor;
        }
        throw new SyntaxException(Errors.WRONG_PARAMETER);
    }

    /**
     * A unique favor for each god to be executed
     *
     * @param player player executing it
     * @param opponent opponent being executed on (maybe)
     */
    public abstract void executeFavor(Player player, Player opponent);
}
