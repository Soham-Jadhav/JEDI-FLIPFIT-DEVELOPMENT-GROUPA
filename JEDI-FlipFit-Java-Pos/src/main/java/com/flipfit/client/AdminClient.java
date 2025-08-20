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
    }

    /**
     * Prints a list of all registered gym owners to the console.
     * @param gymOwners A list of GymOwner objects to be displayed.
     */
    public void viewAllGymOwners(List<GymOwner> gymOwners) {
        System.out.println("Displaying all gym owners...");
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
        System.out.println("Welcome to the Admin menu!");
    }
}