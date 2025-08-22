package com.flipfit.constants;

/**
 * @author Dhruv
 * A class to hold SQL query constants.
 * Using a separate class for SQL constants promotes code maintainability and readability,
 * as it centralizes all SQL queries in one place, making them easier to manage
 * and modify without changing the business logic code.
 */
public class SQLConstants {

    /**
     * SQL query to select user login credentials (email, password, and role)
     * from the 'user' table based on the user's email. The '?' is a placeholder
     * for the email to be provided at runtime, preventing SQL injection.
     */
    public static final String SQL_SELECT_USER_LOGIN_CREDENTIAL = "select email, password, roleId from user where email = ?";
}