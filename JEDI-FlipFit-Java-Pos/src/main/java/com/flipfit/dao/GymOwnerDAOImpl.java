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

public class GymOwnerDAOImpl implements GymOwnerDAOInterface{
    //    private static List<GymCenter> gyms = new ArrayList<>();
//    private static List<Slot> slots = new ArrayList<>();
//    public GymOwnerDAOImpl(){
//        GymCenter gym1 = new GymCenter("g1", "gym1", "gymowner1@gmail.com", "Kanpur", 2, 5, true);
//        GymCenter gym2 = new GymCenter("g2", "gym2", "gymowner2@gmail.com", "Hyderabad", 3, 5, true);
//        GymCenter gym3 = new GymCenter("g3", "gym3", "gymowner3@gmail.com", "Bangalore", 2, 3, true);
//        GymCenter gym4 = new GymCenter("g4", "gym4", "gymowner4@gmail.com", "Cochin", 6, 5, true);
//        gyms.add(gym1);
//        gyms.add(gym2);
//        gyms.add(gym3);
//        gyms.add(gym4);
//
//        Slot s1 = new Slot("900", "1400", "1500", 100, "John", "g1");
//        Slot s2 = new Slot("910", "1500", "1600", 100, "J", "g2");
//        Slot s3 = new Slot("930", "1600", "1700", 100, "Jack", "g3");
//        Slot s4 = new Slot("950", "1700", "1800", 100, "Johnny", "g4");
//        slots.add(s1);
//        slots.add(s2);
//        slots.add(s3);
//        slots.add(s4);
//
//    }
//    public void addGym(GymCenter gymDetails) {
//        // Check for duplicate gym ID (optional but recommended)
//        for (GymCenter existing : gyms) {
//            if (existing.getGymId().equalsIgnoreCase(gymDetails.getGymId())) {
//                System.out.println("Gym with ID " + gymDetails.getGymId() + " already exists.");
//                return;
//            }
//        }
//
//        // Add gym to collection
//        gyms.add(gymDetails);
//        System.out.println("Gym added successfully: " + gymDetails.getGymName());
//    }
    public void addGym(GymCenter gymDetails) {
        Connection connection = null;
        String INSERT_GYM_SQL = "INSERT INTO GymCenter"
                + "  (gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified) VALUES "
                + " (?, ?, ?, ?, ?, ?, ?);";
        //System.out.println(INSERT_GYM_SQL);
        // Step 1: Establishing a Connection
        try {connection = DBUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GYM_SQL);
            preparedStatement.setString(1, gymDetails.getGymId());
            preparedStatement.setString(2, gymDetails.getGymName());
            preparedStatement.setString(3, gymDetails.getOwnerEmail());
            preparedStatement.setString(4, gymDetails.getAddress());
            preparedStatement.setInt(5, gymDetails.getSlotCount());
            preparedStatement.setInt(6, gymDetails.getSeatsPerSlotCount());
            preparedStatement.setBoolean(7, gymDetails.isVerified());

            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information

        }
        System.out.println("Gym added successfully: " + gymDetails.getGymName());
    }



    //    public void editGym(GymCenter gymDetails) {
//        boolean updated = false;
//
//        for (GymCenter gym : gyms) {
//            if (gym.getGymId().equalsIgnoreCase(gymDetails.getGymId())) {
//                gym.setGymName(gymDetails.getGymName());
//                gym.setAddress(gymDetails.getAddress());
//                gym.setSlotCount(gymDetails.getSlotCount());
//                gym.setSeatsPerSlotCount(gymDetails.getSeatsPerSlotCount());
//                gym.setVerified(gymDetails.isVerified());
//                updated = true;
//                System.out.println("Gym details updated successfully for ID: " + gymDetails.getGymId());
//                break;
//            }
//        }
//        if (!updated) {
//            System.out.println("No gym found with ID: " + gymDetails.getGymId());
//        }
//    }
    public void editGym(GymCenter gymDetails) {
        Connection connection = null;
        String INSERT_GYM_SQL = "update GymCenter"
                + "  set gymId = ?, gymName = ?, ownerEmail = ?, address = ?, slotCount = ?, seatsPerSlotCount = ?, isVerified = ? where gymId = ?;";
        System.out.println(INSERT_GYM_SQL);
        // Step 1: Establishing a Connection
        try {connection = DBUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GYM_SQL);
            preparedStatement.setString(1, gymDetails.getGymId());
            preparedStatement.setString(2, gymDetails.getGymName());
            preparedStatement.setString(3, gymDetails.getOwnerEmail());
            preparedStatement.setString(4, gymDetails.getAddress());
            preparedStatement.setInt(5, gymDetails.getSlotCount());
            preparedStatement.setInt(6, gymDetails.getSeatsPerSlotCount());
            preparedStatement.setBoolean(7, gymDetails.isVerified());
            preparedStatement.setString(8, gymDetails.getGymId());

            // System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information

        }
        System.out.println("Gym Edited successfully: " + gymDetails.getGymName());
    }

    //    public void addSlot(Slot slot) {
