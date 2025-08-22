package com.flipfit.exception;

/**
 * @author Dhruv
 * Custom exception for handling invalid user input.
 * This is a checked exception, extending the base `Exception` class.
 * It should be thrown when user-provided data does not meet the expected format or constraints,
 * such as an invalid email address, phone number, or age.
 */
public class InvalidInputException extends Exception {

    /**
     * Constructs a new InvalidInputException with the specified detail message.
     * The message provides more specific information about what was invalid.
     *
     * @param message The detail message.
     */
    public InvalidInputException(String message){
        super(message);
    }
}