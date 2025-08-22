package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;

import java.util.List;

/**
 * @author Dhruv
 * Data Access Object (DAO) interface for gym owner operations.
 * This interface defines the contract for a gym owner's interactions
 * with the data source, including adding and managing gym centers and time slots.
 */
public interface GymOwnerDAOInterface {

    /**
     * Adds a new gym center to the data source.
     *
     * @param gymDetails The GymCenter object containing the details of the gym to be added.
     */
    void addGym(GymCenter gymDetails);

    /**
     * Edits the details of an existing gym center in the data source.
     *
     * @param gymDetails The GymCenter object with the updated details.
     */
    void editGym(GymCenter gymDetails);

    /**
     * Adds a new time slot to a gym center in the data source.
     *
     * @param slot The Slot object containing the details of the slot to be added.
     */
    void addSlot(Slot slot);

    /**
     * Retrieves a list of all gym centers owned by a specific gym owner.
     *
     * @param gymOwnerEmail The email of the gym owner.
     * @return A List of GymCenter objects associated with the specified gym owner.
     */
    List<GymCenter> getGymsOfGymOwner(String gymOwnerEmail);
}