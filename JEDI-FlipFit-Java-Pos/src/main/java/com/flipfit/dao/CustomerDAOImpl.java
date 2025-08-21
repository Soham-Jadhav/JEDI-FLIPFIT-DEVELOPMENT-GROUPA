package com.flipfit.dao;
// For java.util.Date
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipfit.bean.Booking;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import com.flipfit.exception.*;
import com.flipfit.utils.DBUtils;
import com.flipfit.utils.IdGenerator;

public class CustomerDAOImpl implements CustomerDAOInterface{

    public List<GymCenter> getGymInCity(String city) {
        Connection connection = null;
        List<GymCenter> gyms = new ArrayList<GymCenter>();
        String query = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from GymCenter where address = ?";
        try {connection = DBUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city);
            //System.out.println(statement);
            // Step 3: Execute the query or update query
            ResultSet rs = statement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                GymCenter gym = new GymCenter();
                gym.setGymId(rs.getString("gymId"));
                gym.setGymName(rs.getString("gymName"));
                gym.setOwnerEmail(rs.getString("ownerEmail"));
                gym.setAddress(rs.getString("address"));
                gym.setSlotCount(rs.getInt("slotCount"));
                gym.setSeatsPerSlotCount(rs.getInt("seatsPerSlotCount"));
                gym.setVerified(rs.getBoolean("isVerified"));
                gyms.add(gym);
//	                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return gyms;
    }


    public List<Slot> getSlotInGym(String gymId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet output = null;
        List<Slot> slots = new ArrayList<>();
        String query = "SELECT slotId, gymId, startTime, endTime, trainer, numOfSeats, numOfSeatsBooked FROM Slot WHERE gymId = ?";

        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, gymId);
            output = statement.executeQuery();

            while (output.next()) {
                Slot slot = new Slot();
                slot.setSlotId(output.getString("slotId"));
                slot.setGymId(output.getString("gymId"));
                slot.setStartTime(String.valueOf(output.getTime("startTime"))); // Use getTime for Time columns
                slot.setEndTime(String.valueOf(output.getTime("endTime")));     // Use getTime
                slot.setTrainer(output.getString("trainer"));
                slot.setNumOfSeats(output.getInt("numOfSeats"));
                slot.setNumOfSeatsBooked(output.getInt("numOfSeatsBooked"));
                slots.add(slot);
            }
        } catch (SQLException sqlExcep) {
            System.err.println("SQL Error fetching slots for gym " + gymId + ": " + sqlExcep.getMessage());
            // It's generally better to log the exception and then throw a more specific application-level exception
            throw new RuntimeException("Database error retrieving slots: " + sqlExcep.getMessage(), sqlExcep);
        }
        return slots;
    }

    public boolean isSlotBooked(String slotId, Date date) {
        Connection connection = null;
        String query = "select isVerified from Booking where slotId=? and  numOfSeats <= numOfSeatsBooked ";
        try {connection = DBUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, slotId);
            //System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            printSQLException(e);
        }

        return false;
    }


    public int bookSlot(String gymId, String slotId, String customerEmail,  Date date) {
        Connection connection = null;
        // Generate bookingId
        String type = "confirmed";
        int flag=0;
        String bookingId = IdGenerator.generateId("Booking");
        if(isSlotBooked(slotId, date)) {
            type = "Waiting";
            flag = 1;
        }

        String query = "INSERT INTO Booking (bookingId,slotId,gymId,type,date,customerEmail) values(?, ?, ?, ?, ?, ?)";
        try {connection = DBUtils.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, bookingId);
            statement.setString(2, slotId);
            statement.setString(3, gymId);
            statement.setString(4, type);
            statement.setDate(5, new java.sql.Date(date.getTime()));
            statement.setString(6, customerEmail);
            statement.executeUpdate();
            System.out.println("-----------------------------------------------");
        } catch (SQLException sqlExcep) {
            printSQLException(sqlExcep);
        }
        return flag;
    }
    //get customer's bookings.
    public List<Booking> getBookings(String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet output = null;
        List<Booking> customerBookings = new ArrayList<>();
        String query = "SELECT bookingId, slotId, gymId, type, date, customerEmail FROM Booking WHERE customerEmail = ?";

        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            output = statement.executeQuery();

            while (output.next()) {
                Booking booking = new Booking();
                booking.setBookingId(output.getString("bookingId"));
                booking.setSlotId(output.getString("slotId"));
                booking.setGymId(output.getString("gymId"));
                booking.setType(output.getString("type"));
                booking.setDate(output.getDate("date")); // Convert SQL Date to Util Date
                booking.setCustomerEmail(output.getString("customerEmail"));
                customerBookings.add(booking);
            }
        } catch (SQLException sqlExcep) {
            System.err.println("SQL Error fetching bookings for email " + email + ": " + sqlExcep.getMessage());
            // It's generally better to log the exception and then throw a more specific application-level exception
            throw new RuntimeException("Database error retrieving bookings: " + sqlExcep.getMessage(), sqlExcep);
        }
        return customerBookings;
    }

    public boolean cancelBooking(String bookingId, String email) {
        Connection connection = null;
        String query = "Delete from Booking where customerEmail = ? and bookingId = ?";
        try {connection = DBUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, bookingId);
            int rowsAffected = statement.executeUpdate(); // Get the number of rows deleted

            if (rowsAffected > 0) {
                System.out.println("---------------------Successfully Canceled Booking--------------------------");
                return true; // Booking existed and was deleted
            } else {
                System.out.println("No booking found for customer " + email + " and booking id " + bookingId + ". Nothing cancelled.");
                return false; // No booking matched the criteria
            }
            //System.out.println("---------------------Successfully Canceled Booking--------------------------");
        } catch (SQLException sqlExcep) {
            printSQLException(sqlExcep);
            return  false;
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}

























