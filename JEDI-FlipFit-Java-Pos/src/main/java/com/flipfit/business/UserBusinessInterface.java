package com.flipfit.business;

import com.flipfit.bean.Customer;
import com.flipfit.bean.User;
import com.flipfit.bean.GymOwner;


/**
 * @author M J Avani
 * Interface defining the business operations for user management.
 * This contract includes methods for user registration, authentication,
 * and profile editing for both customers and gym owners.
 */
public interface UserBusinessInterface {

    /**
     * Registers a new customer in the system.
     *
     * @param customer The Customer object containing the registration details.
     * @return {@code true} if the customer was successfully registered, {@code false} otherwise.
     */
    boolean registerCustomer(Customer customer);

    /**
     * Authenticates a user based on their credentials (e.g., email and password).
     *
     * @param user The User object containing the credentials to be authenticated.
     * @return {@code true} if the user's credentials are valid, {@code false} otherwise.
     */
    boolean authenticateUser(User user);

    /**
     * Edits the profile information of an existing customer.
     *
     * @param customer The Customer object with the updated profile details.
     * @return {@code true} if the profile was successfully updated, {@code false} otherwise.
     */
    boolean editProfile(Customer customer);

    /**
     * Registers a new gym owner in the system.
     *
     * @param gymOwner The GymOwner object containing the registration details.
     * @return {@code true} if the gym owner was successfully registered, {@code false} otherwise.
     */
    boolean registerGymOwner(GymOwner gymOwner);
}