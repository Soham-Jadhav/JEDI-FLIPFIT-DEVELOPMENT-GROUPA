package com.flipfit.exception;

/**
 * @author Dhruv
 * Custom exception class for scenarios where a gym is not found.
 * This exception is a checked exception (it extends `Exception`), meaning
 * that methods that throw this exception must either handle it or declare
 * that they throw it. This helps enforce proper error handling at compile time.
 */
public class GymNotFoundException extends Exception {

    /**
     * Constructs a new GymNotFoundException with the specified detail message.
     * The message provides more information about the reason for the exception.
     *
     * @param message The detail message.
     */
    public GymNotFoundException(String message) {
        super(message);
    }
}