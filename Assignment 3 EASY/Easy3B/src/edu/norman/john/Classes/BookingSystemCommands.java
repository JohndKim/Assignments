package edu.norman.john.Classes;

/**
 * Interface for a booking system
 */
public interface BookingSystemCommands {
    /**
     * Sets the current date
     */
    void setDate();

    /**
     * Adds a person to the booking system
     */
    void addPerson();

    /**
     * Adds a certificate to a person
     */
    void addCertificate();

    /**
     * Prints a person's info
     */
    void printPerson();

    /**
     * Prints people with a certain role
     */
    void printPeople();

    /**
     * Adds an event to the booking system
     */
    void addEvent();

    /**
     * Adds security to an event
     */
    void addSecurity();

    /**
     * Adds a person to an event
     */
    void bookSpot();

    /**
     * Reports a case and prints out people who have been in the same events as them in the last 14 days
     */
    void reportCase();
}
