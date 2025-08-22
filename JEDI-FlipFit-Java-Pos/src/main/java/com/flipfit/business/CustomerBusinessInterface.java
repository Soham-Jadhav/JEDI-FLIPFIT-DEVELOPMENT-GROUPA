package com.flipfit.business;

import com.flipfit.bean.Booking;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;

import java.util.Date;
import java.util.List;

/**
 * @author Akshitha
 * Interface defining the business operations available to a customer.
 * This contract outlines the core functionalities for viewing gyms, booking slots,
 * and managing personal bookings.
 */
public interface CustomerBusinessInterface {

    /**
     * Retrieves a list of all gym centers located within a specific city.
     *
     * @param city The name of the city to search for gym centers.
     * @return A list of GymCenter objects.
     */
    List<GymCenter> getGymInCity(String city);

    /**
     * Retrieves a list of available time slots for a specified gym.
     *
     * @param gymId The unique identifier of the gym center.
     * @return A list of Slot objects.
     */
    List<Slot> getSlotInGym(String gymId);

    /**
     * Checks if a particular time slot has already been booked on a given date.
     *
     * @param slotId The unique identifier of the time slot.
     * @param date   The date for which to check the booking status.
     * @return {@code true} if the slot is booked, {@code false} otherwise.
     */
    boolean isSlotBooked(String slotId, Date date);

    /**
     * Books a time slot for a customer at a specific gym.
     *
     * @param gymId  The unique identifier of the gym center.
     * @param slotId The unique identifier of the time slot to book.
     * @param email  The email of the customer making the booking.
     * @param date   The date for the booking.
     * @return An integer representing the booking status (e.g., a booking ID or a status code).
     */
    int bookSlot(String gymId, String slotId, String email, Date date);

    /**
     * Retrieves a list of all past and upcoming bookings made by a specific customer.
     *
     * @param email The email of the customer.
     * @return A list of Booking objects associated with the customer's email.
     */
    List<Booking> getBookings(String email);

    /**
     * Cancels an existing booking for a customer.
     *
     * @param bookingId The unique identifier of the booking to be cancelled.
     * @param email     The email of the customer who made the booking.
     * @return {@code true} if the booking was successfully cancelled, {@code false} otherwise.
     */
    boolean cancelBooking(String bookingId, String email);
}