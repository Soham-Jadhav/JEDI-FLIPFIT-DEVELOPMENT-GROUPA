/**
 *
 */
package com.flipfit.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Dhruv
 * Utility class to manage database connections.
 * This class provides a singleton-like method to get a connection
 * to the database by reading credentials from a configuration file.
 */
public class DBUtils {
    // A single, static connection object to be shared across the application.
    private static Connection connection = null;

    /**
     * Establishes and returns a database connection.
     * This method implements a basic singleton pattern for the database connection:
     * if a connection already exists and is not null, it is returned. Otherwise,
     * a new connection is created, initialized, and then returned.
     *
     * @return A database Connection object.
     */
    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                // Properties object to hold database credentials.
                Properties prop = new Properties();

                // Load the properties file (config.properties) from the classpath.
                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
                if (inputStream == null) {
                    throw new IOException("config.properties file not found on the classpath.");
                }

                // Load the properties from the input stream.
                prop.load(inputStream);
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");

                // Establish the connection using the loaded credentials.
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                // Handle SQL exceptions that may occur during connection.
                e.printStackTrace();
            } catch (IOException e) {
                // Handle I/O exceptions if the properties file is not found.
                e.printStackTrace();
            }
            return connection;
        }
    }

    /*
    Un-comment main and run this file with your root credentials to verify connection.
    You can change your credentials in src/main/resources/config.properties
    */
//    public static void main(String[] args) {
//        Connection testConnection = null;
//        try {
//            System.out.println("Attempting to get database connection...");
//            testConnection = getConnection();
//
//            if (testConnection != null && !testConnection.isClosed()) {
//                System.out.println("SUCCESS! Database connection established successfully.");
//                System.out.println("Connection status: " + testConnection.toString());
//            } else {
//                System.err.println("FAILURE! Connection object is null or closed.");
//            }
//        } catch (SQLException e) {
//            System.err.println("An SQL error occurred while checking the connection status.");
//            e.printStackTrace();
//        } finally {
//            if (testConnection != null) {
//                try {
//                    testConnection.close();
//                    System.out.println("Connection closed.");
//                } catch (SQLException e) {
//                    System.err.println("Error closing the connection.");
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}