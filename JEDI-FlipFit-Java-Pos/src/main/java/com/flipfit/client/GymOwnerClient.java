package com.flipfit.client;

import com.flipfit.bean.GymOwner;
import com.flipfit.business.GymOwnerBusiness;
import com.flipfit.business.UserBusiness;

import java.util.Scanner;

public class GymOwnerClient {
    GymOwner gymOwner = new GymOwner();
    GymOwnerBusiness gymOwnerBusiness = new GymOwnerBusiness();

    /**
     * Handles the gym owner registration process.
     * @param in A Scanner object to read user input.
     */
    public void gymOwnerRegistration(Scanner in) {
        System.out.println("\nEnter GymOwner Details: \n");
        System.out.print("Enter Email: ");
        gymOwner.setEmail(in.next());
        System.out.print("Enter Password: ");
        gymOwner.setPassword(in.next());
        gymOwner.setRoleId("GymOwner");
        System.out.print("Enter Name: ");
        gymOwner.setName(in.next());
        System.out.print("Enter Phone Number: ");
        gymOwner.setPhoneNumber(in.next());
        System.out.print("Enter PAN: ");
        gymOwner.setPanNumber(in.next());
        System.out.print("Enter Aadhaar: ");
        gymOwner.setAadharNumber(in.next());

        UserBusiness userBusiness = new UserBusiness();
        boolean registerSuccess = userBusiness.registerGymOwner(gymOwner);

        if (registerSuccess)
            System.out
                    .println("\n" + "Gym Owner registered successfully!");
        else
            System.out.println("\n"+"Gym Owner registration failed! Try again!");
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
        boolean recur = true;
        while (recur) {
            System.out.println("\nHere are the actions you can perform!");

            System.out.println("1. View Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Add Gym");
            System.out.println("4. Edit Gym");
            System.out.println("5. Add Slot");
            System.out.println("6. View All Gym Details");
            System.out.println("7. LogOut\n");

            System.out.print("Enter Your Choice: " );
            int choice = in.nextInt();

            System.out.println("______________________________________________________________\n");

            switch (choice) {
                case 1:
                    viewProfile(in, email);
                    break;
                case 2:
                    editProfile(in, email);
                    break;
                case 3:
                    addGym(in, email);
                    break;
                case 4:
                    editGym(in, email);
                    break;
                case 5:
                    addSlot(in);
                    break;
                case 6:
                    getGymDetails(in, email);
                    break;
                case 7:
                    recur = false;
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}