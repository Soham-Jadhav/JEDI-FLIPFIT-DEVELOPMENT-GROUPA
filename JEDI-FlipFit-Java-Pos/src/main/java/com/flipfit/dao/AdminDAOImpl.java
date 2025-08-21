
package com.flipfit.dao;
import java.util.*;
import com.flipfit.bean.*;
import com.flipfit.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdminDAOImpl implements AdminDAOInterface {
    Connection connection = null;
    public List<GymOwner> getAllGymOwners() {
        List<GymOwner> gymOwners = new ArrayList<GymOwner>();
        String query = "select email, name, phoneNum, aadharNum, panNum, isVerified from GymOwner";
        try {connection = DBUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                GymOwner gymOwner = new GymOwner();
                gymOwner.setEmail(rs.getString("email"));
                gymOwner.setName(rs.getString("name"));
                gymOwner.setPhoneNumber(rs.getString("phoneNum"));
                gymOwner.setAadharNumber(rs.getString("aadharNum"));
                gymOwner.setPanNumber(rs.getString("panNum"));
                gymOwner.setVerified(rs.getBoolean("isVerified"));
                gymOwners.add(gymOwner);
//	                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
        return gymOwners;
    };

    public List<GymCenter> getAllGyms() {
        Connection connection = null;
        List<GymCenter> gyms = new ArrayList<GymCenter>();
        String query = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from GymCenter";
        try {connection = DBUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

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
        // Step 4: try-with-resource statement will auto close the connection.
        return gyms;
    };

    public List<GymOwner> getPendingGymOwnerRequests() {
        Connection connection = null;
        List<GymOwner> gymOwners = new ArrayList<GymOwner>();
        String query = "select email, name, phoneNum, aadharNum, panNum, isVerified from GymOwner where isVerified = ?;";
        try {connection = DBUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.setBoolean(1, false);
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                GymOwner gymOwner = new GymOwner();
                gymOwner.setEmail(rs.getString("email"));
                gymOwner.setName(rs.getString("name"));
                gymOwner.setPhoneNumber(rs.getString("phoneNum"));
                gymOwner.setAadharNumber(rs.getString("aadharNum"));
                gymOwner.setPanNumber(rs.getString("panNum"));
                gymOwner.setVerified(rs.getBoolean("isVerified"));
                gymOwners.add(gymOwner);
//	                System.out.println(id + "," + name + "," + email + "," + country + "," + password);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        // Step 4: try-with-resource statement will auto close the connection.
        return gymOwners;

    };

    public List<GymCenter> getPendingGymRequests() {
        Connection connection = null;
        List<GymCenter> gyms = new ArrayList<GymCenter>();
        String query = "select gymId, gymName, ownerEmail, address, slotCount, seatsPerSlotCount, isVerified from GymCenter where isVerified = ?;";
        try {connection = DBUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.setBoolean(1, false);
            ResultSet rs = preparedStatement.executeQuery();

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
        // Step 4: try-with-resource statement will auto close the connection.
        return gyms;

    };

    public void approveSingleOwnerRequest(String gymOwnerEmail) {
        Connection connection = null;
        String SQL_APPROVE_GYM_OWNER_BY_ID="update GymOwner set isVerified=1 WHERE email=?;";
        try {connection = DBUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_APPROVE_GYM_OWNER_BY_ID);
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.setString(1, gymOwnerEmail);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    };

    public void approveAllOwnerRequest() {
        Connection connection = null;
        String SQL_APPROVE_ALL_GYMS="update GymOwner set isVerified=1;";
        try {connection = DBUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_APPROVE_ALL_GYMS);
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    };

    public void approveSingleGymRequest(String gymId) {
        Connection connection = null;
        String SQL_APPROVE_GYM_BY_ID="update GymCenter set isVerified=1 where gymId = ?;";
        try {connection = DBUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_APPROVE_GYM_BY_ID);
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.setString(1, gymId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    };

    public void approveAllGymRequest() {
        Connection connection = null;
        String SQL_APPROVE_ALL_GYMS="update GymCenter set isVerified=1;";
        try {connection = DBUtils.getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_APPROVE_ALL_GYMS);
            //System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    };

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







//package com.flipfit.dao;
//import java.util.*;
//import com.flipfit.bean.GymOwner;
//import com.flipfit.bean.GymCenter;
//public class AdminDAOImpl implements AdminDAOInterface {
//    List<GymOwner> gymOwners = new ArrayList<>();
//    List<GymCenter> gyms = new ArrayList<>();
//    public AdminDAOImpl() {
//        adminCollection();
//    }
//    public void adminCollection() {
//        // Hardcoded GymOwner 1
//        GymOwner owner1 = new GymOwner();
//        owner1.setEmail("rahul.sharma@example.com");
//        owner1.setName("Rahul Sharma");
//        owner1.setPhoneNumber("9876543210");
//        owner1.setAadharNumber("123456789012");
//        owner1.setPanNumber("ABCDE1234F");
//        owner1.setVerified(true);
//        gymOwners.add(owner1);
//
//        // Hardcoded GymOwner 2
//        GymOwner owner2 = new GymOwner();
//        owner2.setEmail("anita.verma@example.com");
//        owner2.setName("Anita Verma");
//        owner2.setPhoneNumber("9123456780");
//        owner2.setAadharNumber("234567890123");
//        owner2.setPanNumber("BCDEA2345G");
//        owner2.setVerified(false);
//        gymOwners.add(owner2);
//
//        // Hardcoded GymOwner 3
//        GymOwner owner3 = new GymOwner();
//        owner3.setEmail("rohit.kapoor@example.com");
//        owner3.setName("Rohit Kapoor");
//        owner3.setPhoneNumber("9988776655");
//        owner3.setAadharNumber("345678901234");
//        owner3.setPanNumber("CDEAB3456H");
//        owner3.setVerified(true);
//        gymOwners.add(owner3);
//
//        // Hardcoded GymOwner 4
//        GymOwner owner4 = new GymOwner();
//        owner4.setEmail("meena.joshi@example.com");
//        owner4.setName("Meena Joshi");
//        owner4.setPhoneNumber("9012345678");
//        owner4.setAadharNumber("456789012345");
//        owner4.setPanNumber("DEABC4567I");
//        owner4.setVerified(false);
//        gymOwners.add(owner4);
//
//        // Hardcoded GymOwner 5
//        GymOwner owner5 = new GymOwner();
//        owner5.setEmail("vikas.singh@example.com");
//        owner5.setName("Vikas Singh");
//        owner5.setPhoneNumber("9345678901");
//        owner5.setAadharNumber("567890123456");
//        owner5.setPanNumber("EABCD5678J");
//        owner5.setVerified(true);
//        gymOwners.add(owner5);
//
//        // Gym 1
//        GymCenter gym1 = new GymCenter();
//        gym1.setGymId("G001");
//        gym1.setGymName("PowerFit Gym");
//        gym1.setOwnerEmail("rahul.sharma@example.com");
//        gym1.setAddress("Sector 21, Noida, UP");
//        gym1.setSlotCount(6);
//        gym1.setSeatsPerSlotCount(10);
//        gym1.setVerified(true);
//        gyms.add(gym1);
//
//        // Gym 2
//        GymCenter gym2 = new GymCenter();
//        gym2.setGymId("G002");
//        gym2.setGymName("Iron Temple");
//        gym2.setOwnerEmail("anita.verma@example.com");
//        gym2.setAddress("Andheri West, Mumbai, MH");
//        gym2.setSlotCount(5);
//        gym2.setSeatsPerSlotCount(12);
//        gym2.setVerified(false);
//        gyms.add(gym2);
//
//        // Gym 3
//        GymCenter gym3 = new GymCenter();
//        gym3.setGymId("G003");
//        gym3.setGymName("Beast Mode Gym");
//        gym3.setOwnerEmail("rohit.kapoor@example.com");
//        gym3.setAddress("Kukatpally, Hyderabad, TS");
//        gym3.setSlotCount(8);
//        gym3.setSeatsPerSlotCount(15);
//        gym3.setVerified(true);
//        gyms.add(gym3);
//
//        // Gym 4
//        GymCenter gym4 = new GymCenter();
//        gym4.setGymId("G004");
//        gym4.setGymName("FitZone Fitness Club");
//        gym4.setOwnerEmail("meena.joshi@example.com");
//        gym4.setAddress("T. Nagar, Chennai, TN");
//        gym4.setSlotCount(4);
//        gym4.setSeatsPerSlotCount(8);
//        gym4.setVerified(false);
//        gyms.add(gym4);
//
//        // Gym 5
//        GymCenter gym5 = new GymCenter();
//        gym5.setGymId("G005");
//        gym5.setGymName("Sweat Factory");
//        gym5.setOwnerEmail("vikas.singh@example.com");
//        gym5.setAddress("Salt Lake City, Kolkata, WB");
//        gym5.setSlotCount(7);
//        gym5.setSeatsPerSlotCount(10);
//        gym5.setVerified(false);
//        gyms.add(gym5);
//    }
//    public List<GymOwner> getAllGymOwners() {
//        List<GymOwner> temp = new ArrayList<>();
//        for( GymOwner owner : gymOwners){
//            if(owner.isVerified()){
//                temp.add(owner);
//            }
//        }
//        return temp;
//
//
//    }
//    public List<GymCenter> getAllGyms() {
//        List<GymCenter> temp = new ArrayList<>();
//        for( GymCenter center : gyms){
//            if(center.isVerified()){
//                temp.add(center);
//            }
//        }
//        return temp;
//    }
//
//    public List<GymOwner> getPendingGymOwnerRequests() {
//        List<GymOwner> temp = new ArrayList<>();
//        for( GymOwner owner : gymOwners){
//            if(!owner.isVerified()){
//                temp.add(owner);
//            }
//        }
//        return temp;
//    }
//
//    public List<GymCenter> getPendingGymRequests(){
//        List<GymCenter> temp = new ArrayList<>();
//        for( GymCenter center : gyms){
//            if(!center.isVerified()){
//                temp.add(center);
//            }
//        }
//        return temp;
//    }
//
//    public void approveSingleOwnerRequest(String gymOwnerEmail){
//        for( GymOwner owner : gymOwners){
//            if(owner.getEmail().equals(gymOwnerEmail)){
//                owner.setVerified(true);
//            }
//        }
//    }
//
//    public void approveAllOwnerRequest(){
//        for( GymOwner owner : gymOwners){
//            if(!owner.isVerified()){
//                owner.setVerified(true);
//            }
//        }
//    }
//
//    public void approveSingleGymRequest(String gymId){
//        for( GymCenter center : gyms){
//            if(center.getGymId().equals(gymId)){
//                center.setVerified(true);
//            }
//        }
//    }
//
//    public void approveAllGymRequest(){
//        for( GymCenter center : gyms){
//            if(!center.isVerified()){
//                center.setVerified(true);
//            }
//        }
//    }
//}