package edu.norman.john.Classes;

/**
 * Class storing various error messages
 */
public class ErrorMessages {
    /**
     * Error message for an invalid command
     */
    public static final String INVALID_COMMAND = "This is an invalid command, try again";
    /**
     * Error message for an invalid role
     */
    public static final String INVALID_ROLE = "You have entered an invalid role";
    /**
     * Error message for an invalid certificate
     */
    public static final String INVALID_CERTIFICATE = "You have entered an invalid certificate";
    /**
     * Error message for a person trying to carry out a command beyond their permissions
     */
    public static final String NO_PERMISSION = "Your role lacks the permissions to carry out that command";
    /**
     * Error message trying to add someone already part of an event
     */
    public static final String ALREADY_EXISTS = "You are already in this event";
    /**
     * Error message for a non-existent event
     */
    public static final String NONEXISTENT_EVENT = "This event does not exist";
    /**
     * Error message for trying to add someone to a fully booked event
     */
    public static final String NO_SPACE = "This event is fully booked";
    /**
     * Error message if they don't follow the covid regulation
     */
    public static final String BREAK_REGULATION = "You don't meet the covid regulations";

    /**
     * Error message if the event is not safe enough (at least 5:1 student:security ratio)
     */
    public static final String UNSAFE = "You need one security guard for every 5 people in the event";

    /**
     * Constructor to prevent making objects
     */
    private ErrorMessages() throws IllegalAccessException{
        throw new IllegalStateException("Utility-class Constructor");
    }

}
