package model.logic.user;

/**
 * Class representing a user's username
 *
 * @author kayak
 * @version 1.0
 */
public class Username {
    private final String userUsername;

    /**
     * Initializes username
     *
     * @param username username
     */
    public Username(String username){
        this.userUsername = username;
    }

    /**
     * Gets the user's username
     *
     * @return username
     */
    public String getUserUsername() {
        return userUsername;
    }

    /**
     * Converts username to string
     *
     * @return string username
     */
    @Override
    public String toString(){
        return userUsername;
    }
}
