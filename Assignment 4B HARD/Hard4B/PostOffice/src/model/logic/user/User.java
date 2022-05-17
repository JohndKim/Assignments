package model.logic.user;

/**
 * A parent class representing a user
 *
 * @author kayak
 * @version 1.0
 */
public abstract class User {
    protected UserType userType;
    protected ID userID;
    private final Name name;
    protected Password password;
    private boolean isLoggedIn;

    /**
     * Initializes user type (customer, mailman, agent)
     *
     * @param userType type of user
     * @param userID unique ID
     * @param name full name
     * @param password password
     */
    User(UserType userType, ID userID, Name name, Password password){
        this.userType = userType;
        this.isLoggedIn = false;
        this.userID = userID;
        this.name = name;
        this.password = password;
    }

    /**
     * Checks if arg ID equals to customer ID
     *
     * @param id ID to check
     * @return true = equal; false = unequal
     */
    public boolean checkID(ID id){
        return userID.toString().equals(id.toString());
    }

    /**
     * Checks if arg ID and password equal the customer's one
     *
     * @param personID ID to check
     * @param pw password to check
     * @return true = equal; false = unequal
     */
    public boolean checkIDAndPass(String personID, String pw){
        return userID.toString().equals(personID) && password.toString().equals(pw);
    }
    public ID getID() {
        return userID;
    }

    public Name getName() {
        return name;
    }

    public Password getPassword() {
        return password;
    }
    public void setPassword(Password password) {
        this.password = password;
    }
    public boolean getIsLoggedIn(){
        return isLoggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
    public UserType getUserType() {
        return userType;
    }
}
