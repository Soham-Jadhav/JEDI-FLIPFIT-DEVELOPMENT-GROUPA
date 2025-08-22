package com.flipfit.bean;

/**
 * Represents a gym center within the FlipFit system.
 * This class stores details about a gym, including its owner, location,
 * capacity, and verification status.
 *
 * @author Dhruv
 */
public class GymCenter {
    private String gymId;
    private String gymName;
    private String ownerEmail;
    private String address;
    private int slotCount;
    private int seatsPerSlotCount;
    private boolean isVerified;

    /**
     * Constructs a default GymCenter object.
     */
    public GymCenter() {
        // Default constructor
    }

    /**
     * Constructs a new GymCenter with the specified details.
     *
     * @param gymId The unique identifier for the gym.
     * @param gymName The name of the gym.
     * @param ownerEmail The email address of the gym owner.
     * @param address The physical address of the gym.
     * @param slotCount The total number of available time slots per day.
     * @param seatsPerSlotCount The number of seats or capacity for each slot.
     * @param isVerified The verification status of the gym.
     */
    public GymCenter(String gymId, String gymName, String ownerEmail, String address, int slotCount, int seatsPerSlotCount, boolean isVerified) {
        this.gymId = gymId;
        this.gymName = gymName;
        this.ownerEmail = ownerEmail;
        this.address = address;
        this.slotCount = slotCount;
        this.seatsPerSlotCount = seatsPerSlotCount;
        this.isVerified = isVerified;
    }

    /**
     * Retrieves the unique identifier of the gym.
     *
     * @return The gym's unique ID.
     */
    public String getGymId() {
        return gymId;
    }

    /**
     * Sets the unique identifier of the gym.
     *
     * @param gymId The new gym ID.
     */
    public void setGymId(String gymId) {
        this.gymId = gymId;
    }

    /**
     * Retrieves the name of the gym.
     *
     * @return The gym's name.
     */
    public String getGymName() {
        return gymName;
    }

    /**
     * Sets the name of the gym.
     *
     * @param gymName The new gym name.
     */
    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    /**
     * Retrieves the email address of the gym owner.
     *
     * @return The owner's email.
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * Sets the email address of the gym owner.
     *
     * @param ownerEmail The new owner email.
     */
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    /**
     * Retrieves the address of the gym.
     *
     * @return The gym's address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the gym.
     *
     * @param address The new gym address.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Retrieves the number of time slots available at the gym.
     *
     * @return The number of slots.
     */
    public int getSlotCount() {
        return slotCount;
    }

    /**
     * Sets the number of time slots available at the gym.
     *
     * @param slotCount The new slot count.
     */
    public void setSlotCount(int slotCount) {
        this.slotCount = slotCount;
    }

    /**
     * Retrieves the number of seats or capacity per slot.
     *
     * @return The number of seats per slot.
     */
    public int getSeatsPerSlotCount() {
        return seatsPerSlotCount;
    }

    /**
     * Sets the number of seats or capacity per slot.
     *
     * @param seatsPerSlotCount The new number of seats per slot.
     */
    public void setSeatsPerSlotCount(int seatsPerSlotCount) {
        this.seatsPerSlotCount = seatsPerSlotCount;
    }

    /**
     * Checks if the gym has been verified.
     *
     * @return {@code true} if the gym is verified, {@code false} otherwise.
     */
    public boolean isVerified() {
        return isVerified;
    }

    /**
     * Sets the verification status of the gym.
     *
     * @param isVerified The new verification status.
     */
    public void setVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    /**
     * Returns a string representation of the GymCenter object.
     *
     * @return A formatted string containing the gym's details.
     */
    @Override
    public String toString() {
        return "Gym Id : " + this.gymId +
                "\nGym Name : " + this.gymName +
                "\nGym Owner Email : " + this.getOwnerEmail() +
                "\nGym Address : " + this.getAddress() +
                "\nGym Slotcount : " + this.getSlotCount() +
                "\nSeat per slot count : " + this.getSeatsPerSlotCount() +
                "\nVerification : " + (this.isVerified() ? "Yes" : "No");
    }
}