package model.logic.mail;

import errors.Errors;
import errors.SyntaxException;

/**
 * Enum representing postal services
 *
 * @author kayak
 * @version 1.0
 */
public enum PostalServices {
    LETTER("letter", 0.70),
    REGISTERED_LETTER("registered-letter", 1.20),
    REGISTERED_MAIL("registered-mail", 2.00),
    PACKAGE_SMALL("package-small", 5.00),
    PACKAGE_MEDIUM("package-medium", 6.00),
    PACKAGE_LARGE("package-large", 7.00);

    private final String name;
    private final double price;

    /**
     * Initializes name and price of service
     *
     * @param name name of service
     * @param price price of service
     */
    PostalServices(String name, double price){
        this.name = name;
        this.price = price;
    }

    /**
     * Enter the String service and returns the Enum service
     *
     * @param service to get
     * @return the PostalServices service
     * @throws SyntaxException wrong syntax
     */
    public static PostalServices getPostalService(String service) throws SyntaxException {
        for (PostalServices postal : PostalServices.values()){
            if (postal.getName().equals(service)) return postal;
        }
        throw new SyntaxException(Errors.WRONG_PARAMETERS);
    }

    /**
     * Gets the service name
     * @return service name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the service price
     *
     * @return service price
     */
    public double getPrice() {
        return price;
    }
}
