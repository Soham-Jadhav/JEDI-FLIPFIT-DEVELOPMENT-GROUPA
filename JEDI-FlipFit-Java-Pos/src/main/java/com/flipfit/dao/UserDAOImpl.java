package com.flipfit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipfit.bean.*;
import com.flipfit.bean.User;
import com.flipfit.constants.SQLConstants;
import com.flipfit.utils.DBUtils;

public class UserDAOImpl implements UserDAOInterface {

    public boolean authenticateUser(User user) {
        // to run without authentication, make isUserValid = true
        Connection connection = null;

        boolean isUserValid = false;
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_USER_LOGIN_CREDENTIAL);

            preparedStatement.setString(1, user.getEmail());

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                if (user.getPassword().equals(rs.getString("password"))
                        && user.getRoleId().equalsIgnoreCase(rs.getString("roleId"))) {
                    System.out.println(
                            rs.getString("email") + " " + rs.getString("password") + " " + rs.getString("roleId"));
                    isUserValid = true;
                }
            }

        } catch (SQLException e) {

        }

        return isUserValid;
    }

    public boolean registerCustomer(Customer customer) {
        Connection connection = null;
        boolean registerSuccess = false;
        String query = "INSERT INTO GymCustomer VALUES (?,?,?,?,?,?,?)";
        String queryUser = "INSERT INTO User VALUES (?,?,?)";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            PreparedStatement preparedStatementUser = connection.prepareStatement(queryUser);

            preparedStatement.setString(1, customer.getEmail());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getRoleId());
            preparedStatement.setString(4, customer.getName());
            preparedStatement.setString(5, customer.getPhoneNumber());
            preparedStatement.setInt(6, customer.getAge());
            preparedStatement.setString(7, customer.getAddress());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected != 0)
                registerSuccess = true;

            preparedStatementUser.setString(1, customer.getEmail());
            preparedStatementUser.setString(2, customer.getPassword());
            preparedStatementUser.setString(3, "Customer");

            rowsAffected = preparedStatementUser.executeUpdate();
            if (rowsAffected != 0)
                registerSuccess = true;

        } catch (SQLException e) {

        }

        return registerSuccess;
    }

    public boolean registerGymOwner(GymOwner gymOwner) {
        Connection connection = null;
        boolean registerSuccess = false;
        String query = "INSERT INTO GymOwner VALUES (?,?,?,?,?,?,?,?)";
        String queryOwner = "INSERT INTO User VALUES (?,?,?)";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            PreparedStatement preparedStatementOwner = connection.prepareStatement(queryOwner);

            preparedStatement.setString(1, gymOwner.getEmail());
            preparedStatement.setString(2, gymOwner.getPassword());
            preparedStatement.setString(3, gymOwner.getRoleId());
            preparedStatement.setString(4, gymOwner.getName());
            preparedStatement.setString(5, gymOwner.getPhoneNumber());
            preparedStatement.setString(6, gymOwner.getAadharNumber());
            preparedStatement.setString(7, gymOwner.getPanNumber());
            preparedStatement.setBoolean(8, gymOwner.isVerified());

            int rowsAffected = preparedStatement.executeUpdate();

            preparedStatementOwner.setString(1, gymOwner.getEmail());
            preparedStatementOwner.setString(2, gymOwner.getPassword());
            preparedStatementOwner.setString(3, "GymOwner");

            rowsAffected = preparedStatementOwner.executeUpdate();
            if (rowsAffected != 0)
                registerSuccess = true;

        } catch (SQLException e) {

        }

        return registerSuccess;
    }

    public boolean editProfile(Customer customer) {
        Connection connection = null;
        PreparedStatement userUpdateStmt = null;
        PreparedStatement customerDetailsUpdateStmt = null;
        boolean updated = false;

        // SQL to update the Users table (for email and password, as Customer extends User)
        // Assuming email is the primary key in the Users table
        String userUpdateQuery = "UPDATE User SET password = ? WHERE email = ?";

        // SQL to update the CustomerDetails table
        // Assuming customerEmail is the primary key/foreign key in CustomerDetails
        String customerDetailsUpdateQuery = "UPDATE GymCustomer SET email=?, password=?, name = ?, phoneNumber = ?, age = ?, address = ? WHERE email = ?";

        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false); // Start transaction for atomicity

            // 1. Update the Users table (for email and password)
            userUpdateStmt = connection.prepareStatement(userUpdateQuery);
            userUpdateStmt.setString(1, customer.getPassword());
            userUpdateStmt.setString(2, customer.getEmail()); // WHERE clause for email

            int userRowsAffected = userUpdateStmt.executeUpdate();

            if (userRowsAffected == 0) {
                System.out.println("User with email " + customer.getEmail() + " does not exist. Cannot edit profile.");
                connection.rollback(); // Rollback if the base user record is not found
                return false; // User not found
            }

            // 2. Update the CustomerDetails table
            customerDetailsUpdateStmt = connection.prepareStatement(customerDetailsUpdateQuery);
            customerDetailsUpdateStmt.setString(1, customer.getEmail());
            customerDetailsUpdateStmt.setString(2, customer.getPassword());
            customerDetailsUpdateStmt.setString(3, customer.getName());
            customerDetailsUpdateStmt.setString(4, customer.getPhoneNumber());
            customerDetailsUpdateStmt.setInt(5, customer.getAge());
            customerDetailsUpdateStmt.setString(6, customer.getAddress());
            customerDetailsUpdateStmt.setString(7, customer.getEmail()); // WHERE clause for customerEmail

            int customerDetailsRowsAffected = customerDetailsUpdateStmt.executeUpdate();

            if (customerDetailsRowsAffected > 0) {
                updated = true;
                System.out.println("Customer profile details for " + customer.getEmail() + " updated successfully.");
            } else {
                // This scenario means a User record exists but no corresponding CustomerDetails record.
                // This might indicate an incomplete registration or data inconsistency.
                System.out.println("Warning: Customer details for " + customer.getEmail() + " not found or not updated.");
                // Depending on strictness, you might rollback here too, or just log.
                // For a complete profile update, it's safer to rollback if details aren't found.
                connection.rollback();
                return false;
            }

            connection.commit(); // Commit transaction if both updates succeed

        } catch (SQLException sqlExcep) {
            System.err.println("SQL Error editing customer profile for " + customer.getEmail() + ": " + sqlExcep.getMessage());
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback on any SQL error
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
            // Re-throw as a runtime exception or a custom application-specific exception
            throw new RuntimeException("Database error editing customer profile: " + sqlExcep.getMessage(), sqlExcep);
        } finally {
            // Close resources in a finally block
            try {
                if (userUpdateStmt != null) userUpdateStmt.close();
                if (customerDetailsUpdateStmt != null) customerDetailsUpdateStmt.close();
                if (connection != null) {
                    connection.setAutoCommit(true); // Reset auto-commit to true
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return updated; // Returns true if both user and customer details were successfully updated
    }


    public void editGymOwnerDetails(GymOwner gymOwnerDetails) {
        boolean updated = false;

        Connection connection = null;
        PreparedStatement userUpdateStmt = null;
        PreparedStatement customerDetailsUpdateStmt = null;


        // SQL to update the Users table (for email and password, as Customer extends User)
        // Assuming email is the primary key in the Users table
        String userUpdateQuery = "UPDATE User SET password = ? WHERE email = ?";

        // SQL to update the CustomerDetails table
        // Assuming customerEmail is the primary key/foreign key in CustomerDetails
        String customerDetailsUpdateQuery = "UPDATE GymOwner SET email=?, password=?, name = ?, phoneNum = ? WHERE email = ?";

        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false); // Start transaction for atomicity

            // 1. Update the Users table (for email and password)
            userUpdateStmt = connection.prepareStatement(userUpdateQuery);
            userUpdateStmt.setString(1, gymOwnerDetails.getPassword());
            userUpdateStmt.setString(2, gymOwnerDetails.getEmail()); // WHERE clause for email

            int userRowsAffected = userUpdateStmt.executeUpdate();

            if (userRowsAffected == 0) {
                System.out.println("User with email " + gymOwnerDetails.getEmail() + " does not exist. Cannot edit profile.");
                connection.rollback(); // Rollback if the base user record is not found
                //return false; // User not found
            }

            // 2. Update the CustomerDetails table
            customerDetailsUpdateStmt = connection.prepareStatement(customerDetailsUpdateQuery);
            customerDetailsUpdateStmt.setString(1, gymOwnerDetails.getEmail());
            customerDetailsUpdateStmt.setString(2, gymOwnerDetails.getPassword());
            customerDetailsUpdateStmt.setString(3, gymOwnerDetails.getName());
            customerDetailsUpdateStmt.setString(4, gymOwnerDetails.getPhoneNumber());
            customerDetailsUpdateStmt.setString(5, gymOwnerDetails.getEmail()); // WHERE clause for customerEmail

            int customerDetailsRowsAffected = customerDetailsUpdateStmt.executeUpdate();

            if (customerDetailsRowsAffected > 0) {
                updated = true;
                System.out.println("GymOwner profile details for " + gymOwnerDetails.getEmail() + " updated successfully.");
            } else {
                // This scenario means a User record exists but no corresponding CustomerDetails record.
                // This might indicate an incomplete registration or data inconsistency.
                System.out.println("Warning: GymOwner details for " + gymOwnerDetails.getEmail() + " not found or not updated.");
                // Depending on strictness, you might rollback here too, or just log.
                // For a complete profile update, it's safer to rollback if details aren't found.
                connection.rollback();
                // return false;
            }

            connection.commit(); // Commit transaction if both updates succeed

        } catch (SQLException sqlExcep) {
            System.err.println("SQL Error editing customer profile for " + gymOwnerDetails.getEmail() + ": " + sqlExcep.getMessage());
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback on any SQL error
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
            // Re-throw as a runtime exception or a custom application-specific exception
            throw new RuntimeException("Database error editing customer profile: " + sqlExcep.getMessage(), sqlExcep);
        }

    }


    public GymOwner getGymOwnerDetails(String gymOwnerEmailId)  {
        GymOwner gymOwner = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        // Query to select all fields for a gym owner from the GymOwner table
        String query = "SELECT email, password, roleId, name, phoneNum, aadharNum, panNum, isVerified FROM GymOwner WHERE email = ?";

        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, gymOwnerEmailId);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Gym Owner with email ID " + gymOwnerEmailId + " not found.");
            }

            // Create GymOwner object and set its properties from the ResultSet
            gymOwner = new GymOwner();
            gymOwner.setEmail(resultSet.getString("email"));
            gymOwner.setPassword(resultSet.getString("password"));
            gymOwner.setRoleId(resultSet.getString("roleId")); // Map 'roleId' column to 'roleName' attribute
            gymOwner.setName(resultSet.getString("name"));
            gymOwner.setPhoneNumber(resultSet.getString("phoneNum"));
            gymOwner.setAadharNumber(resultSet.getString("aadharNum"));
            gymOwner.setPanNumber(resultSet.getString("panNum"));
            gymOwner.setVerified(resultSet.getBoolean("isVerified"));

        } catch (SQLException sqlExcep) {
            System.err.println("SQL Error retrieving Gym Owner details for " + gymOwnerEmailId + ": " + sqlExcep.getMessage());
            throw new RuntimeException("Database error retrieving Gym Owner details: " + sqlExcep.getMessage(), sqlExcep);
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return gymOwner;
    }


}























