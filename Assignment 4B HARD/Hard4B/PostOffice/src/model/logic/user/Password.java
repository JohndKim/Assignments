package model.logic.user;

/**
 * Class representing a user's password
 *
 * @author kayak
 * @version 1.0
 */
public class Password {
    private final String userPassword;

    /**
     * Initializes password
     *
     * @param userPassword password
     */
    public Password(String userPassword){
        this.userPassword = userPassword;
    }

    /**
     * Converts password to String
     *
     * @return password
     */
    @Override
    public String toString(){
        return userPassword;
    }
}
