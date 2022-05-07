package edu.norman.john.Classes;

/**
 * Class representing date
 */
public class Date {
    // 0-364
    private int currentDate;

    /**
     * Constructor for the date
     * @param date
     */
    public Date(int date){
        this.currentDate = date;
    }

    /**
     * Gets int date
     *
     * @return int date
     */
    public int getIntDate() {
        return currentDate;
    }

    /**
     * sets the date
     *
     * @param date date
     */
    public void setDate(int date) {
        this.currentDate = date;
    }
}
