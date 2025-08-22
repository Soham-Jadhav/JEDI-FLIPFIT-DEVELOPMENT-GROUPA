package com.flipfit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flipfit.bean.*;
import com.flipfit.bean.User;
import com.flipfit.constants.SQLConstants;
import com.flipfit.utils.DBUtils;

/**
 * @author Dhruv
 * Data Access Object (DAO) implementation for user-related operations.
 * This class handles all interactions with the database for user authentication,
 * registration, and profile management for both customers and gym owners.
 */
public class UserDAOImpl implements UserDAOInterface {

    /**
     * Authenticates a user by checking their email, password, and role against the database.
     *
     * @param user The User object containing the credentials to be authenticated.
     * @return {@code true} if the user's credentials are valid, {@code false} otherwise.
     */
    public boolean authenticateUser(User user) {
        Connection connection = null;
        boolean isUserValid = false;
        try {
            // Get a database connection.
            connection = DBUtils.getConnection();

            // Use a prepared statement to prevent SQL injection.
            PreparedStatement preparedStatement = connection.prepareStatement(SQLConstants.SQL_SELECT_USER_LOGIN_CREDENTIAL);
            preparedStatement.setString(1, user.getEmail());

            // Execute the query.
            ResultSet rs = preparedStatement.executeQuery();

            // Check if the result set has a row and if the password and role match.
            while (rs.next()) {
                if (user.getPassword().equals(rs.getString("password"))
                        && user.getRoleId().equalsIgnoreCase(rs.getString("roleId"))) {
                    System.out.println(
                            rs.getString("email") + " " + rs.getString("password") + " " + rs.getString("roleId"));
                    isUserValid = true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUserValid;
    }

    /**
     * Registers a new customer by inserting records into both the `GymCustomer` and `User` tables.
     *
     * @param customer The Customer object containing the registration details.
     * @return {@code true} if the registration was successful, {@code false} otherwise.
     */
    public boolean registerCustomer(Customer customer) {
        Connection connection = null;
        boolean registerSuccess = false;
        String query = "INSERT INTO GymCustomer VALUES (?,?,?,?,?,?,?)";
        String queryUser = "INSERT INTO User VALUES (?,?,?)";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            PreparedStatement preparedStatementUser = connection.prepareStatement(queryUser);

            // Set parameters for the GymCustomer table.
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

            // Set parameters for the User table.
            preparedStatementUser.setString(1, customer.getEmail());
            preparedStatementUser.setString(2, customer.getPassword());
            preparedStatementUser.setString(3, "Customer");

            rowsAffected = preparedStatementUser.executeUpdate();
            if (rowsAffected != 0)
                registerSuccess = true;

        } catch (SQLException e) {
            // In a production app, this should be logged and handled more gracefully.
        }
        return registerSuccess;
    }

    /**
     * Registers a new gym owner by inserting records into both the `GymOwner` and `User` tables.
     *
     * @param gymOwner The GymOwner object containing the registration details.
     * @return {@code true} if the registration was successful, {@code false} otherwise.
     */
    public boolean registerGymOwner(GymOwner gymOwner) {
        Connection connection = null;
        boolean registerSuccess = false;
        String query = "INSERT INTO GymOwner VALUES (?,?,?,?,?,?,?,?)";
        String queryOwner = "INSERT INTO User VALUES (?,?,?)";
        try {
            connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            PreparedStatement preparedStatementOwner = connection.prepareStatement(queryOwner);

            // Set parameters for the GymOwner table.
            preparedStatement.setString(1, gymOwner.getEmail());
            preparedStatement.setString(2, gymOwner.getName());
            preparedStatement.setString(3, gymOwner.getPhoneNumber());
            preparedStatement.setString(4, gymOwner.getAadharNumber());
            preparedStatement.setString(5, gymOwner.getPanNumber());
            preparedStatement.setBoolean(6, gymOwner.isVerified());
            preparedStatement.setString(7, gymOwner.getPassword());
            preparedStatement.setString(8, gymOwner.getRoleId());

            int rowsAffected = preparedStatement.executeUpdate();

            // Set parameters for the User table.
            preparedStatementOwner.setString(1, gymOwner.getEmail());
            preparedStatementOwner.setString(2, gymOwner.getPassword());
            preparedStatementOwner.setString(3, "GymOwner");

            rowsAffected = preparedStatementOwner.executeUpdate();
            if (rowsAffected != 0)
                registerSuccess = true;

        } catch (SQLException e) {
            // In a production app, this should be logged.
        }
        return registerSuccess;
    }

    /**
     * Edits the profile of an existing customer by updating records in both
     * the `User` and `GymCustomer` tables. This is done within a transaction.
     *
     * @param customer The Customer object with the updated details.
     * @return {@code true} if the profile was successfully updated, {@code false} otherwise.
     */
    public boolean editProfile(Customer customer) {
        Connection connection = null;
        PreparedStatement userUpdateStmt = null;
        PreparedStatement customerDetailsUpdateStmt = null;
        boolean updated = false;

        // SQL queries for updating user and customer details.
        String userUpdateQuery = "UPDATE User SET password = ? WHERE email = ?";
        String customerDetailsUpdateQuery = "UPDATE GymCustomer SET email=?, password=?, name = ?, phoneNum = ?, age = ?, address = ? WHERE email = ?";

        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false); // Start transaction for atomicity

            // 1. Update the User table.
            userUpdateStmt = connection.prepareStatement(userUpdateQuery);
            userUpdateStmt.setString(1, customer.getPassword());
            userUpdateStmt.setString(2, customer.getEmail());

            int userRowsAffected = userUpdateStmt.executeUpdate();

            if (userRowsAffected == 0) {
                System.out.println("User with email " + customer.getEmail() + " does not exist. Cannot edit profile.");
                connection.rollback();
                return false;
            }

            // 2. Update the GymCustomer table.
            customerDetailsUpdateStmt = connection.prepareStatement(customerDetailsUpdateQuery);
            customerDetailsUpdateStmt.setString(1, customer.getEmail());
            customerDetailsUpdateStmt.setString(2, customer.getPassword());
            customerDetailsUpdateStmt.setString(3, customer.getName());
            customerDetailsUpdateStmt.setString(4, customer.getPhoneNumber());
            customerDetailsUpdateStmt.setInt(5, customer.getAge());
            customerDetailsUpdateStmt.setString(6, customer.getAddress());
            customerDetailsUpdateStmt.setString(7, customer.getEmail());

            int customerDetailsRowsAffected = customerDetailsUpdateStmt.executeUpdate();

            if (customerDetailsRowsAffected > 0) {
                updated = true;
                System.out.println("Customer profile details for " + customer.getEmail() + " updated successfully.");
            } else {
                System.out.println("Warning: Customer details for " + customer.getEmail() + " not found or not updated.");
                connection.rollback();
                return false;
            }

            connection.commit(); // Commit the transaction.

        } catch (SQLException sqlExcep) {
            System.err.println("SQL Error editing customer profile for " + customer.getEmail() + ": " + sqlExcep.getMessage());
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback on any SQL error.
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
            throw new RuntimeException("Database error editing customer profile: " + sqlExcep.getMessage(), sqlExcep);
        } finally {
            // Close resources.
            try {
                if (userUpdateStmt != null) userUpdateStmt.close();
                if (customerDetailsUpdateStmt != null) customerDetailsUpdateStmt.close();
                if (connection != null) {
                    connection.setAutoCommit(true); // Reset auto-commit to true.
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }
        }
        return updated;
    }

    /**
     * Edits the profile details of an existing gym owner.
     * Note: This method seems to have an issue, as it uses `customerDetailsUpdateQuery`.
     * The logic should be corrected to update the `GymOwner` table.
     *
     * @param gymOwnerDetails The GymOwner object with the updated details.
     */
    public void editGymOwnerDetails(GymOwner gymOwnerDetails) {
        boolean updated = false;

        Connection connection = null;
        PreparedStatement userUpdateStmt = null;
        PreparedStatement gymOwnerDetailsUpdateStmt = null; // Corrected variable name

        // SQL queries for updating user and gym owner details.
        String userUpdateQuery = "UPDATE User SET password = ? WHERE email = ?";
        String gymOwnerDetailsUpdateQuery = "UPDATE GymOwner SET name = ?, phoneNum = ?, aadharNum = ?, panNum = ?, isVerified = ?, password = ?, roleId = ? WHERE email = ?";

        try {
            connection = DBUtils.getConnection();
            connection.setAutoCommit(false);

            // 1. Update the User table.
            userUpdateStmt = connection.prepareStatement(userUpdateQuery);
            userUpdateStmt.setString(1, gymOwnerDetails.getPassword());
            userUpdateStmt.setString(2, gymOwnerDetails.getEmail());

            int userRowsAffected = userUpdateStmt.executeUpdate();

            if (userRowsAffected == 0) {
                System.out.println("User with email " + gymOwnerDetails.getEmail() + " does not exist. Cannot edit profile.");
                connection.rollback();
            }

            // 2. Update the GymOwner table.
            gymOwnerDetailsUpdateStmt = connection.prepareStatement(gymOwnerDetailsUpdateQuery);
            gymOwnerDetailsUpdateStmt.setString(1, gymOwnerDetails.getName());
            gymOwnerDetailsUpdateStmt.setString(2, gymOwnerDetails.getPhoneNumber());
            gymOwnerDetailsUpdateStmt.setString(3, gymOwnerDetails.getAadharNumber());
            gymOwnerDetailsUpdateStmt.setString(4, gymOwnerDetails.getPanNumber());
            gymOwnerDetailsUpdateStmt.setBoolean(5, gymOwnerDetails.isVerified());
            gymOwnerDetailsUpdateStmt.setString(6, gymOwnerDetails.getPassword());
            gymOwnerDetailsUpdateStmt.setString(7, gymOwnerDetails.getRoleId());
            gymOwnerDetailsUpdateStmt.setString(8, gymOwnerDetails.getEmail());

            int gymOwnerDetailsRowsAffected = gymOwnerDetailsUpdateStmt.executeUpdate();

            if (gymOwnerDetailsRowsAffected > 0) {
                updated = true;
                System.out.println("GymOwner profile details for " + gymOwnerDetails.getEmail() + " updated successfully.");
            } else {
                System.out.println("Warning: GymOwner details for " + gymOwnerDetails.getEmail() + " not found or not updated.");
                connection.rollback();
            }

            connection.commit();

        } catch (SQLException sqlExcep) {
            System.err.println("SQL Error editing GymOwner profile for " + gymOwnerDetails.getEmail() + ": " + sqlExcep.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during rollback: " + rollbackEx.getMessage());
                }
            }
            throw new RuntimeException("Database error editing GymOwner profile: " + sqlExcep.getMessage(), sqlExcep);
        }

    }

