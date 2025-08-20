package com.flipfit.client;

import com.flipfit.bean.GymOwner;
import com.flipfit.business.GymOwnerBusiness;

import java.util.Scanner;

public class GymOwnerClient {
    GymOwner gymOwner = new GymOwner();
    GymOwnerBusiness gymOwnerBusiness = new GymOwnerBusiness();

    /**
     * Handles the gym owner registration process.
     * @param in A Scanner object to read user input.
     */
    public void gymOwnerRegistration(Scanner in) {
        System.out.println("Starting gym owner registration...");
    }

    /**
     * Allows a gym owner to view their profile.
     * @param in A Scanner object to read user input.
     * @param email The email of the gym owner.
     */
    public void viewProfile(Scanner in, String email) {
        System.out.println("Viewing profile for " + email + "...");
    }

    /**
     * Allows a gym owner to edit their profile.
     * @param in A Scanner object to read user input.
     * @param email The email of the gym owner.
     */
    public void editProfile(Scanner in, String email) {
        System.out.println("Editing profile for " + email + "...");
    }

    /**
     * Allows a gym owner to add a new gym to their account.
     * @param in A Scanner object to read user input.
     * @param email The email of the gym owner.
     */
    public void addGym(Scanner in, String email) {
        System.out.println("Adding a new gym for " + email + "...");
    }

    /**
     * Allows a gym owner to edit an existing gym.
     * @param in A Scanner object to read user input.
     * @param email The email of the gym owner.
     */
    public void editGym(Scanner in, String email) {
        System.out.println("Editing a gym for " + email + "...");
    }

    /**
     * Allows a gym owner to add a new time slot for a gym.
     * @param in A Scanner object to read user input.
     */
    public void addSlot(Scanner in) {
        System.out.println("Adding a new slot...");
    }

    /**
     * Fetches details of a gym owned by the user.
     * @param in A Scanner object to read user input.
     * @param email The email of the gym owner.
     */
    public void getGymDetails(Scanner in, String email) {
        System.out.println("Getting gym details for " + email + "...");
    }

    /**
     * Displays the gym owner menu and handles user input.
     * @param in A Scanner object to read user input.
     * @param email The email of the currently logged-in gym owner.
     */
    public void gymOwnerMenu(Scanner in, String email) {
        System.out.println("Welcome to the gym owner menu, " + email + "!");
    }
}