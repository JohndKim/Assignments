package model.logic.mail;

import model.logic.user.Customer;

/**
 * A class representing mail
 *
 * @author kayak
 * @version 1.0
 */
public class Mail {
    private final PostalServices mailType;
    private final Customer sender;
    private final Customer receiver;

    /**
     * Initializes mail type, sender, and receiver
     *
     * @param mailType type of mail sent
     * @param sender customer that sent it
     * @param receiver customer that received it
     */
    public Mail(PostalServices mailType, Customer sender, Customer receiver){
        this.mailType = mailType;
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Gets the mail type
     *
     * @return mail type
     */
    public PostalServices getMailType() {
        return mailType;
    }
}
