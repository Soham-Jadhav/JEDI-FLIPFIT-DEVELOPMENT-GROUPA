package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import java.util.List;

/**
 * @author Dhruv
 * Data Access Object (DAO) interface for administrative operations.
 * This interface defines the contract for interacting with the data source
 * to manage gym owners and gym centers, specifically for administrative tasks
 * such as approval and viewing all registered entities.
 */
public interface AdminDAOInterface {

    /**
     * Retrieves a list of all registered gym owners from the data source.
     *
     * @return A List of GymOwner objects.
     */
    List<GymOwner> getAllGymOwners();

    /**
     * Retrieves a list of all registered gym centers from the data source.
     *
     * @return A List of GymCenter objects.
     */
    List<GymCenter> getAllGyms();

    /**
     * Retrieves a list of all gym owner registration requests that are pending approval.
     *
     * @return A List of GymOwner objects representing pending requests.
     */
    List<GymOwner> getPendingGymOwnerRequests();

    /**
     * Retrieves a list of all gym center registration requests that are pending approval.
     *
     * @return A List of GymCenter objects representing pending requests.
     */
    List<GymCenter> getPendingGymRequests();

    /**
     * Approves a single pending gym owner registration request by their email.
     *
     * @param gymOwnerEmail The email of the gym owner to be approved.
     */
    void approveSingleOwnerRequest(String gymOwnerEmail);

    /**
     * Approves all pending gym owner registration requests.
     */
    void approveAllOwnerRequest();

    /**
     * Approves a single pending gym center request by its unique ID.
     *
     * @param gymId The unique ID of the gym center to be approved.
     */
    void approveSingleGymRequest(String gymId);

    /**
     * Approves all pending gym center requests.
     */
    void approveAllGymRequest();
}