package com.flipfit.business;
import java.util.*;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
public class AdminBusiness implements AdminBusinessInterface {

    public List<GymOwner> getGymOwners() {
        System.out.println("Fetched gym owner details successfully!");

        List<GymOwner> gymOwnerList = new ArrayList<>();
        gymOwnerList.add(new GymOwner("email1","psd1","1","name1","123","123","123"));
        gymOwnerList.add(new GymOwner("email2","psd2","2","name2","123","123","123"));
        gymOwnerList.add(new GymOwner("email3","psd3","3","name3","123","123","123"));

        return gymOwnerList;
    }
    /**
     * Obtains a list of every gym within the system.
     * @return List of Gym objects
     */
    public List<GymCenter> getGym() {
        System.out.println("Fetched gym details successfully!");

        List<GymCenter> gymCenterList = new ArrayList<>();
        gymCenterList.add(new GymCenter("1", "Gym1", "email1", "addr1", 10, 10, true));
        gymCenterList.add(new GymCenter("2", "Gym2", "email2", "addr2", 10, 10, true));
        gymCenterList.add(new GymCenter("3", "Gym3", "email3", "addr3", 10, 10, true));

        return gymCenterList;
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