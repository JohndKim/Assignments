package model.logic.user;

import errors.Errors;
import errors.MailException;
import errors.SyntaxException;
import model.logic.mail.Mail;
import model.logic.mail.PostalServices;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * A subclass representing a customer, inheriting the User class
 *
 * @author kayak
 * @version 1.0
 */
public class Customer extends User{

    private static final String SEPARATOR = "; ";
    private static final String NEW_LINE = "\n";
    private static final int STARTING_NUM_OF_MAIL = 0;
    private final Username username;
    private final TreeMap<String, Integer> mail;
    private final LinkedList<Mail> receivedMail;
    private final LinkedList<PostalServices> services;

    /**
     * Initializes userID, name, password, and username
     *
     * @param userID their userID
     * @param name their full name
     * @param password their password
     * @param username their username
     */
    public Customer(ID userID, Name name, Password password, Username username) {
        super(UserType.CUSTOMER, userID, name, password);
        this.username = username;
        this.mail = initializeMail();
        this.receivedMail = new LinkedList<>();
        this.services = new LinkedList<>();
    }

    /**
     * Adds an element (service) to the list
     * @param service type of mail service
     */
    public void addService(PostalServices service){
        services.add(service);
    }

    /**
     * Adds mail to list
     *
     * @param newMail mail to add
     */
     public void addReceivedMail(Mail newMail){
         mail.merge(newMail.getMailType().getName(), 1, Integer::sum);
         receivedMail.add(newMail);
     }

    /**
     * Removes the most recent mail
     *
     * @throws MailException no mail to remove
     */
     public void getMail() throws MailException {
        if (receivedMail.isEmpty()) throw new MailException(Errors.NO_MAIL);
        mail.merge(receivedMail.pollLast().getMailType().getName(), -1, Integer::sum);
     }

    /**
     * Converts mail list to string
     * @return mail list
     */
     public String mailToString() throws MailException {
         StringBuilder list = new StringBuilder();
         for (Map.Entry<String, Integer> entry : mail.entrySet()){ // for each entry in mail
             if (entry.getValue() == 0) continue;
             list.append(entry.getKey()).append(SEPARATOR).append(entry.getValue()).append(NEW_LINE);
         }
         if (list.length() == 0) throw new MailException(Errors.NO_MAIL);
         list.deleteCharAt(list.length()-1);
         return list.toString();
     }

    /**
     * Calculates the amount of money due from sending mail
     *
     * @return money due
     * @throws SyntaxException wrong syntax
     * @throws MailException no mail sent (no money owed)
     */
    public String listPrice() throws SyntaxException, MailException {
        TreeMap<String, Integer> mailList = new TreeMap<>();
        StringBuilder list = new StringBuilder();

        for (PostalServices service : services){ // creates treemap with the appropriate key-values
            mailList.merge(service.getName(), 1, Integer::sum);
        }

        for (Map.Entry<String, Integer> entry : mailList.entrySet()){ // converts treemap to a string and adds the price to the string
            double price = PostalServices.getPostalService(entry.getKey()).getPrice() * entry.getValue();
            list.append(entry.getKey()).append(SEPARATOR).append(entry.getValue()).append(SEPARATOR).append(String.format("%.2f", price)).append(NEW_LINE);
        }

        if (list.length() == 0) throw new MailException(Errors.NO_MAIL_SENT);
        list.deleteCharAt(list.length()-1);
        return list.toString();
    }

    /**
     * Checks if entered username is same as this customer's username
     *
     * @param name username
     * @return true = equal; false = unequal
     */
    public boolean checkUsername(Username name){
        return username.toString().equals(name.toString());
    }

    /**
     * Checks if username and password match this customer
     *
     * @param name username
     * @param pw password
     * @return true = equal; false = unequal
     */
    public boolean checkNameAndPass(String name, String pw){
        return (username.toString().equals(name) && password.toString().equals(pw));
    }

    /**
     * Returns if username equals ID
     *
     * @return true = matches; false = doesn't match
     */
    public boolean usernameEqualsID(){
        return username.toString().equals(userID.toString());
    }

    /**
     * Gets all the services of mail sent
     *
     * @return services of mail sent
     */
    public List<PostalServices> getServices() {
        return services;
    }

    /**
     * Initializes the treemap of mail with: key: service, value: 0
     *
     * @return treemap of mail
     */
    private TreeMap<String, Integer> initializeMail(){
        TreeMap<String, Integer> mailList = new TreeMap<>();
        for (PostalServices service : PostalServices.values()) mailList.put(service.getName(), STARTING_NUM_OF_MAIL);
        // THIS ISN'T WORKING IDK WHY, it isn't setting a value to them
        return mailList;
    }
}
