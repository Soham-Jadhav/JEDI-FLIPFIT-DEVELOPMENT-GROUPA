package com.flipfit.business;
import java.util.*;

import com.flipfit.bean.Booking;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
public class CustomerBusiness implements CustomerBusinessInterface {
    /**
     * Obtains all the gyms for the given city.
     * @param city the city name for which the gym list is requested
     * @return returns List of gyms available for the given city
     */
    public List<GymCenter> getGymInCity(String city) {
        System.out.println("Fetched gym in city successfully!");
        return null;
    }
    /**
     * Obtains all the slots for the given gymId.
     * @param gymId the Gym Id for which the slot details are requested
     * @return returns List of available slots for the given gymId
     */
    public List<Slot> getSlotInGym(String gymId) {
        System.out.println("Fetched slot in gym successfully!");
        return null;
    }
    /**
     * Checks if the slot is already booked or not
     * @param slotId the slot id for which the booking status is requested
     * @param date the date on which the booking status is requested
     * @return returns true if the slot id for the given date is fully booked else returns false
     */
    public boolean isSlotBooked(String slotId, Date date) {
        System.out.println("slot booked successfully!");
        return true;
    }


    public int bookSlot (String gymId, String slotId, String email, Date date){
        System.out.println("slot booked successfully!");
        return 0;
    }

    public List<Booking> getBookings(String email){
        System.out.println("Fetched bookings successfully!");
        return null;
    }

    public boolean cancelBooking(String bookingId, String email){
        System.out.println("cancel booking successfully!");
        return true;
    }

}