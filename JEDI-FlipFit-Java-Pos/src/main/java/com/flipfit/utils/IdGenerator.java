package com.flipfit.utils;

import java.util.HashSet;

/**
 * @author Dhruv
 * Utility class for generating unique IDs.
 * It ensures that each generated ID is unique within the application's runtime.
 */
public class IdGenerator {

    // A static HashSet to store all previously generated IDs to ensure uniqueness.
    private static HashSet<String> alreadyAlloted = new HashSet<>();

    /**
     * Generates a unique ID with a specified prefix.
     * The ID is composed of the prefix and a randomly generated number.
     *
     * @param part The prefix for the ID (e.g., "Gym", "Slot", "Booking").
     * @return A unique String ID.
     */
    public static String generateId(String part) {
        String id = part + "_";
        String generatedId = "";

        while (true) {
            // Generates a random number and appends it to the prefix.
            // The loop ensures the random part of the ID is at least 4 digits long.
            while (generatedId.length() < 4) {
                // Generates a number between 11 and 20, which is then added to the ID.
                generatedId += (int) Math.ceil((Math.random() + 1) * 10);
            }
            id += generatedId;

            // Checks if the generated ID is already in the set.
            // If it's not, it's considered unique and added to the set.
            if (!alreadyAlloted.contains(id)) {
                alreadyAlloted.add(id);
                break;
            }
        }
        return id;
    }
}