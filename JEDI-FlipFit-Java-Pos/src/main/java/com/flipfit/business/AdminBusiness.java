package com.flipfit.business;
import java.util.*;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
public class AdminBusiness implements AdminBusinessInterface {

    public List<GymOwner> getGymOwners() {
        System.out.println("Fetched gym owner details successfully!");
        return null;
    }
    /**
     * Obtains a list of every gym within the system.
     * @return List of Gym objects
     */
    public List<GymCenter> getGym() {
        System.out.println("Fetched gym details successfully!");
        return null;
    }
    /**
     * Returns all GymOwners object whose requests are pending for approval.
     * @return List of GymOwner objects
     */
    public List<GymOwner> viewAllPendingGymOwnerRequests() {
        System.out.println("Fetched pending gym owner details successfully!");
        return null;
    }

    /**
     * Accepts one request from a gym owner.
     * @param gymOwnerEmail The request's email that has to be approved
     */
    public boolean approveSingleGymOwnerRequest(String gymOwnerEmail) {

        System.out.println("Approved gym owner request! " + gymOwnerEmail);
        return true;
    }

    /**
     * Approves all GymOwners whose requests are pending for approval.
     */
    public boolean approveAllPendingGymOwnerRequests() {
        System.out.println("Approved all pending gym owner requests!");
        return true;
    }
    /**
     * Returns all Gym object whose requests are pending for approval.
     * @return List of Gym objects
     */
    public List<GymCenter> viewAllPendingGymRequests() {
        System.out.println("Fetched pending gym requests successfully!");
        return null;
    }
    /**
     * Approves a single Gym object request.
     * @param gymId the id of a gym that needs to be approved
     * @return true if the gymId is valid else returns false
     */
    public boolean approveSingleGymRequest(String gymId) {
        System.out.println("Successfully approved gym request! " + gymId);
        return true;
    }
    /**
     * Approves all Gym whose requests are pending for approval.
     */
    public boolean approveAllPendingGymRequests() {
        System.out.println("Successfully approved all pending gym requests!");
        return true;
    }
}