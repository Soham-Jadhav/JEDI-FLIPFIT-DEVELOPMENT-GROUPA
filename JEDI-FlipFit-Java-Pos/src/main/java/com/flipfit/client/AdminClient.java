package com.flipfit.client;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.business.AdminBusiness;

import java.util.List;
import java.util.Scanner;

public class AdminClient {

    AdminBusiness adminBusiness = new AdminBusiness();
    Scanner sc = new Scanner(System.in);

    /**
     * Prints a list of all gyms to the console.
     * @param gyms A list of GymCenter objects to be displayed.
     */
    public void viewAllGyms(List<GymCenter> gyms) {
        System.out.println("Displaying all gyms...");

        for (GymCenter gym : gyms) {
            System.out.println(gym);
        }
    }

    /**
     * Prints a list of all registered gym owners to the console.
     * @param gymOwners A list of GymOwner objects to be displayed.
     */
    public void viewAllGymOwners(List<GymOwner> gymOwners) {
        System.out.println("Displaying all gym owners...");

        for (GymOwner gym : gymOwners) {
            System.out.println(gym);
        }
    }

    /**
     * Displays all pending gym owner registration requests.
     */
    public void viewAllPendingGymOwnerRequests() {
        System.out.println("Displaying all pending gym owner requests...");
    }

    /**
     * Displays all pending gym registration requests.
     */
    public void viewAllPendingGymRequests() {
        System.out.println("Displaying all pending gym requests...");
    }

    /**
     * Approves a single pending gym owner registration request.
     */
    public void approveSingleGymOwnerRequest() {
        System.out.println("Approving a single gym owner request...");
    }

    /**
     * Approves a single pending gym registration request.
     */
    public void approveSingleGymRequest() {
        System.out.println("Approving a single gym request...");
    }

    /**
     * Approves all pending gym owner registration requests.
     */
    public void approvePendingGymOwnerRequests() {
        System.out.println("Approving all pending gym owner requests...");
    }

    /**
     * Approves all pending gym registration requests.
     */
    public void approvePendingGymRequests() {
        System.out.println("Approving all pending gym requests...");
    }

    /**
     * Displays the main administrative menu and handles user input.
     * @param in A Scanner object to read user input.
     */
    public void adminMenu(Scanner in){
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++");

        while (true) {
            System.out.println("Welcome to the Admin menu!");
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
                    return;
                // Default case statement
                default:
                    System.out.println("Wrong choice");
            }
        }
    }
}