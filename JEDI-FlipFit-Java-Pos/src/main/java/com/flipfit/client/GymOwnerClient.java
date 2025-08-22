package com.flipfit.client;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.Slot;
import com.flipfit.business.GymOwnerBusiness;
import com.flipfit.business.UserBusiness;
import com.flipfit.utils.IdGenerator;

import java.util.*;

/**
 * @author Kriti
 * Client-side class for the Gym Owner user role.
 * This class handles the user interface and interaction for gym owners,
 * providing a menu-driven system to manage their profile, add and edit gyms,
 * and add time slots. It communicates with the GymOwnerBusiness and UserBusiness
 * layers to execute the business logic.
 */
public class GymOwnerClient {

    // Instance of the GymOwner bean to hold user data during registration/editing.
    GymOwner gymOwner = new GymOwner();
    // Instance of the GymOwnerBusiness layer to access gym owner-specific business logic.
    GymOwnerBusiness gymOwnerBusiness = new GymOwnerBusiness();
    // Instance of the UserBusiness layer for user registration and profile editing.
    UserBusiness userBusiness = new UserBusiness();

    /**
     * Prompts the user for details and registers a new gym owner.
     * The method collects all required information and then passes the GymOwner
     * object to the UserBusiness layer for registration.
     *
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
     * Fetches and displays the profile information of the logged-in gym owner.
     *
     * @param in    A Scanner object (unused in this method but kept for consistency).
     * @param email The email of the logged-in gym owner.
     */
    public void viewProfile(Scanner in, String email) {
        gymOwner = gymOwnerBusiness.getProfile(email);
        System.out.println("______________________________________________________________");
        System.out.printf("%15s%15s%15s%15s", "Gym Owner Name", "Phone Number", "PAN Number", "Aadhaar Number");
        System.out.println();
        System.out.printf("%15s%15s%15s%15s", gymOwner.getName(), gymOwner.getPhoneNumber(), gymOwner.getPanNumber(),
                gymOwner.getAadharNumber());
        System.out.println("\n______________________________________________________________");
    }

    /**
     * Allows a gym owner to edit their profile details.
     * It collects new information and sends it to the GymOwnerBusiness layer for update.
     *
     * @param in    A Scanner object to read user input.
     * @param email The email of the gym owner whose profile is to be edited.
     */
    public void editProfile(Scanner in, String email) {
        System.out.println("Enter Details: ");
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
        gymOwner.setEmail(email);

        gymOwnerBusiness.editProfile(gymOwner);
    }

    /**
     * Prompts the user for gym details and adds a new gym center.
     *
     * @param in    A Scanner object to read user input.
     * @param email The email of the logged-in gym owner, which is set as the owner's email for the new gym.
     */
    public void addGym(Scanner in, String email) {
        System.out.println("Please Enter Gym Details ");

        GymCenter gym = new GymCenter();
        gym.setGymId(IdGenerator.generateId("Gym"));
        System.out.print("Gym Name: ");
        gym.setGymName(in.next());
        gym.setOwnerEmail(email);
        System.out.print("Address: ");
        gym.setAddress(in.next());
        System.out.print("SlotCount: ");
        gym.setSlotCount(in.nextInt());
        System.out.print("SeatsPerSlotCount: ");
        gym.setSeatsPerSlotCount(in.nextInt());
        // A newly added gym is not verified by default.
        gym.setVerified(false);

        gymOwnerBusiness.addGym(gym);
    }

    /**
     * Prompts the user for gym details and edits an existing gym center.
     *
     * @param in    A Scanner object to read user input.
     * @param email The email of the logged-in gym owner.
     */
    public void editGym(Scanner in, String email) {
        System.out.println("Please Enter Gym Details ");

        GymCenter gym = new GymCenter();
        System.out.print("Gym Id: ");
        gym.setGymId(in.next());
        System.out.print("GymName: ");
        gym.setGymName(in.next());
        gym.setOwnerEmail(email);
        System.out.print("Address: ");
        gym.setAddress(in.next());
        System.out.print("SlotCount: ");
        gym.setSlotCount(in.nextInt());
        System.out.print("SeatsPerSlotCount: ");
        gym.setSeatsPerSlotCount(in.nextInt());
        // A gym that is edited will need to be re-verified by an admin.
        gym.setVerified(false);

        gymOwnerBusiness.editGym(gym);
    }

    /**
     * Prompts the user for slot details and adds a new time slot to a gym.
     *
     * @param in A Scanner object to read user input.
     */
    public void addSlot(Scanner in) {
        System.out.println("Enter Slot Details: ");
        Slot slot = new Slot();
        slot.setSlotId(IdGenerator.generateId("Slot"));
        System.out.print("Enter Gym Id:");
        slot.setGymId(in.next());
        System.out.print("Enter Slot Start Time: ");
        slot.setStartTime(in.next());
        System.out.print("Enter Slot End Time: ");
        slot.setEndTime(in.next());
        System.out.print("Enter number of seats in slot: ");
        slot.setNumOfSeats(in.nextInt());
        System.out.print("Enter Trainer: ");
        slot.setTrainer(in.next());
        // A new slot has no seats booked initially.
        slot.setNumOfSeatsBooked(0);

        gymOwnerBusiness.addSlot(slot);
    }

    /**
     * Retrieves and displays the details of all gyms associated with the logged-in gym owner.
     *
     * @param in    A Scanner object (unused but kept for consistency).
     * @param email The email of the gym owner.
     */
    public void getGymDetails(Scanner in, String email) {
        List<GymCenter> gymDetails = gymOwnerBusiness.getGymDetail(email);
        for (GymCenter gym : gymDetails) {
            System.out.println(gym);
        }
    }

    /**
     * Displays the main menu for the gym owner and handles their choices.
     * This method acts as the control flow for the gym owner's interaction with the system.
     *
     * @param in    A Scanner object to read user input.
     * @param email The email of the logged-in gym owner.
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