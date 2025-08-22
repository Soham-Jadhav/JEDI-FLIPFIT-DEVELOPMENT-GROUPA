package com.flipfit.bean;

/**
 * Represents a gym owner user in the FlipFit system.
 * This class extends the {@link User} class and includes specific
 * details for a gym owner, such as personal identification numbers
 * and a verification status.
 *
 * @author Soham
 */
public class GymOwner extends User {
    private String name;
    private String phoneNumber;
    private String aadharNumber;
    private String panNumber;
    private boolean isVerified;

    /**
     * Constructs a new GymOwner with the specified details.
     * The verification status is initialized to false by default.
     *
     * @param email The email address of the gym owner.
     * @param password The password for the owner's account.
     * @param roleId The unique identifier for the owner's role.
     * @param name The full name of the gym owner.
     * @param phoneNumber The contact phone number of the gym owner.
     * @param aadharNumber The Aadhar number for identification.
     * @param panNumber The PAN number for tax purposes.
     */
    public GymOwner(String email, String password, String roleId, String name, String phoneNumber, String aadharNumber, String panNumber) {
        super(email, password, roleId);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.aadharNumber = aadharNumber;
        this.panNumber = panNumber;
        this.isVerified = false;
    }

    /**
     * Constructs a default GymOwner object.
     */
    public GymOwner() {
        super();
    }

    /**
     * Retrieves the name of the gym owner.
     *
     * @return The owner's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the gym owner.
     *
     * @param name The new name for the gym owner.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the phone number of the gym owner.
     *
     * @return The owner's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the gym owner.
     *
     * @param phoneNumber The new phone number for the gym owner.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Retrieves the Aadhar number of the gym owner.
     *
     * @return The owner's Aadhar number.
     */
    public String getAadharNumber() {
        return aadharNumber;
    }

    /**
     * Sets the Aadhar number of the gym owner.
     *
     * @param aadharNumber The new Aadhar number for the gym owner.
     */
    public void setAadharNumber(String aadharNumber) {
        this.aadharNumber = aadharNumber;
    }

    /**
     * Retrieves the PAN number of the gym owner.
     *
     * @return The owner's PAN number.
     */
    public String getPanNumber() {
        return panNumber;
    }

    /**
     * Sets the PAN number of the gym owner.
     *
     * @param panNumber The new PAN number for the gym owner.
     */
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    /**
     * Checks the verification status of the gym owner.
     *
     * @return {@code true} if the owner is verified, {@code false} otherwise.
     */
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * Sets the verification status of the gym owner.
     *
     * @param isVerified The new verification status.
     */
    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }
}