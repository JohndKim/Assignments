package edu.norman.john.Classes;

/**
 * Enum with the different types of certificates
 */
public enum CertificateType {
    RECOVERED("recovered", 180),
    TESTED("tested", 1),
    VACCINATED("vaccinated", 999999),
    NONE("no certificate", 0);

    private final String type;
    private final int timeValid;

    /**
     * Initializes type of certificate and how long it is valid
     *
     * @param type of certificate
     * @param timeValid time it is valid
     */
    CertificateType(String type, int timeValid){
        this.type = type;
        this.timeValid = timeValid;
    }

    /**
     * Gets certificate type
     *
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Gets how long it lasts
     *
     * @return valid time
     */
    public int getTimeValid() {
        return timeValid;
    }
}
