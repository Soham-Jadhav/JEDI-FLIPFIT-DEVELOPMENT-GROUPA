package com.flipfit.bean;

/**
 * Represents a bookable time slot at a gym.
 * This class stores information about a specific time period,
 * including its capacity and assigned trainer, and tracks the number
 * of booked seats.
 *
 * @author Prisha
 */
public class Slot {
    private String slotId;
    private String gymId;
    private String startTime;
    private String endTime;
    private String trainer;
    private int numOfSeats;
    private int numOfSeatsBooked;

    /**
     * Constructs a default Slot object.
     */
    public Slot() {
        // Default constructor
    }

    /**
     * Constructs a new Slot with the specified details.
     *
     * @param slotId The unique identifier for the slot.
     * @param startTime The start time of the slot (e.g., "09:00 AM").
     * @param endTime The end time of the slot (e.g., "10:00 AM").
     * @param numOfSeats The total number of available seats in the slot.
     * @param trainer The name of the trainer assigned to this slot.
     * @param gymId The unique identifier of the gym this slot belongs to.
     */
    public Slot(String slotId, String startTime, String endTime, int numOfSeats, String trainer, String gymId) {
        this.slotId = slotId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numOfSeats = numOfSeats;
        this.trainer = trainer;
        this.gymId = gymId;
        this.numOfSeatsBooked = 0;
    }

    /**
     * Retrieves the unique identifier of the slot.
     *
     * @return The slot's unique ID.
     */
    public String getSlotId() {
        return slotId;
    }

    /**
     * Sets the unique identifier of the slot.
     *
     * @param slotId The new slot ID.
     */
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    /**
     * Retrieves the start time of the slot.
     *
     * @return The slot's start time.
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets the number of seats that have been booked.
     *
     * @param numOfSeatsBooked The updated number of booked seats.
     */
    public void setNumOfSeatsBooked(int numOfSeatsBooked) {
        this.numOfSeatsBooked = numOfSeatsBooked;
    }

    /**
     * Retrieves the number of seats that have been booked.
     *
     * @return The number of booked seats.
     */
    public int getNumOfSeatsBooked() {
        return numOfSeatsBooked;
    }

    /**
     * Sets the start time of the slot.
     *
     * @param startTime The new start time.
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Retrieves the end time of the slot.
     *
     * @return The slot's end time.
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets the end time of the slot.
     *
     * @param endTime The new end time.
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Retrieves the total number of seats available in the slot.
     *
     * @return The total number of seats.
     */
    public int getNumOfSeats() {
        return numOfSeats;
    }

    /**
     * Sets the total number of seats available in the slot.
     *
     * @param numOfSeats The new total number of seats.
     */
    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    /**
     * Retrieves the name of the trainer assigned to the slot.
     *
     * @return The trainer's name.
     */
    public String getTrainer() {
        return trainer;
    }

    /**
     * Sets the name of the trainer assigned to the slot.
     *
     * @param trainer The new trainer's name.
     */
    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    /**
     * Retrieves the unique identifier of the gym this slot belongs to.
     *
     * @return The ID of the parent gym.
     */
    public String getGymId() {
        return gymId;
    }

    /**
     * Sets the unique identifier of the gym this slot belongs to.
     *
     * @param gymId The new gym ID.
     */
    public void setGymId(String gymId) {
        this.gymId = gymId;
    }
}