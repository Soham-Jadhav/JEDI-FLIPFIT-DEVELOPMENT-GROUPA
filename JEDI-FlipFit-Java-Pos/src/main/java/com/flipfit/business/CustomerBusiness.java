package com.flipfit.business;
import java.util.*;

import com.flipfit.bean.Booking;
import com.flipfit.bean.Customer;
import com.flipfit.bean.GymCenter;
import com.flipfit.dao.CustomerDAOImpl;
import com.flipfit.bean.Slot;
public class CustomerBusiness implements CustomerBusinessInterface {

    CustomerDAOImpl customerDao = new CustomerDAOImpl();

    public List<GymCenter> getGymInCity(String city) {
        return customerDao.getGymInCity(city);
    }

    public List<Slot> getSlotInGym(String gymId) {
        return customerDao.getSlotInGym(gymId);
    }

    public boolean isSlotBooked(String slotId, Date date) {
        return customerDao.isSlotBooked(slotId, date);
    }

    public int bookSlot (String gymId, String slotId, String email, Date date){
        return customerDao.bookSlot(gymId, slotId, email, date);
    }

    public List<Booking> getBookings(String email){
        return customerDao.getBookings(email);
    }

    public boolean cancelBooking(String bookingId, String email){
        return customerDao.cancelBooking(bookingId, email);
    }

}