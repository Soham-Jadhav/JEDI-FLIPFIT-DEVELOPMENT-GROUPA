package com.flipfit.exception;

/**
 * @author Dhruv
 * Custom exception to indicate that a user or a system component
 * attempted to perform an action for which it does not have the
 * necessary permissions or a valid role.
 * This is a checked exception that extends the base `Exception` class.
 */
public class UnauthorizedAccessException extends Exception {

    /**
     * Constructs a new UnauthorizedAccessException with the specified
     * detail message. The message should explain what access was
     * unauthorized and why.
     *
     * @param message The detail message.
     */
    public UnauthorizedAccessException(String message) {
        super(message);
    }
}