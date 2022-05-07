
package edu.norman.john.Classes;

import java.util.Scanner;

/**
 * A class representing a booking system for lectures (events) during the pandemic
 */
public class BookingSystem implements BookingSystemCommands {
    private static final String SUCCESSFUL_COMMAND = "Okay";
    private static final String COLON = ";";
    private final Event[] events;
    private final Person[] people;
    private String[] splitInput;
    private String[] splitAgain;
    private int numOfPeople;
    private int numOfEvents;
    private final Date currentDate;

    /**
     * Constructor that initializes the booking system
     */
    public BookingSystem(){
        this.numOfPeople = 0;
        this.numOfEvents = 0;
        this.currentDate = new Date(0);
        int maxNumOfEvents = 1000;
        this.events = new Event[maxNumOfEvents];
        int maxNumOfPeople = 1000;
        this.people = new Person[maxNumOfPeople];
    }

    /**
     * Method that runs the commands according to user input
     *
     * I didn't want to use switch case, wanted to use enum but I tried and it uhhh >.>;;;
     */
    public void commands() {
        boolean loopCondition = true;
        while (loopCondition) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            splitInput = input.split("\\s+");
            if (splitInput.length > 1) splitAgain = splitInput[1].split(COLON);

            switch (splitInput[0]) { // I TRIED DOING THIS WITH ENUMS BUT WAS SO CONFUSED
                case "set-date":
                    setDate();
                    break;
                case "add-person":
                    addPerson();
                    break;
                case "add-certificate":
                    addCertificate();
                    break;
                case "print-person":
                    printPerson();
                    break;
                case "print-people":
                    printPeople();
                    break;
                case "add-event":
                    addEvent();
                    break;
                case "add-security":
                    addSecurity();
                    break;
                case "book-spot":
                    bookSpot();
                    break;
                case "report-case":
                    reportCase();
                    break;
                case "quit":
                    loopCondition = false;
                    break;
                default:
                    System.out.println(ErrorMessages.INVALID_COMMAND);
            }
        }
    }

    /**
     * Method to set the current date
     */
    @Override
    public void setDate(){
        currentDate.setDate(Integer.parseInt(splitInput[1]));
        System.out.println("Date set to: " + splitInput[1]);
    }

    /**
     * Method to create a person and add it to the people array
     *
     * @param role of the created person
     *
     * Used to supplement addPerson()
     */
    public void createPerson(Role role){
        people[numOfPeople] = new Person(role, new Name(splitAgain[1], splitAgain[2]), numOfPeople);
        System.out.println(numOfPeople); // id
        numOfPeople++;
    }

    /**
     * Method to create and add a person and output their ID
     */
    @Override
    public void addPerson(){
        switch(splitAgain[0]){
            case "student":
                createPerson(Role.STUDENT);
                break;
            case "lecturer":
                createPerson(Role.LECTURER);
                break;
            case "security":
                createPerson(Role.SECURITY);
                break;
            default:
                System.out.println(ErrorMessages.INVALID_ROLE);
        }
    }

    /**
     * Prints the person as a string
     */
    @Override
    public void printPerson(){
        System.out.println(people[Integer.parseInt(splitInput[1])]);
    } // removed toString() here as well

    /**
     * adds a certificate to the person (e.g. tested)
     */
    @Override
    public void addCertificate(){
        // add-certificate personID;3G;date (return OK)
        int personID = Integer.parseInt(splitAgain[0]);
        switch (splitAgain[1]) {
            case "tested":
                people[personID].setType(CertificateType.TESTED);
                System.out.println(SUCCESSFUL_COMMAND);
                break;
            case "recovered":
                people[personID].setType(CertificateType.RECOVERED);
                System.out.println(SUCCESSFUL_COMMAND);
                break;
            case "vaccinated":
                people[personID].setType(CertificateType.VACCINATED);
                System.out.println(SUCCESSFUL_COMMAND);
                break;
            default:
                System.out.println(ErrorMessages.INVALID_CERTIFICATE);
                break;
        }
        people[personID].setCertificateDate(new Date(Integer.parseInt(splitAgain[2]))); // the date they got it
    }

    /**
     * prints people with a specific role
     *
     * @param role to print
     */
    public void printPeopleWithRole(Role role){
        for (Person person : people){
            if (person == null) break;
            if (person.getRole() == role) System.out.println(person);
        }
    }

    /**
     * Takes what specific role to print out and prints it
     */
    @Override
    public void printPeople(){
        switch (splitInput[1]) {
            case "student":
                printPeopleWithRole(Role.STUDENT);
                break;
            case "lecturer":
                printPeopleWithRole(Role.LECTURER);
                break;
            case "security":
                printPeopleWithRole(Role.SECURITY);
                break;
            default:
                System.out.println(ErrorMessages.INVALID_ROLE);
                break;
        }
    }

    /**
     * Method to add an event into the event array
     */
    @Override
    public void addEvent(){
        int lecturerID = Integer.parseInt(splitAgain[0]);
        if (people[lecturerID].getRole() == Role.LECTURER) { // must be a lecturer to create an event
            events[numOfEvents] = new Event(Integer.parseInt(splitAgain[0]), splitAgain[1], Integer.parseInt(splitAgain[2]), splitAgain[3], Integer.parseInt(splitAgain[4]));
            System.out.println(numOfEvents);
            numOfEvents++;
        } else {
            System.out.println(ErrorMessages.NO_PERMISSION);
        }
    }

    /**
     * This adds security to an event
     *
     * @return a string (depends on if the security has already been deployed there or not)
     */
    public String addSecurityOutputMessage(){
        int securityID = Integer.parseInt(splitAgain[1]);
        if (people[securityID].getRole() != Role.SECURITY) return ErrorMessages.NO_PERMISSION; // ensures that the person is actually security
        if (hasBooked(events[Integer.parseInt(splitAgain[0])], securityID)) return ErrorMessages.ALREADY_EXISTS;
        return events[Integer.parseInt(splitAgain[0])].addSecurity(people[securityID]); // addSecurity is a method inside "Events" and also one here
    }

    /**
     * Prints the output message if security has been added or not
     */
    @Override
    public void addSecurity(){
        System.out.println(addSecurityOutputMessage());
    }

    /**
     * Checks if the reported person is in the event
     *
     * @param event the event to check
     * @param checkID the reported ID
     * @return true = person has booked; false = not
     */
    public boolean hasBooked(Event event, int checkID){
        int[] checkIDs = event.getPeopleID();
        for (int potentialID : checkIDs){
            if (potentialID == checkID) return true;
        }
        return false;
    }

    /**
     * Determines which string to return depending on cases
     *
     * @return string message
     */
    public String bookSpotOutputMessage(){
        int eventID = Integer.parseInt(splitAgain[0]);
        int personID = Integer.parseInt(splitAgain[1]);
        Event event = events[eventID];

        if (eventID >= numOfEvents) return ErrorMessages.NONEXISTENT_EVENT;
        if (hasBooked(event, personID)) return ErrorMessages.ALREADY_EXISTS;
        else if (event.spotsRemaining() == 0) return ErrorMessages.NO_SPACE;
        return event.addPerson(people[personID]);
    }

    /**
     * Prints the message
     */
    @Override
    public void bookSpot(){
        System.out.println(bookSpotOutputMessage());
    }

    /**
     * Sorts the contacted IDs from smallest to largest via selection sort (e.g 0,1,2,3)
     *
     * @param list is the list of IDs to sort
     * @return the sorted list
     */
    public int[] sortContacts(int[] list){
        for (int i = 0; i < list.length-1; i++){
            int minIndex = i;
            for (int j = i+1; j < list.length; j++){
                if (list[j] < list[minIndex]){
                    minIndex = j;
                }
            }
            int tmp = list[minIndex];
            list[minIndex] = list[i];
            list[i] = tmp;
        }
        return list;
    }

    /**
     * Creates new list of people in contact with the reported guy
     *
     * @param contacts current people who were in contact with the reported guy
     * @param newContacts a list of new people who were in contact
     * @return a new list with the people in contact
     */
    public int[] getContactIDs(int[] contacts, int[] newContacts){
        int num = 0;
        int[] newContactIDs = resizeArray(contacts, newContacts.length + contacts.length);
        for (int newContact : newContacts) {
            newContactIDs[contacts.length + num] = newContact; // stores the contact ID in the array
            num++;
        }
        return newContactIDs;
    }

    /**
     * Resizes an array
     *
     * @param arr array we want to make bigger
     * @param size array's new size
     * @return the new array
     */
    public int[] resizeArray(int[] arr, int size){
        int[] tmp = new int[size];
        System.arraycopy(arr, 0, tmp, 0, arr.length);
        return tmp;
    }


    /**
     * finds the events that happened in the last 14 days
     *
     * @return events in the last 14 days
     */
    public Event[] findEventsInTheFourteenDays() {
        int maxNumOfEvents = 1000;
        Event[] eventsInTheLastFourteenDays = new Event[maxNumOfEvents];
        int index = 0;

        for (Event event : events) {
            if (event == null) break;
            if (currentDate.getIntDate() - event.getDate().getIntDate() <= 14) {
                eventsInTheLastFourteenDays[index] = event;
                index++;
            }
        }
        Event[] tmp = new Event[index];
        System.arraycopy(eventsInTheLastFourteenDays, 0, tmp, 0, index);

        return tmp;
    }

    /**
     * Finds the events the reported person went to, then finds the people who went to the same events
     *
     * @return an array of integers with the ID of contacted people
     */
    public int[] findContactPeople(){
        int reportedID = Integer.parseInt(splitAgain[0]);
        int[] contactIDs = new int[0];
        Event[] eventsInTheLastFourteenDays = findEventsInTheFourteenDays();

        for (Event event : eventsInTheLastFourteenDays) {
            if (!hasBooked(event, reportedID)) continue; // if the person hasn't booked there
            contactIDs = getContactIDs(contactIDs, event.getPeopleID()); // stores the contact IDs here
        }
        return contactIDs;
        // returns -1, -1, -1, -1, 0, 1, 2, 3 (four -1 must be from initialize array, so its being copied wrong?)
    }

    /**
     * Method to calculate the number of shared events an ID has with the reported ID
     *
     * @param i is the array index (loop)
     * @param contactIDs is the int array with the IDs in contact with the reported ID
     * @return number of shared events
     */
    public int calculateNumOfSharedEvents(int i, int[] contactIDs){
        int numOfSharedEvents = 1;
        for (int j = i+1; j < contactIDs.length; j++){
            if (contactIDs[i] == contactIDs[j])  numOfSharedEvents++;
        }
        return numOfSharedEvents;
    }

    @Override
    public void reportCase(){
        int[] contactIDs = sortContacts(findContactPeople());
        int emptyID = -1;

        for (int i = 0; i < contactIDs.length; i++){
            if (contactIDs[i] == emptyID || contactIDs[i] == Integer.parseInt(splitAgain[0])) continue; // if equal to a non-existent ID OR the reported ID, skip
            int numOfSharedEvents = calculateNumOfSharedEvents(i, contactIDs);
            System.out.println(people[contactIDs[i]] + " [" + numOfSharedEvents + "]"); // removed .toString() from first thing
            i += numOfSharedEvents-1; // ensures we skip parts of the loop to prevent duplicating prints
            }
        }
    }