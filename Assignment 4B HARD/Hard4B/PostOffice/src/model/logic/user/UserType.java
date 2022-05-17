package model.logic.user;

/**
 * An enum representing all the types of users (customer, mailman, agent, or no user)
 *
 * @author kayak
 * @version 1.0
 */
public enum UserType {
    CUSTOMER("customer"),
    MAILMAN("mailman"),
    AGENT("agent"),
    NO_USER("no_user");

    private final String type;

    /**
     * Initializes user type (customer, mailman, agent, or no user)
     * @param type type of user
     */
    UserType(String type){
        this.type = type;
    }
}
