package com.flipfit.bean;

import java.util.Date;

/**
 * Represents a booking for a fitness class or gym slot in the FlipFit system.
 * This class encapsulates all the details related to a specific booking made by a customer.
 *
 * @author Your Name (or Team Name)
 */
public class Booking {
    private String bookingId;
    private String slotId;
    private String gymId;
    private String type;
    private Date date;
    private String customerEmail;

    /**
     * Constructs a default Booking object.
     */
    public Booking() {
        super();
    }

    /**
     * Constructs a new Booking with all the specified details.
     *
     * @param bookingId The unique identifier for the booking.
     * @param slotId The unique identifier for the time slot.
     * @param gymId The unique identifier for the gym.
     * @param type The type of booking (e.g., "CLASS", "OPEN_GYM").
     * @param date The date of the booking.
     * @param customerEmail The email address of the customer who made the booking.
     */
    public Booking(String bookingId, String slotId, String gymId, String type, Date date, String customerEmail) {
        this.bookingId = bookingId;
        this.slotId = slotId;
        this.gymId = gymId;
        this.type = type;
        this.date = date;
        this.customerEmail = customerEmail;
    }

    /**
     * Retrieves the unique identifier of the booking.
     *
     * @return The booking ID.
     */
    public String getBookingId() {
        return bookingId;
    }

    /**
     * Sets the unique identifier of the booking.
     *
     * @param bookingId The new booking ID.
     */
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    /**
     * Retrieves the unique identifier of the time slot.
     *
     * @return The slot ID.
     */
    public String getSlotId() {
        return slotId;
    }

    /**
     * Sets the unique identifier of the time slot.
     *
     * @param slotId The new slot ID.
     */
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }

    /**
     * Retrieves the unique identifier of the gym.
     *
     * @return The gym ID.
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
     * Retrieves the type of the booking.
     *
     * @return The booking type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the booking.
     *
     * @param type The new booking type.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Retrieves the date of the booking.
     *
     * @return The booking date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets the date of the booking.
     *
     * @param date The new booking date.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Retrieves the email address of the customer who made the booking.
     *
     * @return The customer's email address.
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /**
     * Sets the email address of the customer who made the booking.
     *
     * @param customerEmail The new customer email address.
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}