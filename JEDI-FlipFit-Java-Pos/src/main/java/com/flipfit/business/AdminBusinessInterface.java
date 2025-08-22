package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import java.util.List;

/**
 * @author Akshitha
 * Interface for business logic related to administrative tasks.
 * This contract defines the operations an administrator can perform,
 * primarily for managing gym owners and gym centers, including approval processes.
 */
public interface AdminBusinessInterface {

    /**
     * Retrieves a list of all registered gym owners.
     * @return A List of GymOwner objects.
     */
    List<GymOwner> getGymOwners();

    /**
     * Retrieves a list of all gym centers.
     * @return A List of GymCenter objects.
     */
    List<GymCenter> getGym();

    /**
     * Retrieves a list of all gym owner registration requests that are pending approval.
     * @return A List of GymOwner objects representing pending requests.
     */
    List<GymOwner> viewAllPendingGymOwnerRequests();

    /**
     * Approves a single pending gym owner registration request.
     * @param gymOwnerEmail The email of the gym owner to be approved.
     * @return true if the approval was successful, false otherwise.
     */
    boolean approveSingleGymOwnerRequest(String gymOwnerEmail);

    /**
     * Approves all pending gym owner registration requests at once.
     * @return true if all pending requests were successfully approved, false otherwise.
     */
    boolean approveAllPendingGymOwnerRequests();

    /**
     * Retrieves a list of all gym center registration requests that are pending approval.
     * @return A List of GymCenter objects representing pending requests.
     */
    List<GymCenter> viewAllPendingGymRequests();

    /**
     * Approves a single pending gym center registration request.
     * @param gymId The unique ID of the gym center to be approved.
     * @return true if the approval was successful, false otherwise.
     */
    boolean approveSingleGymRequest(String gymId);

    /**
     * Approves all pending gym center registration requests at once.
     * @return true if all pending requests were successfully approved, false otherwise.
     */
    boolean approveAllPendingGymRequests();
}