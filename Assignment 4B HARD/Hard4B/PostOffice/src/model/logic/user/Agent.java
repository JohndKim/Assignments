package model.logic.user;

import errors.Errors;
import errors.PasswordException;

import static commands.CommandParser.PASSWORD;

/**
 * Class representing an agent inheriting a user
 *
 * @author kayak
 * @version 1.0
 */
public class Agent extends User{

    /**
     * Initializes userID, name, and password
     *
     * @param userID user ID
     * @param name full name
     * @param password password
     */
    public Agent(ID userID, Name name, Password password) {
        super(UserType.AGENT, userID, name, password);
    }

    /**
     * Resets a customer's password
     *
     * @param customer customer
     * @param newPassword new password
     * @throws PasswordException password fails to meet requirements
     */
    public void resetPassword(Customer customer, Password newPassword) throws PasswordException {
        if (!newPassword.toString().matches(PASSWORD)) throw new PasswordException(Errors.PASSWORD_FAIL);
        customer.setPassword(newPassword);
    }
}
