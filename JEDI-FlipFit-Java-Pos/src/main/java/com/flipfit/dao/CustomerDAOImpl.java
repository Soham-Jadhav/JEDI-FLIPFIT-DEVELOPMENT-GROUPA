package com.flipfit.dao;
import java.util.*;
import com.flipfit.utils.IdGenerator;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import com.flipfit.bean.Booking;
import com.flipfit.bean.Customer;
public class CustomerDAOImpl implements CustomerDAOInterface{
    private static List<GymCenter> gyms = new ArrayList<>();
    private static List<Slot> slots = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();

    GymCenter gym1 = new GymCenter("g1", "gym1", "gymowner1@gmail.com", "Kanpur", 2, 5, true);
    GymCenter gym2 = new GymCenter("g2", "gym2", "gymowner2@gmail.com", "Hyderabad", 3, 5, true);
    GymCenter gym3 = new GymCenter("g3", "gym3", "gymowner3@gmail.com", "Bangalore", 2, 3, true);
    GymCenter gym4 = new GymCenter("g4", "gym4", "gymowner4@gmail.com", "Cochin", 6, 5, true);

    Slot s1 = new Slot("900", "1400", "1500", 100, "John", "g1");
    Slot s2 = new Slot("910", "1500", "1600", 100, "J", "g2");
    Slot s3 = new Slot("930", "1600", "1700", 100, "Jack", "g3");
    Slot s4 = new Slot("950", "1700", "1800", 100, "Johnny", "g4");

    Date d1 = new Date();
    Booking b1 = new Booking("123", "121", "171", "confirmed", d1, "c1@gmail.com");
    Booking b2 = new Booking("173", "191", "131", "waitlisted", d1, "c2@gmail.com");
    Booking b3 = new Booking("113", "129", "173", "confirmed", d1, "c3@gmail.com");
    Booking b4 = new Booking("193", "127", "971", "waitlisted", d1, "c4@gmail.com");

    Customer customer1 = new Customer("c1@gmail.com", "c1", "Customer", "Vaishnavi", "0000", 22, "Kanpur");
    Customer customer2 = new Customer("c2@gmail.com", "c2", "Customer", "Anjali", "0000", 32, "Vadodara");
    Customer customer3 = new Customer("c3@gmail.com", "c3", "Customer", "Sudha", "0000", 42, "Kolkata");
    Customer customer4 = new Customer("c4@gmail.com", "c4", "Customer", "Aaishu", "0000", 52, "Mumbai");

    public CustomerDAOImpl() {
        gyms.add(gym1);
        gyms.add(gym2);
        gyms.add(gym3);
        gyms.add(gym4);

        slots.add(s1);
        slots.add(s2);
        slots.add(s3);
        slots.add(s4);

        bookings.add(b1);
        bookings.add(b2);
        bookings.add(b3);
        bookings.add(b4);

        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);
    }
    public List<GymCenter> getGymInCity(String city) {
        List<GymCenter> newGym = new ArrayList<GymCenter>();
        for (GymCenter gym : gyms) {
            if (gym.getAddress().equals(city)) {
                newGym.add(gym);
            }
        }
        return newGym;
    }
    public List<Slot> getSlotInGym(String gymId) {
        List<Slot> slotsOfGym = new ArrayList<>();
        for (Slot s : slots) {
            if (s.getGymId().equals(gymId)) {
                slotsOfGym.add(s);
            }
        }
        return slotsOfGym;
    }
    public boolean isSlotBooked(String slotId, Date date) {
        for (Slot s : slots) {
            if (s.getSlotId().equals(slotId)) {
                if (s.getNumOfSeats() <= s.getNumOfSeatsBooked())
                    return true;
                else
                    return false;
            }
        }
        return false;
    }
    public int bookSlot(String gymId, String slotId, String email, Date date) {
        List<Booking> tempBookings = new ArrayList<>();
        boolean flag=false;
        for(Booking booking:bookings)
        {
            if(booking.getCustomerEmail().equals(email) && booking.getType().equals("confirmed"))
            {
                flag=true;
                tempBookings.add(booking);
            }
        }
        if(flag)
        {
            boolean isDate=false;
            for(Booking booking:tempBookings)
            {
                if(booking.getDate().equals(date))
                {
                    isDate=true;
                    for(Slot slot:slots)
                    {
                        if(slot.getSlotId().equals(slotId) && !slot.getGymId().equals(gymId))
                        {
                            int num=slot.getNumOfSeatsBooked();
                            if(!isSlotBooked(slotId,date))
                            {
                                slot.setNumOfSeatsBooked(num--);
                                Booking getBooking = new Booking();
                                getBooking.setBookingId(IdGenerator.generateId("Booking"));
                                Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"confirmed",date,email);
                                bookings.add(tempBooking);
                                bookings.remove(booking);
                                return 0;
                            }
                            else
                            {
                                slot.setNumOfSeatsBooked(num--);
                                Booking getBooking = new Booking();
                                getBooking.setBookingId(IdGenerator.generateId("Booking"));
                                Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"waitlisted",date,email);
                                bookings.add(tempBooking);
                                return 1;
                            }
                        }
                    }
                    return 3;
                }
            }
            if(!isDate)
            {
                for(Slot slot:slots)
                {
                    if(slot.getSlotId().equals(slotId) && slot.getGymId().equals(gymId))
                    {
                        int num=slot.getNumOfSeatsBooked();
                        slot.setNumOfSeatsBooked(num--);
                        Booking getBooking = new Booking();
                        getBooking.setBookingId(IdGenerator.generateId("Booking"));
                        if(!isSlotBooked(slotId,date))
                        {
                            Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"confirmed",date,email);
                            bookings.add(tempBooking);
                            return 2;
                        }
                        else
                        {
                            Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"waitlisted",date,email);
                            bookings.add(tempBooking);
                            return 1;
                        }
                    }
                }
            }
        }
        else
        {
            for(Slot slot:slots)
            {
                if(slot.getSlotId().equals(slotId) && slot.getGymId().equals(gymId))
                {
                    int num=slot.getNumOfSeatsBooked();
                    slot.setNumOfSeatsBooked(num--);
                    Booking getBooking = new Booking();
                    getBooking.setBookingId(IdGenerator.generateId("Booking"));
                    if(!isSlotBooked(slotId,date))
                    {
                        Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"confirmed",date,email);
                        bookings.add(tempBooking);
                        return 2;
                    }
                    else
                    {
                        Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"waitlisted",date,email);
                        bookings.add(tempBooking);
                        return 1;
                    }
                }
            }
            return 3;
        }
        return 1;
    }

    public List<Booking> getBookings(String email) {

        List<Booking> customerBookings = new ArrayList<Booking>();

        for (Booking b : bookings) {
            if (b.getCustomerEmail().equals(email)) {
                customerBookings.add(b);
            }
        }
        return customerBookings;
    }

    public boolean cancelBooking(String bookingId, String email){

        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                bookings.remove(booking);
                System.out.println("Successfully cancelled your booking" );
                return true;
            }
        }
        return false;
    }
}