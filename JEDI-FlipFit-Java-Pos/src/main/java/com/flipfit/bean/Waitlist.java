package com.flipfit.bean;

/**
 * Represents a user's position on a waiting list for a fully booked gym slot.
 * This class stores an entry in a queue for a slot that has no available seats.
 * It contains details about the user, the slot, and the user's current position.
 *
 * @author Akshitha
 */
public class Waitlist {
    private String waitlistId;
    private String userId;
    private String slotId;
    private int position;

    /**
     * Constructs a default Waitlist object.
     */
    public Waitlist() {
        // Default constructor
    }

    /**
     * Constructs a new Waitlist entry with the specified details.
     *
     * @param waitlistId The unique identifier for this waitlist entry.
     * @param userId The ID of the user on the waitlist.
     * @param slotId The ID of the slot for which the user is waiting.
     * @param position The user's position in the queue.
     */
    public Waitlist(String waitlistId, String userId, String slotId, int position) {
        this.waitlistId = waitlistId;
        this.userId = userId;
        this.slotId = slotId;
        this.position = position;
    }

    /**
     * Retrieves the unique identifier of the waitlist entry.
     *
     * @return The waitlist ID.
     */
    public String getWaitlistId() {
        return waitlistId;
    }

    /**
     * Sets the unique identifier of the waitlist entry.
     *
     * @param waitlistId The new waitlist ID.
     */
    public void setWaitlistId(String waitlistId) {
        this.waitlistId = waitlistId;
    }

    /**
     * Retrieves the ID of the user on the waitlist.
     *
     * @return The user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user on the waitlist.
     *
     * @param userId The new user ID.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the ID of the slot for which the user is waiting.
     *
     * @return The slot ID.
     */
    public String getSlotId() {
        return slotId;
    }

    /**
     * Sets the ID of the slot for which the user is waiting.
     *
     * @param slotId The new slot ID.
     */
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    /**
     * Retrieves the user's position in the waitlist queue.
     *
     * @return The position in the queue.
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets the user's position in the waitlist queue.
     *
     * @param position The new position.
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Notifies a user on the waitlist that a slot has become available for them.
     * This method would typically be called when a booking is canceled.
     */
    public void notifyPromotion() {
        // Method implementation to be added
    }

    /**
     * Adds a new user to the end of the waitlist for a specific slot.
     * This method should also set the correct position for the new entry.
     */
    public void addToWaitlist() {
        // Method implementation to be added
    }

    /**
     * Removes a user from the waitlist.
     * This method should also update the positions of the remaining users on the list.
     */
    public void removeFromWaitlist() {
        // Method implementation to be added
    }
}