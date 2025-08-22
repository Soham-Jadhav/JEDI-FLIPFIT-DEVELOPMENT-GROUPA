package com.flipfit.bean;

/**
 * Represents an administrator user in the FlipFit system.
 * This class extends the {@link User} class and adds specific
 * properties for an admin, such as name and phone number.
 *
 * @author Kriti
 */
public class Admin extends User {
    private String name;
    private String phoneNumber;

    /**
     * Constructs a new Admin with the specified details.
     *
     * @param email The email address of the admin.
     * @param password The password for the admin's account.
     * @param roleId The unique identifier for the admin's role.
     * @param name The full name of the admin.
     * @param phoneNumber The contact phone number of the admin.
     */
    public Admin(String email, String password, String roleId, String name, String phoneNumber) {
        super(email, password, roleId);
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Constructs a default Admin object.
     */
    public Admin() {
        super();
    }

    /**
     * Retrieves the name of the admin.
     *
     * @return The admin's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the admin.
     *
     * @param name The new name for the admin.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the phone number of the admin.
     *
     * @return The admin's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the admin.
     *
     * @param phoneNumber The new phone number for the admin.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}