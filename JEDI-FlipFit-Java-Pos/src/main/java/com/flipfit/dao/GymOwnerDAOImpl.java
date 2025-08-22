package com.flipfit.dao;

import com.flipfit.bean.GymOwner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.flipfit.bean.User;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import com.flipfit.utils.DBUtils;

/**
 * @author Dhruv
 * Data Access Object (DAO) implementation for gym owner operations.
 * This class handles all interactions with the database for a gym owner,
 * including adding/editing gym centers and managing time slots.
 */
public class GymOwnerDAOImpl implements GymOwnerDAOInterface{

    /**
     * Adds a new gym center to the database.
     *
     * @param gymDetails The GymCenter object containing the details of the gym to be added.
     */
    public void addGym(GymCenter gymDetails) {
        Connection connection = null;
        String INSERT_GYM_SQL = "INSERT INTO GymCenter"
                + "  (gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified) VALUES "
                + " (?, ?, ?, ?, ?, ?, ?);";

        try {
            // Establish a connection to the database.
            connection = DBUtils.getConnection();

            // Create a prepared statement to execute the SQL query safely.
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GYM_SQL);
            preparedStatement.setString(1, gymDetails.getGymId());
            preparedStatement.setString(2, gymDetails.getGymName());
            preparedStatement.setString(3, gymDetails.getOwnerEmail());
            preparedStatement.setString(4, gymDetails.getAddress());
            preparedStatement.setInt(5, gymDetails.getSlotCount());
            preparedStatement.setInt(6, gymDetails.getSeatsPerSlotCount());
            preparedStatement.setBoolean(7, gymDetails.isVerified());

            // Execute the update query (INSERT).
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Print detailed SQL exception information for debugging.
            e.printStackTrace();
        }
        System.out.println("Gym added successfully: " + gymDetails.getGymName());
    }

    /**
     * Edits the details of an existing gym center in the database.
     *
     * @param gymDetails The GymCenter object with the updated details.
     */
    public void editGym(GymCenter gymDetails) {
        Connection connection = null;
        String INSERT_GYM_SQL = "update GymCenter"
                + "  set gymId = ?, gymName = ?, ownerEmail = ?, address = ?, slotCount = ?, seatsPerSlotCount = ?, isVerified = ? where gymId = ?;";
        System.out.println(INSERT_GYM_SQL);

        try {
            connection = DBUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GYM_SQL);
            preparedStatement.setString(1, gymDetails.getGymId());
            preparedStatement.setString(2, gymDetails.getGymName());
            preparedStatement.setString(3, gymDetails.getOwnerEmail());
            preparedStatement.setString(4, gymDetails.getAddress());
            preparedStatement.setInt(5, gymDetails.getSlotCount());
            preparedStatement.setInt(6, gymDetails.getSeatsPerSlotCount());
            preparedStatement.setBoolean(7, gymDetails.isVerified());
            preparedStatement.setString(8, gymDetails.getGymId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Gym Edited successfully: " + gymDetails.getGymName());
    }

    /**
     * Adds a new time slot to a gym in the database.
     *
     * @param slot The Slot object containing the details of the slot to be added.
     */
    public void addSlot(Slot slot) {
        Connection connection = null;
        String INSERT_SLOT_SQL = "INSERT INTO Slot" + "  (slotId, gymId, startTime, endTime, trainer, numOfSeats, numOfSeatsBooked) VALUES "
                + " (?, ?, ?, ?, ?, ?, ?);";

        try {
            connection = DBUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SLOT_SQL);
            preparedStatement.setString(1, slot.getSlotId());
            preparedStatement.setString(2, slot.getGymId());
            preparedStatement.setString(3, slot.getStartTime());
            preparedStatement.setString(4, slot.getEndTime());
            preparedStatement.setString(5, slot.getTrainer());
            preparedStatement.setInt(6, slot.getNumOfSeats());
            preparedStatement.setInt(7, slot.getNumOfSeatsBooked());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Gym added successfully: " );
    }

    /**
     * Retrieves a list of all gym centers owned by a specific gym owner.
     *
     * @param gymOwnerId The unique ID (email) of the gym owner.
     * @return A List of GymCenter objects associated with the gym owner.
     */
    public List<GymCenter> getGymsOfGymOwner(String gymOwnerId) {
        Connection connection = null;
        List<GymCenter> gyms = new ArrayList<GymCenter>();
        String query = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from GymCenter where ownerEmail =  ?";
        try {
            connection = DBUtils.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gymOwnerId);
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
            e.printStackTrace();
        }
        return gyms;
    }

}