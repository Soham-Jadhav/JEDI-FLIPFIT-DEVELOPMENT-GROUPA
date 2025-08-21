package com.flipfit.business;
import java.util.*;
import com.flipfit.dao.AdminDAOImpl;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
public class AdminBusiness implements AdminBusinessInterface {

    // Hardcoded lists to simulate DAO functionality
    private List<GymOwner> gymOwners = new ArrayList<>();
    private List<GymCenter> gymCenters = new ArrayList<>();

    public AdminBusiness() {
        // Initialize hardcoded data
        GymOwner verifiedGymOwner = new GymOwner("owner1@email.com", "pass123", "owner", "John Doe", "1234567890", "111122223333", "ABCDE1234F");
        verifiedGymOwner.setVerified(true);
        gymOwners.add(verifiedGymOwner);
        gymOwners.add(new GymOwner("owner2@email.com", "pass456", "owner", "Jane Smith", "0987654321", "444455556666", "FGHIJ5678K"));

        GymCenter verifiedGym = new GymCenter("1", "Flex Fit", "Hyderabad", "owner1@email.com", 5, 20, false);
        verifiedGym.setVerified(true);
        gymCenters.add(verifiedGym);
        gymCenters.add(new GymCenter("2", "Iron Core", "Bangalore", "owner2@email.com", 10, 30, false));
    }

    /**
     * Obtains a list of every gym owner within the system.
     * @return List of GymOwner objects
     */
    public List<GymOwner> getGymOwners() {
        System.out.println("Fetched gym owner details successfully!");
        return new ArrayList<>(gymOwners);
    }

    /**
     * Obtains a list of every gym within the system.
     * @return List of Gym objects
     */
    public List<GymCenter> getGym() {
        System.out.println("Fetched gym details successfully!");
        return new ArrayList<>(gymCenters);
    }

    /**
     * Returns all GymOwners object whose requests are pending for approval.
     * @return List of GymOwner objects
     */
    public List<GymOwner> viewAllPendingGymOwnerRequests() {
        System.out.println("Fetched pending gym owner details successfully!");
        List<GymOwner> pendingOwners = new ArrayList<>();
        for (GymOwner owner : gymOwners) {
            if (!owner.isVerified()) {
                pendingOwners.add(owner);
            }
        }
        return pendingOwners;
    }

    /**
     * Accepts one request from a gym owner.
     * @param gymOwnerEmail The request's email that has to be approved
     */
    public boolean approveSingleGymOwnerRequest(String gymOwnerEmail) {
        for (GymOwner owner : gymOwners) {
            if (owner.getEmail().equals(gymOwnerEmail) && !owner.isVerified()) {
                owner.setVerified(true);
                System.out.println("Approved gym owner request! " + gymOwnerEmail);
                return true;
            }
        }
        return false;
    }

    /**
     * Approves all GymOwners whose requests are pending for approval.
     */
    public boolean approveAllPendingGymOwnerRequests() {
        boolean approved = false;
        for (GymOwner owner : gymOwners) {
            if (!owner.isVerified()) {
                owner.setVerified(true);
                approved = true;
            }
        }
        System.out.println("Approved all pending gym owner requests!");
        return approved;
    }

    /**
     * Returns all Gym object whose requests are pending for approval.
     * @return List of Gym objects
     */
    public List<GymCenter> viewAllPendingGymRequests() {
        System.out.println("Fetched pending gym requests successfully!");
        List<GymCenter> pendingGyms = new ArrayList<>();
        for (GymCenter gym : gymCenters) {
            if (!gym.isVerified()) {
                pendingGyms.add(gym);
            }
        }
        return pendingGyms;
    }

    /**
     * Approves a single Gym object request.
     * @param gymId the id of a gym that needs to be approved
     * @return true if the gymId is valid else returns false
     */
    public boolean approveSingleGymRequest(String gymId) {
        for (GymCenter gym : gymCenters) {
            if (gym.getGymId().equals(gymId) && !gym.isVerified()) {
                gym.setVerified(true);
                System.out.println("Successfully approved gym request! " + gymId);
                return true;
            }
        }
        return false;
    }

    /**
     * Approves all Gym whose requests are pending for approval.
     */
    public boolean approveAllPendingGymRequests() {
        boolean approved = false;
        for (GymCenter gym : gymCenters) {
            if (!gym.isVerified()) {
                gym.setVerified(true);
                approved = true;
            }
        }
        System.out.println("Successfully approved all pending gym requests!");
        return approved;
    }
}
