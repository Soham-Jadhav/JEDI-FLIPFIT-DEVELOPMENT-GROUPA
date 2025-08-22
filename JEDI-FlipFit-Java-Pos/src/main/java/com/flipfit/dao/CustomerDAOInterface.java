package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import com.flipfit.bean.Booking;

import java.util.Date;
import java.util.List;

/**
 * @author Dhruv
 * Data Access Object (DAO) interface for customer-related operations.
 * This interface defines the contract for interacting with the data source
 * to handle all customer functionalities, such as searching for gyms,
 * booking slots, and managing their personal bookings.
 */
public interface CustomerDAOInterface {

    /**
     * Retrieves a list of all gym centers located in a specific city.
     *
     * @param city The name of the city to search for.
     * @return A List of GymCenter objects in the specified city.
     */
    List<GymCenter> getGymInCity(String city);

    /**
     * Retrieves a list of all available time slots for a given gym.
     *
     * @param gymId The unique identifier of the gym.
     * @return A List of Slot objects associated with the gym.
     */
    List<Slot> getSlotInGym(String gymId);

    /**
     * Checks if a specific time slot is fully booked on a given date.
     *
     * @param slotId The unique identifier of the slot.
     * @param date The date to check for booking availability.
     * @return {@code true} if the slot is fully booked, {@code false} otherwise.
     */
    boolean isSlotBooked(String slotId, Date date);

    /**
     * Books a time slot for a customer at a specific gym.
     *
     * @param gymId The unique identifier of the gym.
     * @param slotId The unique identifier of the slot to book.
     * @param email The email of the customer making the booking.
     * @param date The date for the booking.
     * @return An integer representing the outcome of the booking (e.g., success, waitlist).
     */
    int bookSlot(String gymId, String slotId, String email, Date date);

    /**
     * Retrieves a list of all bookings made by a specific customer.
     *
     * @param email The email of the customer.
     * @return A List of Booking objects associated with the customer.
     */
    List<Booking> getBookings(String email);

    /**
     * Cancels an existing booking made by a customer.
     *
     * @param bookingId The unique identifier of the booking to cancel.
     * @param email The email of the customer who made the booking.
     * @return {@code true} if the booking was successfully cancelled, {@code false} otherwise.
     */
    boolean cancelBooking(String bookingId, String email);
}