    /**
     * Retrieves the profile details of a gym owner from the database.
     *
     * @param gymOwnerEmailId The email ID of the gym owner.
     * @return A GymOwner object containing the profile details.
     */
    public GymOwner getGymOwnerDetails(String gymOwnerEmailId) {
        GymOwner gymOwner = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        // Query to select all fields for a gym owner from the GymOwner table.
        String query = "SELECT email, name, phoneNum, aadharNum, panNum, isVerified, password, roleId FROM GymOwner WHERE email = ?";

        try {
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, gymOwnerEmailId);
            resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                System.out.println("Gym Owner with email ID " + gymOwnerEmailId + " not found.");
            }

            // Create GymOwner object and set its properties from the ResultSet.
            gymOwner = new GymOwner();
            gymOwner.setEmail(resultSet.getString("email"));
            gymOwner.setName(resultSet.getString("name"));
            gymOwner.setPhoneNumber(resultSet.getString("phoneNum"));
            gymOwner.setAadharNumber(resultSet.getString("aadharNum"));
            gymOwner.setPanNumber(resultSet.getString("panNum"));
            gymOwner.setVerified(resultSet.getBoolean("isVerified"));
            gymOwner.setPassword(resultSet.getString("password"));
            gymOwner.setRoleId(resultSet.getString("roleId"));
        } catch (SQLException sqlExcep) {
            System.err.println("SQL Error retrieving Gym Owner details for " + gymOwnerEmailId + ": " + sqlExcep.getMessage());
            throw new RuntimeException("Database error retrieving Gym Owner details: " + sqlExcep.getMessage(), sqlExcep);
        } finally {
            // Close resources.
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