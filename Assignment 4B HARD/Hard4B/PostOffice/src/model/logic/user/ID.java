package model.logic.user;

/**
 * Class representing a unique user ID
 *
 * @author kayak
 * @version 1.0
 */
public class ID {
    private final String userID;

    /**
     * Initializes user ID
     * @param userID user ID
     */
    public ID(String userID){
        this.userID = userID;
    }

    /**
     * Converts userID to String
     *
     * @return userID
     */
    @Override
    public String toString(){
        return String.valueOf(userID);
    }
}
