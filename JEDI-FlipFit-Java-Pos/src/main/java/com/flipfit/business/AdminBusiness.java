package com.flipfit.business;
import java.util.*;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;

ublic class AdminBusiness implements AdminBusinessInterface {

    // Hardcoded data lists to replace database connections
    private List<GymOwner> allGymOwners;
    private List<GymCenter> allGyms;

    public AdminBusiness() {
        // Initialize hardcoded data in the constructor
        this.allGymOwners = new ArrayList<>();
        this.allGyms = new ArrayList<>();
        initializeData();
    }

    /**
     * Fills the in-memory lists with sample data.
     * The new GymOwner constructor is used here.
     * Some entries are set to 'isVerified=true' and some to 'isVerified=false'
     * to simulate pending requests.
     */
    private void initializeData() {
        // Sample Gym Owners using the new constructor
        allGymOwners.add(new GymOwner("john.doe@example.com", "pass123", "GYM_OWNER", "John Doe", "9876543210", "111122223333", "ABCDE1234F"));
        allGymOwners.add(new GymOwner("jane.smith@example.com", "pass456", "GYM_OWNER", "Jane Smith", "9876543211", "222233334444", "FGHIJ5678K"));
        allGymOwners.get(0).setVerified(true); // Manually set some to verified
        allGymOwners.get(1).setVerified(false); // Manually set some to unverified

        allGymOwners.add(new GymOwner("peter.jones@example.com", "pass789", "GYM_OWNER", "Peter Jones", "9876543212", "333344445555", "LMNOP9012Q"));
        allGymOwners.add(new GymOwner("susan.white@example.com", "pass012", "GYM_OWNER", "Susan White", "9876543213", "444455556666", "RSTUV3456W"));
        allGymOwners.get(2).setVerified(true); // Manually set to verified
        allGymOwners.get(3).setVerified(false); // Manually set to unverified


        // Sample Gym Centers
        allGyms.add(new GymCenter("G1", "Flex Fit Gym", true, "john.doe@example.com"));
        allGyms.add(new GymCenter("G2", "Iron Beast Fitness", false, "jane.smith@example.com"));
        allGyms.add(new GymCenter("G3", "Planet Muscle", true, "peter.jones@example.com"));
        allGyms.add(new GymCenter("G4", "The Sweat Spot", false, "susan.white@example.com"));
    }

    /**
     * Obtains a list of every gym owner from the hardcoded list.
     * @return List of GymOwner objects
     */
    @Override
    public List<GymOwner> getGymOwners() {
        System.out.println("Fetched all gym owner details from hardcoded list.");
        // Return a copy of the list to prevent external modification
        return new ArrayList<>(this.allGymOwners);
    }

    /**
     * Obtains a list of every gym from the hardcoded list.
     * @return List of GymCenter objects
     */
    @Override
    public List<GymCenter> getGym() {
        System.out.println("Fetched all gym details from hardcoded list.");
        // Return a copy of the list to prevent external modification
        return new ArrayList<>(this.allGyms);
    }

    /**
     * Returns all GymOwner objects whose requests are pending for verification.
     * This is done by filtering the hardcoded list based on the isVerified field.
     * @return List of GymOwner objects
     */
    @Override
    public List<GymOwner> viewAllPendingGymOwnerRequests() {
        System.out.println("Fetched pending gym owner details from hardcoded list.");
        return this.allGymOwners.stream()
                .filter(owner -> !owner.isVerified())
                .collect(Collectors.toList());
    }

    /**
     * Approves a single gym owner request by changing their isVerified status.
     * @param gymOwnerEmail The email of the gym owner to approve
     * @return true if the owner was found and verified, false otherwise
     */
    @Override
    public boolean approveSingleGymOwnerRequest(String gymOwnerEmail) {
        System.out.println("Approving single gym owner request: " + gymOwnerEmail);
        for (GymOwner owner : allGymOwners) {
            if (owner.getEmail().equals(gymOwnerEmail) && !owner.isVerified()) {
                owner.setVerified(true);
                System.out.println("Approved gym owner request for " + gymOwnerEmail + "!");
                return true;
            }
        }
        System.out.println("Gym owner not found or already verified: " + gymOwnerEmail);
        return false;
    }

    /**
     * Approves all GymOwners whose requests are pending by setting their isVerified status to true.
     * @return always returns true
     */
    @Override
    public boolean approveAllPendingGymOwnerRequests() {
        System.out.println("Approving all pending gym owner requests...");
        this.allGymOwners.forEach(owner -> owner.setVerified(true));
        System.out.println("Approved all pending gym owner requests!");
        return true;
    }

    /**
     * Returns all GymCenter objects whose requests are pending for approval.
     * This is done by filtering the hardcoded list.
     * @return List of GymCenter objects
     */
    @Override
    public List<GymCenter> viewAllPendingGymRequests() {
        System.out.println("Fetched pending gym requests from hardcoded list.");
        return this.allGyms.stream()
                .filter(gym -> !gym.isApproved())
                .collect(Collectors.toList());
    }

    /**
     * Approves a single GymCenter object request by changing its approval status.
     * @param gymId the id of a gym that needs to be approved
     * @return true if the gym was found and approved, false otherwise
     */
    @Override
    public boolean approveSingleGymRequest(String gymId) {
        System.out.println("Approving single gym request: " + gymId);
        for (GymCenter gym : allGyms) {
            if (gym.getId().equals(gymId) && !gym.isApproved()) {
                gym.setApproved(true);
                System.out.println("Successfully approved gym request for " + gymId + "!");
                return true;
            }
        }
        System.out.println("Gym not found or already approved: " + gymId);
        return false;
    }

    /**
     * Approves all GymCenter objects whose requests are pending by setting their approval status to true.
     * @return always returns true
     */
    @Override
    public boolean approveAllPendingGymRequests() {
        System.out.println("Approving all pending gym requests...");
        this.allGyms.forEach(gym -> gym.setApproved(true));
        System.out.println("Successfully approved all pending gym requests!");
        return true;
    }
}
