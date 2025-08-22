package com.flipfit.business;
import java.util.*;

import com.flipfit.bean.Booking;
import com.flipfit.bean.Customer;
import com.flipfit.bean.GymCenter;
import com.flipfit.dao.CustomerDAOImpl;
import com.flipfit.bean.Slot;

/**
 * @author Abhinaya
 * Business logic class for customer-related operations.
 * This class acts as a service layer, orchestrating data access
 * and applying business rules before interacting with the DAO layer.
 */
public class CustomerBusiness implements CustomerBusinessInterface {

    // Instance of the data access object (DAO) for customer operations.
    // This allows the business layer to interact with the data source.
    CustomerDAOImpl customerDao = new CustomerDAOImpl();

    /**
     * Retrieves a list of all gym centers located in a specific city.
     * * @param city The name of the city to search for gym centers.
     * @return A list of GymCenter objects.
     */
    public List<GymCenter> getGymInCity(String city) {
        return customerDao.getGymInCity(city);
    }

    /**
     * Retrieves a list of available time slots for a specific gym center.
     * * @param gymId The unique identifier of the gym center.
     * @return A list of Slot objects.
     */
    public List<Slot> getSlotInGym(String gymId) {
        return customerDao.getSlotInGym(gymId);
    }

    /**
     * Checks if a specific time slot has already been booked on a given date.
     * This is useful for preventing double-booking.
     * * @param slotId The unique identifier of the time slot.
     * @param date The date for which to check the booking status.
     * @return true if the slot is booked, false otherwise.
     */
    public boolean isSlotBooked(String slotId, Date date) {
        return customerDao.isSlotBooked(slotId, date);
    }

    /**
     * Books a time slot for a customer at a specific gym.
     * * @param gymId The unique identifier of the gym center.
     * @param slotId The unique identifier of the time slot to book.
     * @param email The email of the customer making the booking.
     * @param date The date for the booking.
     * @return An integer representing the booking status (e.g., booking ID or a status code).
     */
    public int bookSlot (String gymId, String slotId, String email, Date date){
        return customerDao.bookSlot(gymId, slotId, email, date);
    }

    /**
     * Retrieves a list of all bookings made by a specific customer.
     * * @param email The email of the customer.
     * @return A list of Booking objects.
     */
    public List<Booking> getBookings(String email){
        return customerDao.getBookings(email);
    }

    /**
     * Cancels an existing booking for a customer.
     * * @param bookingId The unique identifier of the booking to be cancelled.
     * @param email The email of the customer who made the booking.
     * @return true if the booking was successfully cancelled, false otherwise.
     */
    public boolean cancelBooking(String bookingId, String email){
        return customerDao.cancelBooking(bookingId, email);
    }

}