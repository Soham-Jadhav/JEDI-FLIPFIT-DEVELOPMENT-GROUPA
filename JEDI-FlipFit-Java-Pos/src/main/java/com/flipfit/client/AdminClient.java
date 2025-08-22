package com.flipfit.client;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.business.AdminBusiness;

import java.util.List;
import java.util.Scanner;

/**
 * @author Kriti
 * Client-side class for the Admin user role.
 * This class handles the user interface and interaction for administrators,
 * providing a menu-driven system to perform actions such as viewing and
 * approving gym and gym owner requests. It communicates with the
 * AdminBusiness layer to execute the business logic.
 */
public class AdminClient {

    // Instance of the business layer to access administrative functionalities.
    AdminBusiness adminBusiness = new AdminBusiness();

    // Scanner object to read user input from the console.
    Scanner sc = new Scanner(System.in);

    /**
     * Displays a formatted list of all gym centers.
     *
     * @param gyms A list of GymCenter objects to be displayed.
     */
    public void viewAllGyms(List<GymCenter> gyms) {
        for (GymCenter gym : gyms) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Gym Id-->" + gym.getGymId());
            System.out.println("Gym Name-->" + gym.getGymName());
            System.out.println("Gym Owner Mail-->" + gym.getOwnerEmail());
            System.out.println("Gym Address-->" + gym.getAddress());
            System.out.println("Gym Slot Count-->" + gym.getSlotCount());
            System.out.println("Gym Verification -->" + (gym.isVerified() ? "Yes" : "No"));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        }
    }

    /**
     * Displays a formatted list of all gym owners.
     *
     * @param gymOwners A list of GymOwner objects to be displayed.
     */
    public void viewAllGymOwners(List<GymOwner> gymOwners) {
        for (GymOwner gymOwner : gymOwners) {
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("Gym Owner Name-->" + gymOwner.getName());
            System.out.println("Gym Owner phone numver-->" + gymOwner.getPhoneNumber());
            System.out.println("Gym Owner Aadhar-->" + gymOwner.getAadharNumber());
            System.out.println("Gym Owner panNumber-->" + gymOwner.getPanNumber());
            System.out.println("Gym Owner Verification -->" + (gymOwner.isVerified() ? "Yes" : "No"));
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        }

    }

    /**
     * Retrieves and displays all pending gym owner registration requests.
     * It calls the business layer to get the data and then uses the view method.
     */
    public void viewAllPendingGymOwnerRequests() {
        viewAllGymOwners(adminBusiness.viewAllPendingGymOwnerRequests());
    }

    /**
     * Retrieves and displays all pending gym registration requests.
     * It calls the business layer to get the data and then uses the view method.
     */
    public void viewAllPendingGymRequests() {
        viewAllGyms(adminBusiness.viewAllPendingGymRequests());
    }

    /**
     * Prompts the user for a gym owner's email and approves that single request.
     */
    public void approveSingleGymOwnerRequest() {
        System.out.println("Enter gym owner email: ");
        adminBusiness.approveSingleGymOwnerRequest(sc.next());
    }

    /**
     * Prompts the user for a gym ID and approves that single gym request.
     */
    public void approveSingleGymRequest() {
        System.out.println("Enter gym Id: ");
        adminBusiness.approveSingleGymRequest(sc.next());
    }

    /**
     * Approves all pending gym owner registration requests.
     * This method delegates the call directly to the business layer.
     */
    public void approvePendingGymOwnerRequests() {
        adminBusiness.approveAllPendingGymOwnerRequests();
    }

    /**
     * Approves all pending gym registration requests.
     * This method delegates the call directly to the business layer.
     */
    public void approvePendingGymRequests() {
        adminBusiness.approveAllPendingGymRequests();
    }

    /**
     * Displays the main menu for the administrator and handles user input.
     * This method acts as the control flow for the admin's interaction with the system.
     *
     * @param in A Scanner object used to read user input.
     */
    public void adminMenu(Scanner in) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");
        while (true) {
            System.out.println("1. View All Gym ");
            System.out.println("2. View All Gym Owners");
            System.out.println("3. View all pending Gym Owner Requests");
            System.out.println("4. View all pending Gym Requests");
            System.out.println("5. Approve all pending Gym Owner Requests");
            System.out.println("6. Approve all pending Gym Requests");
            System.out.println("7. Approve Single Gym Owner Request");
            System.out.println("8. Approve Single Gym Request");
            System.out.println("9. Exit");

            System.out.print("Enter your choice: ");
            int choice = in.nextInt();
            switch (choice) {
                // Case statements
                case 1:
                    List<GymCenter> gymList = adminBusiness.getGym();
                    viewAllGyms(gymList);
                    break;
                case 2:
                    List<GymOwner> gymOwnerList = adminBusiness.getGymOwners();
                    viewAllGymOwners(gymOwnerList);
                    break;
                case 3:
                    viewAllPendingGymOwnerRequests();
                    break;
                case 4:
                    viewAllPendingGymRequests();
                    break;
                case 5:
                    approvePendingGymOwnerRequests();
                    break;
                case 6:
                    approvePendingGymRequests();
                    break;
                case 7:
                    approveSingleGymOwnerRequest();
                    break;
                case 8:
                    approveSingleGymRequest();
                    break;
                case 9:
                    return; // Exits the admin menu loop.
                // Default case statement
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}