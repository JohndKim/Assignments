package edu.norman.john.Classes;

/**
 * Enum representing the roles a person can have
 */
public enum Role {
    STUDENT("student"), // can visit
    LECTURER("lecturer"), // can hold an avent
    SECURITY("security"); // protecc da students

    private final String role;

    /**
     * Initializes role
     *
     * @param role of person
     */
    Role(String role){
        this.role = role;
    }

    /**
     * Gets the person's role
     *
     * @return role
     */
    public String getRole() {
        return role;
    }
}
