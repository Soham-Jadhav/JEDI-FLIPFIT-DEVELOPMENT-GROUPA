package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.Slot;
import java.util.List;

/**
 * @author M J Avani
 * Interface defining the business operations for a gym owner.
 * This contract outlines the functionalities for managing a gym owner's
 * profile, their associated gym centers, and time slots.
 */
public interface GymOwnerBusinessInterface {

    /**
     * Retrieves the profile information for a specific gym owner.
     *
     * @param email The email address of the gym owner.
     * @return A GymOwner object containing the profile details.
     */
    GymOwner getProfile(String email);

    /**
     * Updates the profile details for a gym owner.
     *
     * @param gymOwnerNew The GymOwner object with the updated information.
     */
    void editProfile(GymOwner gymOwnerNew);

    /**
     * Adds a new gym center to the system, owned by the current gym owner.
     *
     * @param gym The GymCenter object to be added.
     * @return {@code true} if the gym was successfully added, {@code false} otherwise.
     */
    boolean addGym(GymCenter gym);

    /**
     * Edits the details of an existing gym center.
     *
     * @param gym The GymCenter object containing the updated details.
     */
    void editGym(GymCenter gym);

    /**
     * Adds a new time slot to a specific gym center.
     *
     * @param slot The Slot object to be added.
     */
    void addSlot(Slot slot);

    /**
     * Retrieves the details of all gym centers associated with a specific gym owner.
     *
     * @param gymOwnerEmail The email of the gym owner.
     * @return A list of GymCenter objects owned by the specified gym owner.
     */
    List<GymCenter> getGymDetail(String gymOwnerEmail);
}