//        // Check if slot ID already exists (optional safety)
//        for (Slot existing : slots) {
//            if (existing.getSlotId().equalsIgnoreCase(slot.getSlotId())) {
//                System.out.println("Slot with ID " + slot.getSlotId() + " already exists.");
//                return;
//            }
//        }
//
//        // Add the slot to the list
//        slots.add(slot);
//        for (GymCenter gym : gyms) {
//            if (gym.getGymId().equalsIgnoreCase(slot.getGymId())) {
//                gym.setSlotCount(gym.getSlotCount()+1);
//                break;
//            }
//        }
//        System.out.println("Slot added successfully for gym: " + slot.getGymId() + ", slot ID: " + slot.getSlotId());
//    }
    public void addSlot(Slot slot) {
        Connection connection = null;
        String INSERT_SLOT_SQL = "INSERT INTO Slot" + "  (slotId, gymId, startTime, endTime, trainer, numOfSeats, numOfSeatsBooked) VALUES "
                + " (?, ?, ?, ?, ?, ?, ?);";
        //System.out.println(INSERT_SLOT_SQL);
        // Step 1: Establishing a Connection
        try {connection = DBUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SLOT_SQL);
            preparedStatement.setString(1, slot.getSlotId());
            preparedStatement.setString(2, slot.getGymId());
            preparedStatement.setString(3, slot.getStartTime());
            preparedStatement.setString(4, slot.getEndTime());
            preparedStatement.setString(5, slot.getTrainer());
            preparedStatement.setInt(6, slot.getNumOfSeats());
            preparedStatement.setInt(7, slot.getNumOfSeatsBooked());

            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {

            // print SQL exception information
        }
        System.out.println("Gym added successfully: " );
    }

    //    public List<GymCenter> getGymsOfGymOwner(String gymOwnerEmail) {
//        List<GymCenter> ownerGyms = new ArrayList<>();
//
//        for (GymCenter gym : gyms) {
//            if (gym.getOwnerEmail().equalsIgnoreCase(gymOwnerEmail)) {
//                ownerGyms.add(gym);
//            }
//        }
//
//        if (ownerGyms.isEmpty()) {
//            System.out.println("No gyms found for gym owner: " + gymOwnerEmail);
//        }
//
//        return ownerGyms;
//    }
    public List<GymCenter> getGymsOfGymOwner(String gymOwnerId) {
        Connection connection = null;
        List<GymCenter> gyms = new ArrayList<GymCenter>();
        String query = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from GymCenter where ownerEmail =  ?";
        try {connection = DBUtils.getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, gymOwnerId);
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                GymCenter gym = new GymCenter();
                gym.setGymId(rs.getString("gymId"));
                gym.setGymName(rs.getString("gymName"));
                gym.setOwnerEmail(rs.getString("gymOwnerEmail"));
                gym.setAddress(rs.getString("address"));
                gym.setSlotCount(rs.getInt("slotCount"));
                gym.setSeatsPerSlotCount(rs.getInt("seatsPerSlot"));
                gym.setVerified(rs.getBoolean("isVerified"));
                gyms.add(gym);
                // System.out.println(id + "," + name + "," + email + "," + country + "," + password);
            }
        } catch (SQLException e) {

        }
        // Step 4: try-with-resource statement will auto close the connection.
        return gyms;
    }

}