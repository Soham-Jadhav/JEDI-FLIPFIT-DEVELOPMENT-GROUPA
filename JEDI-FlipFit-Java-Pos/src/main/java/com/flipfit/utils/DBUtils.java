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
 *
 */
public class DBUtils {
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
                Properties prop = new Properties();
                // Load the properties file from the classpath

                InputStream inputStream = DBUtils.class.getClassLoader().getResourceAsStream("./config.properties");
                if (inputStream == null) {
                    throw new IOException("config.properties file not found on the classpath.");
                }

                prop.load(inputStream);
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");

                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
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