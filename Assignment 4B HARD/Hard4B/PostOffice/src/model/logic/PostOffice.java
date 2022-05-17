package model.logic;

import errors.*;
import model.logic.mail.Mail;
import model.logic.mail.PostalServices;
import model.logic.user.*;

import java.util.*;

import static commands.CommandParser.SUCCESS_WITH_MESSAGE;

/**
 * A class representing a post office
 *
 * @author kayak
 * @version 1.0
 */
public class PostOffice {
    private static final boolean LOGGED_IN = true;
    private static final boolean LOGGED_OUT = false;
    private final HashSet<Customer> customers;
    private final HashSet<Mailman> mailmen;
    private final HashSet<Agent> agents;
    private UserType currentUser;

    /**
     * Initializes customers, mailmen, agents, and current user
     */
    public PostOffice() {
        this.customers = new HashSet<>();
        this.mailmen = new HashSet<>();
        this.agents = new HashSet<>();
        this.currentUser = UserType.NO_USER;
    }

    /**
     * Executes the add-customer command
     * Adds a new customer to the post office
     *
     * @param customer customer to add
     * @return "okay"
     * @throws ExistException if customer already exists in the system
     * @throws LogException if someone is using the system rn
     * @throws AddUserException couldn't add the user
     */
    public String addCustomer(Customer customer) throws ExistException, LogException, AddUserException {
        // checks for exceptions
        addPersonCheck(customer, customers);
        // adds customer
        customers.add(customer);
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Executes the add-mailman command
     * Adds a new mailman to the post office
     *
     * @param mailman mailman to add
     * @return "okay"
     * @throws ExistException if mailman already exists in the system
     * @throws LogException if someone is using the system rn
     * @throws AddUserException couldn't add the user
     */
    public String addMailman(Mailman mailman) throws ExistException, LogException, AddUserException {
        addPersonCheck(mailman, mailmen);
        mailmen.add(mailman);
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Executes the add-agent command
     * Adds a new agent to the post office
     *
     * @param agent agent to add
     * @return "okay"
     * @throws ExistException if agent already exists in the system
     * @throws LogException if someone is using the system rn
     * @throws AddUserException couldn't add the user
     */
    public String addAgent(Agent agent) throws ExistException, LogException, AddUserException {
        addPersonCheck(agent, agents);
        agents.add(agent);
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Executes the authenticate command
     * Authenticates the user (logs them in)
     *
     * @param username of user
     * @param password of user
     * @return "okay"
     * @throws LogException if someone is already logged in
     */
    public String authenticate(String username, String password) throws LogException {
        if (currentUser != UserType.NO_USER) throw new LogException(Errors.SYSTEM_IN_USE);

        for (var customer : customers) {
            if (customer.checkNameAndPass(username, password)) return authenticatePerson(customer);
        }
        for (var mailman : mailmen) {
            if (mailman.checkIDAndPass(username, password)) return authenticatePerson(mailman);
        }
        for (var agent : agents) {
            if (agent.checkIDAndPass(username, password)) return authenticatePerson(agent);
        }

        throw new LogException(Errors.INCORRECT_CREDENTIALS);
    }

    /**
     * Executes the logout command
     * Logging out a user
     *
     * @return "Okay"
     * @throws LogException if no one is logged in
     */
    public String logout() throws LogException {
        if (currentUser == UserType.NO_USER) throw new LogException(Errors.ALREADY_LOGGED_OUT);
        currentUser = UserType.NO_USER;

        if (logOutPerson(customers) != null) return SUCCESS_WITH_MESSAGE;
        if (logOutPerson(mailmen) != null) return SUCCESS_WITH_MESSAGE;
        if (logOutPerson(agents) != null) return SUCCESS_WITH_MESSAGE;

        throw new LogException(Errors.LOG_OUT_FAIL);
    }

    /**
     * Executes the send-mail command (when a customer is logged in)
     * A customer sends mail to another customer
     *
     * @param service is type of mail
     * @param receiver is the recipient customer
     * @return "Okay"
     * @throws LogException if user is not a customer
     * @throws ExistException if customer doesn't exist
     */
    public String sendMailCustomer(PostalServices service, Username receiver) throws LogException, ExistException {
        if (currentUser != UserType.CUSTOMER) throw new LogException(Errors.NO_PERMISSION);
        if (!matchesUsername(receiver)) throw new LogException(Errors.CUSTOMER_DOES_NOT_EXIST);

        customerWithUsername(receiver).addReceivedMail(new Mail(service, getLoggedInCustomer(), customerWithUsername(receiver)));
        getLoggedInCustomer().addService(service);
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Executes the send-mail command (when a mailman is logged in)
     * A mailman sending mail behalf of a customer
     *
     * @param service type of mail
     * @param receiver recipient customer
     * @param sender sender customer
     * @return "okay"
     * @throws LogException user isn't a  mailman
     * @throws ExistException one of the customers don't exist
     */
    public String sendMailMailman(PostalServices service, Username receiver, Username sender) throws LogException, ExistException {
        if (currentUser != UserType.MAILMAN) throw new LogException(Errors.NO_PERMISSION);
        if (!matchesUsername(sender) || !matchesUsername(receiver)) throw new LogException(Errors.CUSTOMER_DOES_NOT_EXIST);

        customerWithUsername(receiver).addReceivedMail(new Mail(service, customerWithUsername(sender), customerWithUsername(receiver)));
        customerWithUsername(sender).addService(service);
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Executes the get-mail command (when a customer is logged in)
     * A customer gets their mail
     *
     * @return "okay"
     * @throws MailException no mail to get
     * @throws ExistException customer doesn't exist
     * @throws LogException customer isn't logged in
     */
    public String getMailCustomer() throws MailException, ExistException, LogException {
        if (currentUser != UserType.CUSTOMER) throw new LogException(Errors.NO_CUSTOMER_LOGGED_IN);
        getLoggedInCustomer().getMail();
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Executes the get-mail command (when a mailman is logged in)
     * A mailman gets a customer's mail for them
     *
     * @param receiver customer
     * @return "okay"
     * @throws MailException no mail to get
     * @throws ExistException customer doesn't exist
     * @throws LogException customer isn't logged in
     */
    public String getMailMailman(Username receiver) throws MailException, ExistException, LogException {
        if (currentUser != UserType.MAILMAN) throw new LogException(Errors.NO_PERMISSION);
        customerWithUsername(receiver).getMail();
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Executes the list-mail command (when a customer is logged in)
     * Lists all the mail of the customer
     *
     * @return a list of all mail
     * @throws ExistException customer doesn't exist
     * @throws LogException customer not logged in
     * @throws MailException no mail
     */
    public String listMailCustomer() throws ExistException, LogException, MailException {
        if (currentUser == UserType.NO_USER) throw new LogException(Errors.NO_CUSTOMER_LOGGED_IN);
        if (currentUser != UserType.CUSTOMER) throw new LogException(Errors.NO_PERMISSION);
        return getLoggedInCustomer().mailToString();
    }

    /**
     * Executes the list-mail command (when a mailman or agent is logged in)
     * Lists mail of a customer via mailman/agent
     *
     * @param name of customer
     * @return list of mail
     * @throws ExistException customer doesn't exist
     * @throws LogException mailman/agent not logged in
     * @throws MailException no mail
     */
    public String listMailMailManAgent(Username name) throws ExistException, LogException, MailException {
        if (currentUser != UserType.MAILMAN && currentUser != UserType.AGENT) throw new LogException(Errors.NO_PERMISSION);
        return customerWithUsername(name).mailToString();
    }

    /**
     * Executes the list-price command (when a customer is logged in)
     * Lists price of all sent mail of a customer
     *
     * @return type of mail sent, number, price
     * @throws ExistException customer doesn't exist
     * @throws SyntaxException wrong syntax
     * @throws LogException customer not logged in
     * @throws MailException no mail
     */
    public String listPriceCustomer() throws ExistException, SyntaxException, LogException, MailException {
        if (currentUser == UserType.NO_USER) throw new LogException(Errors.NO_CUSTOMER_LOGGED_IN);
        if (currentUser != UserType.CUSTOMER) throw new LogException(Errors.NO_PERMISSION);
        if (getLoggedInCustomer().getServices().isEmpty()) throw new MailException(Errors.NO_MAIL_SENT);
        return getLoggedInCustomer().listPrice();
    }

    /**
     * Executes the list-price command (when a mailman or agent is logged in)
     * Lists price of all mail of a customer
     *
     * @param customerUsername customer username
     * @return price of mail sent
     * @throws ExistException customer doesn't exist
     * @throws SyntaxException wrong syntax
     * @throws LogException customer not logged in
     * @throws MailException no mail
     */
    public String listPriceMailmanAgent(Username customerUsername) throws ExistException, SyntaxException, LogException, MailException {
        if (currentUser != UserType.MAILMAN && currentUser != UserType.AGENT) throw new LogException(Errors.NO_PERMISSION);
        return customerWithUsername(customerUsername).listPrice();
    }

    /**
     * Executes the reset-pin command (can only be executed by an agent)
     * Resets a customer's password
     *
     * @param name customer username
     * @param newPassword new password
     * @return "okay"
     * @throws LogException agent not logged in
     * @throws ExistException customer doesn't exist
     * @throws PasswordException new password doesn't meet correct criteria
     */
    public String resetPassword(Username name, Password newPassword) throws LogException, ExistException, PasswordException {
        if (currentUser != UserType.AGENT) throw new LogException(Errors.NO_PERMISSION);
        getLoggedInAgent().resetPassword(customerWithUsername(name), newPassword);
        return SUCCESS_WITH_MESSAGE;
    }


    // PRIVATE METHODS


    /**
     * A generic method to check if a person can be added to the post office
     *
     * @param person person to add
     * @param people list of people of type person
     * @param <T> customer, mailman, or agent
     * @throws LogException someone is logged in
     * @throws ExistException person already exists
     * @throws AddUserException username and id matches
     */
    private <T extends User> void addPersonCheck(T person, HashSet<T> people) throws LogException, ExistException, AddUserException {
        if (currentUser != UserType.NO_USER) throw new LogException(Errors.SYSTEM_IN_USE);
        if (person instanceof Customer && ((Customer) person).usernameEqualsID()) throw new AddUserException(Errors.NAME_ID_MATCHES);
        for (T user : people){
            if (user.checkID(person.getID())) throw new ExistException(Errors.PERSON_EXISTS);
        }
    }

    /**
     * A generic method to authenticate a person
     *
     * @param person customer, mailman, or agent
     * @return "okay"
     * @param <T> type of person
     */
    private <T extends User> String authenticatePerson(T person){
        currentUser = person.getUserType();
        person.setLoggedIn(LOGGED_IN);
        return SUCCESS_WITH_MESSAGE;
    }

    /**
     * Logs out a person
     *
     * @param people list of people
     * @return "okay"
     * @param <T> type of person
     */
    private <T extends User> String logOutPerson(HashSet<T> people){
        for (T person : people){
            if (person.getIsLoggedIn()) {
                person.setLoggedIn(LOGGED_OUT);
                return SUCCESS_WITH_MESSAGE;
            }
        }
        return null;
    }

    /**
     * Method to return a customer with the same username
     *
     * @param name username
     * @return customer
     * @throws ExistException customer with username doesn't exist
     */
    private Customer customerWithUsername(Username name) throws ExistException {
        for (Customer customer : customers) if (customer.checkUsername(name)) return customer;
        throw new ExistException(Errors.CUSTOMER_DOES_NOT_EXIST);
    }

    /**
     * Returns true if username matches customer username
     *
     * @param receiver customer receiving mail
     * @return true = match; false = no match
     */
    private boolean matchesUsername(Username receiver) {
        for (Customer customer : customers) if (customer.checkUsername(receiver)) return true;
        return false;
    }

    /**
     * Returns the logged in customer
     *
     * @return customer
     * @throws LogException no one logged in
     */
    private Customer getLoggedInCustomer() throws LogException {
        for (Customer customer : customers) if (customer.getIsLoggedIn()) return customer;
        throw new LogException(Errors.NO_CUSTOMER_LOGGED_IN);
    }

    /**
     * Returns the logged in agent
     *
     * @return age t
     * @throws LogException no one logged in
     */
    private Agent getLoggedInAgent() throws LogException {
        for (Agent agent : agents) if (agent.getIsLoggedIn()) return agent;
        throw new LogException(Errors.NO_AGENT_LOGGED_IN);
    }
}
