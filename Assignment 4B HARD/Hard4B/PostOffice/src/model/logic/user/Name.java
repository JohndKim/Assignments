package model.logic.user;

/**
 * Represents the full name of a user
 *
 * @author kayak
 * @version 1.0
 */
public class Name {
    private final String firstName;
    private final String lastName;

    /**
     * Initializes first name and last name
     *
     * @param firstName first name
     * @param lastName last name
     */
    public Name(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Gets first name of the user
     *
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Gets last name of the user
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
}