//package com.flipfit.dao;
//import java.util.*;
//import com.flipfit.bean.Customer;
//import com.flipfit.bean.User;
//import com.flipfit.bean.GymOwner;
//
//public class UserDAOImpl {
//    private static List<Customer> customers = new ArrayList<>();
//    private static List<User> users = new ArrayList<>();
//    private static List<GymOwner> gymOwners = new ArrayList<>();
//
//    public UserDAOImpl(){
//        Customer customer1 = new Customer("c1@gmail.com", "c1", "Customer", "Vaishnavi", "0000", 22, "Kanpur");
//        Customer customer2 = new Customer("c2@gmail.com", "c2", "Customer", "Anjali", "0000", 32, "Vadodara");
//        Customer customer3 = new Customer("c3@gmail.com", "c3", "Customer", "Sudha", "0000", 42, "Kolkata");
//        Customer customer4 = new Customer("c4@gmail.com", "c4", "Customer", "Aaishu", "0000", 52, "Mumbai");
//        customers.add(customer1);
//        customers.add(customer2);
//        customers.add(customer3);
//        customers.add(customer4);
//
//        User user1 = new User(customer1.getEmail(), customer1.getPassword(), customer1.getRoleId());
//        users.add(user1);
//        User user2 = new User(customer2.getEmail(), customer2.getPassword(), customer2.getRoleId());
//        users.add(user2);
//        User user3 = new User(customer3.getEmail(), customer3.getPassword(), customer3.getRoleId());
//        users.add(user3);
//        User user4 = new User(customer4.getEmail(), customer4.getPassword(), customer4.getRoleId());
//        users.add(user4);
//
//        GymOwner owner1 = new GymOwner("o1@gmail.com", "o1", "GymOwner", "Rohan Mehra", "9999", "123456789012", "ABCDE1234F");
//        GymOwner owner2 = new GymOwner("o2@gmail.com", "o2", "GymOwner", "Priya Desai", "8888", "234567890123", "BCDEA2345G");
//        GymOwner owner3 = new GymOwner("o3@gmail.com", "o3", "GymOwner", "Amit Singh", "7777", "345678901234", "CDEAB3456H");
//        GymOwner owner4 = new GymOwner("o4@gmail.com", "o4", "GymOwner", "Sneha Nair", "6666", "456789012345", "DEABC4567I");
//        gymOwners.add(owner1);
//        gymOwners.add(owner2);
//        gymOwners.add(owner3);
//        gymOwners.add(owner4);
//
//        User user5 = new User(owner1.getEmail(), owner1.getPassword(), owner1.getRoleId());
//        users.add(user5);
//        User user6 = new User(owner2.getEmail(), owner2.getPassword(), owner2.getRoleId());
//        users.add(user6);
//        User user7 = new User(owner3.getEmail(), owner3.getPassword(), owner3.getRoleId());
//        users.add(user7);
//        User user8 = new User(owner4.getEmail(), owner4.getPassword(), owner4.getRoleId());
//        users.add(user8);
//
//    }
//
//    public boolean registerCustomer(Customer customer) {
//        // Check if customer already exists
//        for (Customer existing : customers) {
//            if (existing.getEmail().equals(customer.getEmail())) {
//                System.out.println("Customer already exists.");
//                return false;
//            }
//        }
//
//        // Add customer to collection
//        customers.add(customer);
//
//        // Create corresponding user account
//        User user = new User(customer.getEmail(),  customer.getPassword(), customer.getRoleId());
//        users.add(user);
//
//        System.out.println("Customer registered successfully.");
//        return true;
//    }
//
//    public boolean authenticateUser(User user) {
//        for (User u : users) {
//            if (u.getEmail() != null && u.getPassword() != null && u.getRoleId() != null &&
//                    user.getEmail() != null && user.getPassword() != null && user.getRoleId() != null &&
//                    u.getEmail().equalsIgnoreCase(user.getEmail()) &&
//                    u.getPassword().equals(user.getPassword()) &&
//                    u.getRoleId().equalsIgnoreCase(user.getRoleId())) {
//                System.out.println("User authenticated: " + u.getEmail());
//                return true;
//            }
////            System.out.println(u.getEmail());
////            System.out.println(u.getPassword());
////            System.out.println(u.getRoleId());
//        }
//        System.out.println("Invalid credentials for: " + user.getEmail());
//        return false;
//    }
//
//    public boolean editProfile(Customer customer) {
//        boolean flag = false;
//        for(Customer cust : customers) {
//            if (cust.getEmail().equals(customer.getEmail())) {
//                flag = true;
//                cust.setPassword(customer.getPassword());
//                cust.setAddress(customer.getAddress());
//                cust.setAge(customer.getAge());
//                cust.setName(customer.getName());
//                cust.setPhoneNumber(customer.getPhoneNumber());
//                break;
//            }
//        }
//        if(flag==false)System.out.println("Customer does not exists.");
//        for(User u : users) {
//            if (u.getEmail().equalsIgnoreCase(customer.getEmail())) {
//                u.setPassword(customer.getPassword());
//                break;
//            }
//        }
//        return true;
//    }
//
//    public boolean registerGymOwner(GymOwner gymOwner) {
//        // Check if the gym owner already exists
//        for (GymOwner existing : gymOwners) {
//            if (existing.getEmail().equalsIgnoreCase(gymOwner.getEmail())) {
//                System.out.println("Gym Owner already exists.");
//                return false;
//            }
//        }
//
//        // Add gym owner to the collection
//        gymOwners.add(gymOwner);
//
//        // Create corresponding user account for login
//        User user = new User();
//        user.setEmail(gymOwner.getEmail());
//        user.setPassword(gymOwner.getPassword());
//        user.setRoleId("GymOwner");
//        users.add(user);
//
//        System.out.println("Gym Owner registered successfully.");
//        return true;
//    }
//
//    public GymOwner getGymOwnerDetails(String gymOwnerEmailId) {
//        for (GymOwner owner : gymOwners) {
//            if (owner.getEmail().equalsIgnoreCase(gymOwnerEmailId)) {
//                return owner;
//            }
//        }
//        System.out.println("GymOwner with email " + gymOwnerEmailId + " not found.");
//        return null; // or return new GymOwner(); based on your design preference
//    }
//
//    public void editGymOwnerDetails(GymOwner gymOwnerDetails) {
//        boolean updated = false;
//
//        // Update in gymOwners list
//        for (GymOwner owner : gymOwners) {
//            if (owner.getEmail().equalsIgnoreCase(gymOwnerDetails.getEmail())) {
//                owner.setName(gymOwnerDetails.getName());
//                owner.setPhoneNumber(gymOwnerDetails.getPhoneNumber());
//                owner.setAadharNumber(gymOwnerDetails.getAadharNumber());
//                owner.setPanNumber(gymOwnerDetails.getPanNumber());
//                owner.setVerified(gymOwnerDetails.isVerified());
//                owner.setPassword(gymOwnerDetails.getPassword());
//                updated = true;
//                System.out.println("Gym owner profile updated.");
//                break;
//            }
//        }
//
//        // Update in users list
//        for (User user : users) {
//            if (user.getEmail().equalsIgnoreCase(gymOwnerDetails.getEmail())) {
//                user.setPassword(gymOwnerDetails.getPassword());
//                user.setRoleId("GymOwner"); // Ensure role stays correct
//                break;
//            }
//        }
//
//        if (!updated) {
//            System.out.println("Gym owner with email " + gymOwnerDetails.getEmail() + " not found.");
//        }
//    }
//
//}