package com.flipfit.dao;
import java.util.*;
import com.flipfit.bean.*;
import com.flipfit.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Dhruv
 * Data Access Object (DAO) implementation for Admin-related operations.
 * This class handles all interactions with the database for the administrator role,
 * including retrieving, viewing, and approving gym owners and gym centers.
 */
public class AdminDAOImpl implements AdminDAOInterface {

    // Connection object to establish a link to the database.
    Connection connection = null;

    /**
     * Retrieves a list of all gym owners from the database.
     *
     * @return A List of all GymOwner objects.
     */
    public List<GymOwner> getAllGymOwners() {
        List<GymOwner> gymOwners = new ArrayList<GymOwner>();
        String query = "select email, name, phoneNum, aadharNum, panNum, isVerified from GymOwner";
        try {
            // Establish a connection to the database.
            connection = DBUtils.getConnection();

            // Create a PreparedStatement to execute the SQL query.
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Execute the query and get the result set.
            ResultSet rs = preparedStatement.executeQuery();

            // Process the result set and populate the list of GymOwner objects.
            while (rs.next()) {
                GymOwner gymOwner = new GymOwner();
                gymOwner.setEmail(rs.getString("email"));
                gymOwner.setName(rs.getString("name"));
                gymOwner.setPhoneNumber(rs.getString("phoneNum"));
                gymOwner.setAadharNumber(rs.getString("aadharNum"));
                gymOwner.setPanNumber(rs.getString("panNum"));
                gymOwner.setVerified(rs.getBoolean("isVerified"));
                gymOwners.add(gymOwner);
            }
        } catch (SQLException e) {
            // Handle SQL exceptions by printing detailed information.
            printSQLException(e);
        }
        // The connection is closed automatically by the try-with-resource statement in DBUtils.
        return gymOwners;
    };

    /**
     * Retrieves a list of all gym centers from the database.
     *
     * @return A List of all GymCenter objects.
     */
    public List<GymCenter> getAllGyms() {
        Connection connection = null;
        List<GymCenter> gyms = new ArrayList<GymCenter>();
        String query = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from GymCenter";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();

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
            printSQLException(e);
        }
        return gyms;
    };

    /**
     * Retrieves a list of all gym owner requests that are pending approval.
     * This is identified by the 'isVerified' column being false.
     *
     * @return A List of GymOwner objects with isVerified set to false.
     */
    public List<GymOwner> getPendingGymOwnerRequests() {
        Connection connection = null;
        List<GymOwner> gymOwners = new ArrayList<GymOwner>();
        String query = "select email, name, phoneNum, aadharNum, panNum, isVerified from GymOwner where isVerified = ?;";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the first parameter of the prepared statement to false.
            preparedStatement.setBoolean(1, false);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                GymOwner gymOwner = new GymOwner();
                gymOwner.setEmail(rs.getString("email"));
                gymOwner.setName(rs.getString("name"));
                gymOwner.setPhoneNumber(rs.getString("phoneNum"));
                gymOwner.setAadharNumber(rs.getString("aadharNum"));
                gymOwner.setPanNumber(rs.getString("panNum"));
                gymOwner.setVerified(rs.getBoolean("isVerified"));
                gymOwners.add(gymOwner);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return gymOwners;
    };

    /**
     * Retrieves a list of all gym center requests that are pending approval.
     * This is identified by the 'isVerified' column being false.
     *
     * @return A List of GymCenter objects with isVerified set to false.
     */
    public List<GymCenter> getPendingGymRequests() {
        Connection connection = null;
        List<GymCenter> gyms = new ArrayList<GymCenter>();
        String query = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from GymCenter where isVerified = ?;";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the first parameter of the prepared statement to false.
            preparedStatement.setBoolean(1, false);
            ResultSet rs = preparedStatement.executeQuery();

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
            printSQLException(e);
        }
        return gyms;
    };

    /**
     * Approves a single gym owner request by setting their 'isVerified' status to true.
     *
     * @param gymOwnerEmail The email of the gym owner to be approved.
     */
    public void approveSingleOwnerRequest(String gymOwnerEmail) {
        Connection connection = null;
        String SQL_APPROVE_GYM_OWNER_BY_ID="update GymOwner set isVerified=1 WHERE email=?;";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_APPROVE_GYM_OWNER_BY_ID);

            // Set the gym owner's email as a parameter in the update query.
            preparedStatement.setString(1, gymOwnerEmail);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    };

    /**
     * Approves all pending gym owner requests by setting their 'isVerified' status to true.
     */
    public void approveAllOwnerRequest() {
        Connection connection = null;
        String SQL_APPROVE_ALL_GYMS="update GymOwner set isVerified=1;";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_APPROVE_ALL_GYMS);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    };

    /**
     * Approves a single gym center request by setting its 'isVerified' status to true.
     *
     * @param gymId The unique ID of the gym center to be approved.
     */
    public void approveSingleGymRequest(String gymId) {
        Connection connection = null;
        String SQL_APPROVE_GYM_BY_ID="update GymCenter set isVerified=1 where gymId = ?;";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_APPROVE_GYM_BY_ID);

            // Set the gym ID as a parameter in the update query.
            preparedStatement.setString(1, gymId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    };

    /**
     * Approves all pending gym center requests by setting their 'isVerified' status to true.
     */
    public void approveAllGymRequest() {
        Connection connection = null;
        String SQL_APPROVE_ALL_GYMS="update GymCenter set isVerified=1;";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_APPROVE_ALL_GYMS);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    };

    /**
     * A helper method to print detailed information about a SQLException.
     * This is useful for debugging database-related errors.
     *
     * @param ex The SQLException to be analyzed.
     */
    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
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