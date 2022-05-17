package model.logic.user;

/**
 * A class representing a mailman inheriting the user
 *
 * @author kayak
 * @version 1.0
 */
public class Mailman extends User{
    /**
     * Initializes user ID, full name, password
     * @param userID user ID
     * @param name full name
     * @param password password
     */
    public Mailman(ID userID, Name name, Password password) {
        super(UserType.MAILMAN, userID, name, password);
    }
}
