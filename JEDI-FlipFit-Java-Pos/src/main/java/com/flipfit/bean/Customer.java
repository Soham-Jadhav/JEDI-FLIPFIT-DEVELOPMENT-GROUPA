package com.flipfit.bean;

/**
 * Represents a customer user in the FlipFit system.
 * This class extends the {@link User} class and includes additional
 * personal details specific to a customer.
 *
 * @author Kriti
 */
public class Customer extends User {
    private String name;
    private String phoneNumber;
    private int age;
    private String address;

    /**
     * Constructs a new Customer with the specified details.
     *
     * @param email The email address of the customer.
     * @param password The password for the customer's account.
     * @param roleId The unique identifier for the customer's role.
     * @param name The full name of the customer.
     * @param phoneNumber The contact phone number of the customer.
     * @param age The age of the customer.
     * @param address The physical address of the customer.
     */
    public Customer(String email, String password, String roleId, String name, String phoneNumber, int age, String address) {
        super(email, password, roleId);
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.address = address;
    }

    /**
     * Constructs a default Customer object.
     */
    public Customer() {
        super();
    }

    /**
     * Retrieves the name of the customer.
     *
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the customer.
     *
     * @param name The new name for the customer.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the phone number of the customer.
     *
     * @return The customer's phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the customer.
     *
     * @param phoneNumber The new phone number for the customer.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Retrieves the age of the customer.
     *
     * @return The customer's age.
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age of the customer.
     *
     * @param age The new age for the customer.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Retrieves the address of the customer.
     *
     * @return The customer's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the customer.
     *
     * @param address The new address for the customer.
     */
    public void setAddress(String address) {
        this.address = address;
    }
}