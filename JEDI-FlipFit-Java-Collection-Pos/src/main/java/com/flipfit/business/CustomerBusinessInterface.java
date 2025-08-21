package com.flipfit.business;

import com.flipfit.bean.Booking;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;

import java.util.Date;
import java.util.List;

public interface CustomerBusinessInterface {

    List<GymCenter> getGymInCity(String city);

    List<Slot> getSlotInGym(String gymId);

    boolean isSlotBooked(String slotId, Date date);

    int bookSlot(String gymId, String slotId, String email, Date date);

    List<Booking> getBookings(String email);

    boolean cancelBooking(String bookingId, String email);
}