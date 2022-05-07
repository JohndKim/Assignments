package edu.norman.john.Classes;

/**
 * Class representing a person's name
 */
public class Name {
    private final String fname;
    private final String lname;

    /**
     * Initializes the first and last name
     *
     * @param fname first name
     * @param lname last name
     */
    public Name(String fname, String lname){
        this.fname = fname;
        this.lname = lname;
    }

    /**
     * Converts name to string
     * @return full name
     */
    @Override
    public String toString(){
        return fname + " " + lname;
    }
}
