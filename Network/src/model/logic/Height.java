package model.logic;

/**
 * Class representing a tree height
 *
 * @author kayak
 * @version 1.0
 */
public class Height {
    private int treeHeight;
    private int maxHeight;

    /**
     * Initializes tree and max height to 0
     */
    public Height() {
        this.treeHeight = 0;
        this.maxHeight = 0;
    }

    /**
     * Gets tree height
     *
     * @return tree height
     */
    public int getTreeHeight() {
        return treeHeight;
    }

    /**
     * Gets max height
     *
     * @return max height
     */
    public int getMaxHeight() {
        return maxHeight;
    }

    /**
     * Sets max height
     *
     * @param maxHeight max height
     */
    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    /**
     * Increases tree height
     */
    public void increaseTreeHeight() {
        treeHeight++;
    }

    /**
     * Decreases tree height
     */
    public void decreaseTreeHeight() {
        treeHeight--;
    }

    /**
     * Checks if tree height is greater than max height
     *
     * @return true = greater; false = less than
     */
    public boolean greaterThanMax() {
        return treeHeight > maxHeight;
    }
}
