package com.flipfit.business;
import java.util.*;
import com.flipfit.dao.GymOwnerDAOImpl;
import com.flipfit.bean.GymOwner;
import com.flipfit.dao.UserDAOImpl;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;

/**
 * @author Soham Jadhav
 * Business logic class for gym owner operations.
 * This class handles the business-level interactions for a gym owner,
 * including managing their profile, gyms, and slots. It acts as a bridge
 * between the presentation layer and the data access layer (DAO).
 */
public class GymOwnerBusiness implements GymOwnerBusinessInterface {

    // Instance of the UserDAOImpl to handle user-related data access,
    // such as retrieving and editing gym owner profiles.
    UserDAOImpl userDAO = new UserDAOImpl();

    // Instance of the GymOwnerDAOImpl to handle gym and slot-related data access.
    GymOwnerDAOImpl gymOwnerDAO = new GymOwnerDAOImpl();

    /**
     * Retrieves the profile details of a specific gym owner.
     *
     * @param email The email of the gym owner whose profile is to be fetched.
     * @return A GymOwner object containing the profile details.
     */
    public GymOwner getProfile(String email) {
        System.out.println("Fetched Gym owner details successfully! " + email);
        return userDAO.getGymOwnerDetails(email);
    }

    /**
     * Updates the profile details for a gym owner.
     *
     * @param gymOwnerNew The GymOwner object containing the updated profile information.
     */
    public void editProfile(GymOwner gymOwnerNew) {
        userDAO.editGymOwnerDetails(gymOwnerNew);
        System.out.println("\nEdited your profile Successfully!");
    }

    /**
     * Adds a new gym center associated with the current gym owner.
     *
     * @param gym The GymCenter object to be added.
     * @return {@code true} if the gym was successfully added.
     */
    public boolean addGym(GymCenter gym) {
        gymOwnerDAO.addGym(gym);
        System.out.println("\nAdded Gym Successfully!" + gym.getGymId());
        return true;
    }

    /**
     * Edits the details of an existing gym center.
     *
     * @param gym The GymCenter object with updated information.
     */
    public void editGym(GymCenter gym) {
        gymOwnerDAO.editGym(gym);
        System.out.println("\nEdited Gym Details Successfully! ");
    }

    /**
     * Adds a new time slot for a specific gym.
     *
     * @param slot The Slot object to be added.
     */
    public void addSlot(Slot slot) {
        gymOwnerDAO.addSlot(slot);
        System.out.println("\nAdded slot successfully!");
    }

    /**
     * Retrieves a list of all gym centers owned by a specific gym owner.
     *
     * @param gymOwnerEmail The email of the gym owner.
     * @return A List of GymCenter objects.
     */
    public List<GymCenter> getGymDetail(String gymOwnerEmail) {
        System.out.println("\nFetched gym details successfully! " + gymOwnerEmail);
        return gymOwnerDAO.getGymsOfGymOwner(gymOwnerEmail);
    }
}