//package com.flipfit.dao;
//import java.util.*;
//import com.flipfit.utils.IdGenerator;
//import com.flipfit.bean.GymCenter;
//import com.flipfit.bean.Slot;
//import com.flipfit.bean.Booking;
//import com.flipfit.bean.Customer;
//public class CustomerDAOImpl implements CustomerDAOInterface{
//    private static List<GymCenter> gyms = new ArrayList<>();
//    private static List<Slot> slots = new ArrayList<>();
//    private static List<Booking> bookings = new ArrayList<>();
//    private static List<Customer> customers = new ArrayList<>();
//
//    GymCenter gym1 = new GymCenter("g1", "gym1", "gymowner1@gmail.com", "Kanpur", 2, 5, true);
//    GymCenter gym2 = new GymCenter("g2", "gym2", "gymowner2@gmail.com", "Hyderabad", 3, 5, true);
//    GymCenter gym3 = new GymCenter("g3", "gym3", "gymowner3@gmail.com", "Bangalore", 2, 3, true);
//    GymCenter gym4 = new GymCenter("g4", "gym4", "gymowner4@gmail.com", "Cochin", 6, 5, true);
//
//    Slot s1 = new Slot("900", "1400", "1500", 100, "John", "g1");
//    Slot s2 = new Slot("910", "1500", "1600", 100, "J", "g2");
//    Slot s3 = new Slot("930", "1600", "1700", 100, "Jack", "g3");
//    Slot s4 = new Slot("950", "1700", "1800", 100, "Johnny", "g4");
//
//    Date d1 = new Date();
//    Booking b1 = new Booking("123", "121", "171", "confirmed", d1, "c1@gmail.com");
//    Booking b2 = new Booking("173", "191", "131", "waitlisted", d1, "c2@gmail.com");
//    Booking b3 = new Booking("113", "129", "173", "confirmed", d1, "c3@gmail.com");
//    Booking b4 = new Booking("193", "127", "971", "waitlisted", d1, "c4@gmail.com");
//
//    Customer customer1 = new Customer("c1@gmail.com", "c1", "Customer", "Vaishnavi", "0000", 22, "Kanpur");
//    Customer customer2 = new Customer("c2@gmail.com", "c2", "Customer", "Anjali", "0000", 32, "Vadodara");
//    Customer customer3 = new Customer("c3@gmail.com", "c3", "Customer", "Sudha", "0000", 42, "Kolkata");
//    Customer customer4 = new Customer("c4@gmail.com", "c4", "Customer", "Aaishu", "0000", 52, "Mumbai");
//
//    public CustomerDAOImpl() {
//        gyms.add(gym1);
//        gyms.add(gym2);
//        gyms.add(gym3);
//        gyms.add(gym4);
//
//        slots.add(s1);
//        slots.add(s2);
//        slots.add(s3);
//        slots.add(s4);
//
//        bookings.add(b1);
//        bookings.add(b2);
//        bookings.add(b3);
//        bookings.add(b4);
//
//        customers.add(customer1);
//        customers.add(customer2);
//        customers.add(customer3);
//        customers.add(customer4);
//    }
//    public List<GymCenter> getGymInCity(String city) {
//        List<GymCenter> newGym = new ArrayList<GymCenter>();
//        for (GymCenter gym : gyms) {
//            if (gym.getAddress().equals(city)) {
//                newGym.add(gym);
//            }
//        }
//        return newGym;
//    }
//    public List<Slot> getSlotInGym(String gymId) {
//        List<Slot> slotsOfGym = new ArrayList<>();
//        for (Slot s : slots) {
//            if (s.getGymId().equals(gymId)) {
//                slotsOfGym.add(s);
//            }
//        }
//        return slotsOfGym;
//    }
//    public boolean isSlotBooked(String slotId, Date date) {
//        for (Slot s : slots) {
//            if (s.getSlotId().equals(slotId)) {
//                if (s.getNumOfSeats() <= s.getNumOfSeatsBooked())
//                    return true;
//                else
//                    return false;
//            }
//        }
//        return false;
//    }
//    public int bookSlot(String gymId, String slotId, String email, Date date) {
//        List<Booking> tempBookings = new ArrayList<>();
//        boolean flag=false;
//        for(Booking booking:bookings)
//        {
//            if(booking.getCustomerEmail().equals(email) && booking.getType().equals("confirmed"))
//            {
//                flag=true;
//                tempBookings.add(booking);
//            }
//        }
//        if(flag)
//        {
//            boolean isDate=false;
//            for(Booking booking:tempBookings)
//            {
//                if(booking.getDate().equals(date))
//                {
//                    isDate=true;
//                    for(Slot slot:slots)
//                    {
//                        if(slot.getSlotId().equals(slotId) && !slot.getGymId().equals(gymId))
//                        {
//                            int num=slot.getNumOfSeatsBooked();
//                            if(!isSlotBooked(slotId,date))
//                            {
//                                slot.setNumOfSeatsBooked(num--);
//                                Booking getBooking = new Booking();
//                                getBooking.setBookingId(IdGenerator.generateId("Booking"));
//                                Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"confirmed",date,email);
//                                bookings.add(tempBooking);
//                                bookings.remove(booking);
//                                return 0;
//                            }
//                            else
//                            {
//                                slot.setNumOfSeatsBooked(num--);
//                                Booking getBooking = new Booking();
//                                getBooking.setBookingId(IdGenerator.generateId("Booking"));
//                                Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"waitlisted",date,email);
//                                bookings.add(tempBooking);
//                                return 1;
//                            }
//                        }
//                    }
//                    return 3;
//                }
//            }
//            if(!isDate)
//            {
//                for(Slot slot:slots)
//                {
//                    if(slot.getSlotId().equals(slotId) && slot.getGymId().equals(gymId))
//                    {
//                        int num=slot.getNumOfSeatsBooked();
//                        slot.setNumOfSeatsBooked(num--);
//                        Booking getBooking = new Booking();
//                        getBooking.setBookingId(IdGenerator.generateId("Booking"));
//                        if(!isSlotBooked(slotId,date))
//                        {
//                            Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"confirmed",date,email);
//                            bookings.add(tempBooking);
//                            return 2;
//                        }
//                        else
//                        {
//                            Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"waitlisted",date,email);
//                            bookings.add(tempBooking);
//                            return 1;
//                        }
//                    }
//                }
//            }
//        }
//        else
//        {
//            for(Slot slot:slots)
//            {
//                if(slot.getSlotId().equals(slotId) && slot.getGymId().equals(gymId))
//                {
//                    int num=slot.getNumOfSeatsBooked();
//                    slot.setNumOfSeatsBooked(num--);
//                    Booking getBooking = new Booking();
//                    getBooking.setBookingId(IdGenerator.generateId("Booking"));
//                    if(!isSlotBooked(slotId,date))
//                    {
//                        Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"confirmed",date,email);
//                        bookings.add(tempBooking);
//                        return 2;
//                    }
//                    else
//                    {
//                        Booking tempBooking=new Booking(getBooking.getBookingId(),slotId,slot.getGymId(),"waitlisted",date,email);
//                        bookings.add(tempBooking);
//                        return 1;
//                    }
//                }
//            }
//            return 3;
//        }
//        return 1;
//    }
//
//    public List<Booking> getBookings(String email) {
//
//        List<Booking> customerBookings = new ArrayList<Booking>();
//
//        for (Booking b : bookings) {
//            if (b.getCustomerEmail().equals(email)) {
//                customerBookings.add(b);
//            }
//        }
//        return customerBookings;
//    }
//
//    public boolean cancelBooking(String bookingId, String email){
//
//        for (Booking booking : bookings) {
//            if (booking.getBookingId().equals(bookingId)) {
//                bookings.remove(booking);
//                System.out.println("Successfully cancelled your booking" );
//                return true;
//            }
//        }
//        return false;
//    }
//}