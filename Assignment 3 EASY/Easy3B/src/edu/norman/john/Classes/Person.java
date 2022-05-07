package edu.norman.john.Classes;

/**
 * A class representing a person
 */
public class Person {
    private final Role role;
    private final Name name;
    private CertificateType type;
    private Date certificateDate;
    private final int personID;

    /**
     * Constructor that initializes role, name, and person ID
     *
     * @param role their job
     * @param name their full name
     * @param personID their unique numerical ID
     */
    public Person(Role role, Name name, int personID){
        this.role = role;
        this.name = name;
        this.type = CertificateType.NONE;
        this.certificateDate = new Date(0);
        this.personID = personID;
    }

    /**
     * Sets their certification for covid (i.e. tested, vaccinated, recovered, none (automatically none))
     * @param type of certificate
     */
    public void setType(CertificateType type) {
        this.type = type;
    }

    /**
     * Sets when they got it
     * @param certificateDate date of getting the certificate
     */
    public void setCertificateDate(Date certificateDate) {
        this.certificateDate = certificateDate;
    }

    /**
     * Getter to get the date
     *
     * @return the date
     */
    public Date getCertificateDate() {
        return certificateDate;
    }

    /**
     * Getter to get the type
     *
     * @return the type
     */
    public CertificateType getType() {
        return type;
    }

    /**
     * Gets the ID
     *
     * @return ID
     */
    public int getPersonID() {
        return personID;
    }

    /**
     * Gets the role
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Converts the person's information into a string
     * @return
     */
    @Override
    public String toString(){
        return personID + ", " + name + ", " + role.getRole() + ", " + type.getType();
    }

}
