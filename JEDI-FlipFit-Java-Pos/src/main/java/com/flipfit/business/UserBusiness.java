package com.flipfit.business;
import com.flipfit.bean.Customer;
import com.flipfit.dao.UserDAOImpl;
import com.flipfit.bean.User;
import com.flipfit.bean.GymOwner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Akshitha
 * Business logic class for user-related operations.
 * This class handles the business rules for user registration, authentication,
 * and profile management, acting as an intermediary between the user interface
 * and the data access layer (DAO).
 */
public class UserBusiness implements UserBusinessInterface {

    // Instance of the UserDAOImpl to perform database operations.
    UserDAOImpl userDao = new UserDAOImpl();

    // A static list to potentially hold users in memory.
    // Note: In a real-world application, this might be replaced by direct
    // database interaction to avoid state issues in a multi-user environment.
    private static List<User> users=new ArrayList<>();;

    /**
     * Retrieves the list of users currently held in memory.
     * @return A List of User objects.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the static list of users.
     * @param users A List of User objects to be set.
     */
    public void setUsers(List<User> users) {
        UserBusiness.users =users;
    }

    /**
     * Registers a new customer in the system.
     * This method delegates the registration process to the DAO layer.
     * @param customer The Customer object to be registered.
     * @return {@code true} if the registration was successful, {@code false} otherwise.
     */
    public boolean registerCustomer(Customer customer) {
        boolean registerSuccess = false;
        registerSuccess = userDao.registerCustomer(customer);
        return registerSuccess;
    }

    /**
     * Authenticates a user based on their credentials.
     * This method checks the user's details against the stored records via the DAO.
     * @param userToAuth The User object containing the credentials to authenticate.
     * @return {@code true} if the user is successfully authenticated, {@code false} otherwise.
     */
    public boolean authenticateUser(User userToAuth) {
        // Loop through every user in the static list
        boolean authenticateSuccess = false;
        authenticateSuccess = userDao.authenticateUser(userToAuth);
        return authenticateSuccess; // No match found after checking all users
    }

    /**
     * Edits the profile details of an existing customer.
     * The updated customer information is passed to the DAO for persistence.
     * @param customer The Customer object with updated details.
     * @return {@code true} if the profile was successfully edited, {@code false} otherwise.
     */
    public boolean editProfile(Customer customer) {
        boolean editSuccess = false;
        editSuccess = userDao.editProfile(customer);
        return editSuccess;
    }

    /**
     * Registers a new gym owner in the system.
     * This operation is delegated to the DAO layer.
     * @param gymOwner The GymOwner object to be registered.
     * @return {@code true} if the registration was successful, {@code false} otherwise.
     */
    public boolean registerGymOwner(GymOwner gymOwner) {
        boolean registerSuccess = false;
        registerSuccess = userDao.registerGymOwner(gymOwner);
        return registerSuccess;
    }
}