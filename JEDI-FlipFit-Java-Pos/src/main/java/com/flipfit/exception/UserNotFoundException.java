package com.flipfit.exception;

/**
 * @author Dhruv
 * Custom exception to indicate that a user with a specified
 * identifier (e.g., email or user ID) was not found in the data source.
 * This is a checked exception, so methods that search for a user
 * and cannot find one must declare or handle this exception.
 */
public class UserNotFoundException extends Exception {

    /**
     * Constructs a new UserNotFoundException with the specified
     * detail message. The message should provide information about
     * which user was not found.
     *
     * @param message The detail message.
     */
    public UserNotFoundException(String message){
        super(message);
    }
}