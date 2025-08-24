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

/**
 * @author Dhruv
 * Data Access Object (DAO) implementation for customer-related operations.
 * This class handles all interactions with the database for the customer role,
 * including searching for gyms, booking slots, and managing bookings.
 */
public class CustomerDAOImpl implements CustomerDAOInterface{

    /**
     * Retrieves a list of gyms located in a specific city from the database.
     *
     * @param city The name of the city to search for gyms.
     * @return A List of GymCenter objects.
     */
    public List<GymCenter> getGymInCity(String city) {
        Connection connection = null;
        List<GymCenter> gyms = new ArrayList<GymCenter>();
        String query = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from GymCenter where address = ?";
        try {
            // Establish a connection to the database.
            connection = DBUtils.getConnection();

            // Create a PreparedStatement to execute the SQL query with a parameter.
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city);

            // Execute the query and get the result set.
            ResultSet rs = statement.executeQuery();

            // Process the result set and populate the list of GymCenter objects.
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
            }
        } catch (SQLException e) {
            // Handle SQL exceptions by printing detailed information.
            printSQLException(e);
        }
        return gyms;
    }

    /**
     * Retrieves a list of available time slots for a specific gym.
     *
     * @param gymId The unique ID of the gym center.
     * @return A List of Slot objects.
     */
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

    /**
     * Checks if a specific time slot is fully booked for a given date.
     *
     * @param slotId The unique ID of the time slot.
     * @param date The date for which to check the booking status.
     * @return {@code true} if the slot is booked, {@code false} otherwise.
     */
    public boolean isSlotBooked(String slotId, Date date) {
        Connection connection = null;
        String query = "select slotId from Slot where slotId=? and  numOfSeats <= numOfSeatsBooked ";
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

    /**
     * Books a time slot for a customer.
     * It checks for availability and either confirms the booking or adds it to a waiting list.
     *
     * @param gymId The unique ID of the gym.
     * @param slotId The unique ID of the slot to be booked.
     * @param customerEmail The email of the customer.
     * @param date The date of the booking.
     * @return An integer flag indicating the booking status (0: re-booked, 1: waitlisted, 2: confirmed, 3: not found).
     */
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
        String updateSlotQuery = "UPDATE Slot SET numOfSeatsBooked = numOfSeatsBooked + 1 WHERE slotId = ?";
        try {
            connection = DBUtils.getConnection();

            // Insert new booking
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, bookingId);
            statement.setString(2, slotId);
            statement.setString(3, gymId);
            statement.setString(4, type);
            statement.setDate(5, new java.sql.Date(date.getTime()));
            statement.setString(6, customerEmail);
            statement.executeUpdate();

            // Check if the booking is confirmed before updating the slot
            if (flag == 0) {
                PreparedStatement updateStatement = connection.prepareStatement(updateSlotQuery);
                updateStatement.setString(1, slotId);
                updateStatement.executeUpdate();
            }

            System.out.println("-----------------------------------------------");
        } catch (SQLException sqlExcep) {
            printSQLException(sqlExcep);
        }
        return flag;
    }

    /**
     * Retrieves all bookings made by a specific customer.
     *
     * @param email The email of the customer.
     * @return A List of Booking objects associated with the customer.
     */
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
            throw new RuntimeException("Database error retrieving bookings: " + sqlExcep.getMessage(), sqlExcep);
        }
        return customerBookings;
    }

    /**
     * Cancels an existing booking for a customer.
     *
     * @param bookingId The unique ID of the booking to be cancelled.
     * @param email The email of the customer.
     * @return {@code true} if the booking was successfully cancelled, {@code false} otherwise.
     */
    public boolean cancelBooking(String bookingId, String email) {
        Connection connection = null;
        PreparedStatement statement = null;
        String slotId = null;
        boolean bookingDeleted = false; // Initialize flag

        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Step 1: Fetch the slotId from the booking before deleting
            String fetchSlotIdQuery = "SELECT slotId FROM Booking WHERE customerEmail = ? AND bookingId = ?";
            statement = connection.prepareStatement(fetchSlotIdQuery);
            statement.setString(1, email);
            statement.setString(2, bookingId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                slotId = rs.getString("slotId");
            } else {
                System.out.println("No booking found for customer " + email + " and booking id " + bookingId + ". Nothing cancelled.");
                return false; // No booking matched the criteria
            }

            // Step 2: Delete the booking
            String deleteBookingQuery = "DELETE FROM Booking WHERE customerEmail = ? AND bookingId = ?";
            statement = connection.prepareStatement(deleteBookingQuery);
            statement.setString(1, email);
            statement.setString(2, bookingId);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                bookingDeleted = true; // Set flag to true if deletion was successful
            }

            // Step 3: Check the flag and decrement numOfSeatsBooked
            if (bookingDeleted) {
                String updateSlotQuery = "UPDATE Slot SET numOfSeatsBooked = numOfSeatsBooked - 1 WHERE slotId = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateSlotQuery);
                updateStatement.setString(1, slotId);
                updateStatement.executeUpdate();

                // Step 4: Fetch the total seats and booked seats after the decrement
                String fetchSlotDetailsQuery = "SELECT numOfSeats, numOfSeatsBooked FROM Slot WHERE slotId = ?";
                PreparedStatement fetchSlotDetailsStatement = connection.prepareStatement(fetchSlotDetailsQuery);
                fetchSlotDetailsStatement.setString(1, slotId);
                ResultSet slotDetailsRs = fetchSlotDetailsStatement.executeQuery();

                if (slotDetailsRs.next()) {
                    int numOfSeats = slotDetailsRs.getInt("numOfSeats");
                    int numOfSeatsBooked = slotDetailsRs.getInt("numOfSeatsBooked");
                    int availableSeats = numOfSeats - numOfSeatsBooked;

                    if (availableSeats > 0) {
                        // Step 5: Update the 'Waiting' bookings to 'confirmed' based on available seats
                        String updateWaitlistQuery = "UPDATE Booking SET type = 'confirmed' WHERE slotId = ? AND type = 'Waiting' ORDER BY date ASC LIMIT ?";
                        PreparedStatement updateWaitlistStatement = connection.prepareStatement(updateWaitlistQuery);
                        updateWaitlistStatement.setString(1, slotId);
                        updateWaitlistStatement.setInt(2, availableSeats);
                        int confirmedBookingsCount = updateWaitlistStatement.executeUpdate();

                        // Step 6: Update numOfSeatsBooked by adding the number of confirmed bookings
                        if (confirmedBookingsCount > 0) {
                            String finalUpdateSlotQuery = "UPDATE Slot SET numOfSeatsBooked = numOfSeatsBooked + ? WHERE slotId = ?";
                            PreparedStatement finalUpdateStatement = connection.prepareStatement(finalUpdateSlotQuery);
                            finalUpdateStatement.setInt(1, confirmedBookingsCount);
                            finalUpdateStatement.setString(2, slotId);
                            finalUpdateStatement.executeUpdate();
                            System.out.println(confirmedBookingsCount + " waitlisted bookings have been confirmed.");
                        }
                    }
                }

                connection.commit(); // Commit the transaction
                System.out.println("---------------------Successfully Canceled Booking--------------------------");
                return true;
            } else {
                connection.rollback(); // Rollback if delete failed
                return false;
            }

        } catch (SQLException sqlExcep) {
            try {
                if (connection != null) {
                    connection.rollback(); // Rollback on exception
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
            printSQLException(sqlExcep);
            return  false;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                printSQLException(e);
            }
        }
    }

    /**
     * A helper method to print detailed information about a SQLException.
     *
     * @param ex The SQLException to be analyzed.
     */
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