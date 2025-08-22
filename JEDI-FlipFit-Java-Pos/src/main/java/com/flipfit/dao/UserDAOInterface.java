package com.flipfit.dao;

import com.flipfit.bean.Customer;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.User;

/**
 * @author Dhruv
 * Data Access Object (DAO) interface for user management.
 * This interface defines the contract for interacting with the data source
 * to handle all user-related functionalities, including registration,
 * authentication, and profile management for both customers and gym owners.
 */
public interface UserDAOInterface {

    /**
     * Registers a new customer in the data source.
     *
     * @param customer The Customer object containing the registration details.
     * @return {@code true} if the registration was successful, {@code false} otherwise.
     */
    boolean registerCustomer(Customer customer);

    /**
     * Authenticates a user based on their credentials.
     *
     * @param user The User object containing the email, password, and role to authenticate.
     * @return {@code true} if the user's credentials are valid, {@code false} otherwise.
     */
    boolean authenticateUser(User user);

    /**
     * Updates the profile details of an existing customer.
     *
     * @param customer The Customer object with the updated details.
     * @return {@code true} if the profile was successfully updated, {@code false} otherwise.
     */
    boolean editProfile(Customer customer);

    /**
     * Registers a new gym owner in the data source.
     *
     * @param gymOwner The GymOwner object containing the registration details.
     * @return {@code true} if the registration was successful, {@code false} otherwise.
     */
    boolean registerGymOwner(GymOwner gymOwner);

    /**
     * Retrieves the profile details of a specific gym owner.
     *
     * @param gymOwnerEmailId The email ID of the gym owner.
     * @return A GymOwner object containing the profile details, or {@code null} if not found.
     */
    GymOwner getGymOwnerDetails(String gymOwnerEmailId);

    /**
     * Updates the profile details of an existing gym owner.
     *
     * @param gymOwnerDetails The GymOwner object with the updated details.
     */
    void editGymOwnerDetails(GymOwner gymOwnerDetails);
}