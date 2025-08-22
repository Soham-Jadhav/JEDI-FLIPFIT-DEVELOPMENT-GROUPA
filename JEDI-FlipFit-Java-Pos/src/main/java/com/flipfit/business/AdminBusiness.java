package com.flipfit.business;
import java.util.*;
import com.flipfit.dao.AdminDAOImpl;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;

/**
 * @author Prisha Srinidi
 * Business logic class for administrator operations.
 * This class acts as a service layer for the admin, handling business rules
 * related to managing gym owners and gym centers, including approval workflows.
 */
public class AdminBusiness implements AdminBusinessInterface {
    AdminDAOImpl adminDAO = new AdminDAOImpl();
    /**
     * Obtains a list of every gym owner within the system.
     * This method retrieves all gym owners, regardless of their verification status.
     * @return List of all GymOwner objects.
     */
    public List<GymOwner> getGymOwners() {
        System.out.println("Fetched gym owner details successfully!");
        return adminDAO.getAllGymOwners();
    }

    /**
     * Obtains a list of every gym within the system.
     * This method retrieves all gym centers, regardless of their verification status.
     * @return List of all GymCenter objects.
     */
    public List<GymCenter> getGym() {
        System.out.println("Fetched gym details successfully!");
        return adminDAO.getAllGyms();
    }

    /**
     * Returns all GymOwner objects whose requests are pending for approval.
     * A gym owner's request is considered pending if their `isVerified` status is false.
     * @return List of GymOwner objects with pending requests.
     */
    public List<GymOwner> viewAllPendingGymOwnerRequests() {
        System.out.println("Fetched pending gym owner details successfully!");
        return adminDAO.getPendingGymOwnerRequests();
    }

    /**
     * Approves a single pending registration request from a gym owner.
     *
     * @param gymOwnerEmail The email of the gym owner whose request needs to be approved.
     * @return {@code true} if the approval was successful (i.e., the owner was found and not already verified), {@code false} otherwise.
     */
    public boolean approveSingleGymOwnerRequest(String gymOwnerEmail) {
        adminDAO.approveSingleOwnerRequest(gymOwnerEmail);
        System.out.println("Approved gym owner request! " + gymOwnerEmail);
        return true;
    }

    /**
     * Approves all GymOwners whose requests are currently pending for approval.
     *
     * @return {@code true} if at least one request was approved, {@code false} if there were no pending requests.
     */
    public boolean approveAllPendingGymOwnerRequests() {
        adminDAO.approveAllOwnerRequest();
        System.out.println("Approved all pending gym owner requests!");
        return true;
    }

    /**
     * Returns all GymCenter objects whose requests are pending for approval.
     * A gym center's request is considered pending if its `isVerified` status is false.
     * @return List of GymCenter objects with pending requests.
     */
    public List<GymCenter> viewAllPendingGymRequests() {
        System.out.println("Fetched pending gym requests successfully!");
        return adminDAO.getPendingGymRequests();
    }

    /**
     * Approves a single GymCenter registration request.
     *
     * @param gymId The unique ID of the gym that needs to be approved.
     * @return {@code true} if the approval was successful, {@code false} if the gym ID was not found or was already verified.
     */
    public boolean approveSingleGymRequest(String gymId) {
        adminDAO.approveSingleGymRequest(gymId);
        System.out.println("Successfully approved gym request! " + gymId);
        return true;
    }

    /**
     * Approves all GymCenter objects whose requests are pending for approval.
     *
     * @return {@code true} if at least one gym request was approved, {@code false} if there were no pending requests.
     */
    public boolean approveAllPendingGymRequests() {
        adminDAO.approveAllGymRequest();
        System.out.println("Successfully approved all pending gym requests!");
        return true;
    }
}