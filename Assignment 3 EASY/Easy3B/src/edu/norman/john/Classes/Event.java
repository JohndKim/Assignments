package edu.norman.john.Classes;

import java.util.Arrays;

/**
 * Class representing an event
 */
public class Event {
    private final Date date;
    private final LectureRoom room;
    // people:guards = 5:1
    private final int[] eventPeople;
    private int numOfPeople = 0;
    private int numOfSecurity = 0;
    private int uniqueID = -1;
    private final int capacity;
    private final String regulation;


    /**
     * Initializes the personID, lecture room, regulation, and date
     *
     * @param personID ID of the person in the event
     * @param roomName name of room
     * @param capacity max num of people
     * @param regulation covid rules (3G vs. 2G)
     * @param date date of event
     */
    public Event(int personID, String roomName, int capacity, String regulation, int date){
        this.capacity = capacity;
        this.eventPeople = initializeArray(new int[capacity]);
        this.date = new Date(date);
        this.room = new LectureRoom(capacity, roomName);
        this.eventPeople[numOfPeople] = personID;
        this.numOfPeople++;
        this.uniqueID = uniqueID++;
        this.regulation = regulation;
    }

    /**
     * Initializes eventPeople array to all -1
     *
     * @param array eventPeople
     * @return -1 array
     */
    public int[] initializeArray(int[] array) {
        Arrays.fill(array, -1);
        return array;
    }

    /**
     * Adds a person to the array
     *
     * @param person to add
     * @return a message
     */
    public String addPerson(Person person){
        if (!checkCertificate(person)) return ErrorMessages.BREAK_REGULATION;
        if (checkSafety()) {
            eventPeople[numOfPeople] = person.getPersonID();
            numOfPeople++;
            return stringSpotsRemaining();
        }
        return ErrorMessages.UNSAFE;
    }

    /**
     * Method to add security
     *
     * @param security security to add
     * @return a message (success or unsuccessful)
     */
    public String addSecurity(Person security){
        numOfSecurity++;
        return addPerson(security);
    }

    /**
     * Checks if it is unsafe
     * @return
     */
    public boolean checkSafety(){
        if (numOfSecurity == 0 && numOfPeople >= 5) return false;
        return (numOfSecurity == 0 || (numOfPeople / numOfSecurity <= 5));
    }

    /**
     * Checks if they are following the regulation
     *
     * @param person to check
     * @return true = following it; false = not following it
     */
    public boolean checkCertificate(Person person){
        boolean notExpired = date.getIntDate() - person.getCertificateDate().getIntDate() < person.getType().getTimeValid();

        if (regulation.equals("3G")) {
            return notExpired && person.getType() == CertificateType.TESTED || person.getType() == CertificateType.RECOVERED || person.getType() == CertificateType.VACCINATED;
        } else {
            return notExpired && person.getType() == CertificateType.RECOVERED || person.getType() == CertificateType.VACCINATED && notExpired;
        }
    }

    /**
     * Gets person ID
     *
     * @return ID
     */
    public int[] getPeopleID() {
        return eventPeople;
    }

    /**
     * Finds remaining space
     *
     * @return message of remaining space
     */
    public String stringSpotsRemaining(){
        int remainingSpots = spotsRemaining();
        return remainingSpots + " spot(s) left";
    }

    /**
     * Calculates remaining space
     *
     * @return number of remaining space
     */
    public int spotsRemaining(){
        return capacity - numOfPeople;
    }

    /**
     * Gets the event date
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }
}
