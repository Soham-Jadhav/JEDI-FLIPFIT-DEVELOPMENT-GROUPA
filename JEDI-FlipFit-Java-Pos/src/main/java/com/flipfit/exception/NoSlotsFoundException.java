package com.flipfit.exception;

/**
 * @author Dhruv
 * Custom exception to be thrown when no suitable time slots are found
 * for a specific query. This can happen if a gym has no slots, or if all
 * existing slots are already booked.
 * This is a checked exception, so methods that can't find a slot will need
 * to declare or handle this exception.
 */
public class NoSlotsFoundException extends Exception {

    /**
     * Constructs a new NoSlotsFoundException with the specified detail message.
     * The message should explain why no slots were found.
     *
     * @param message The detail message.
     */
    public NoSlotsFoundException(String message) {
        super(message);
    }
}