package com.flipfit.client;

import com.flipfit.bean.Customer;
import com.flipfit.business.CustomerBusiness;

import java.util.Scanner;

public class CustomerClient {
    Customer customer = new Customer();
    CustomerBusiness customerBusiness = new CustomerBusiness();
    Scanner sc = new Scanner(System.in);

    /**
     * Handles the customer registration process.
     */
    public void registerCustomer() {
        System.out.println("Starting customer registration...");
    }

    /**
     * Displays available gyms to the customer.
     * @param email The email of the customer.
     */
    public void viewGyms(String email) {
        System.out.println("Displaying available gyms for " + email + "...");
    }

    /**
     * Fetches a list of all gyms.
     */
    public void getGyms() {
        System.out.println("Fetching all gyms...");
    }

    /**
     * Allows the customer to edit their profile.
     * @param email The email of the customer whose profile is being edited.
     */
    public void editProfile(String email) {
        System.out.println("Editing profile for " + email + "...");
    }

    /**
     * Cancels a customer's existing booking.
     * @param email The email of the customer canceling the booking.
     */
    public void cancelBooking(String email) {
        System.out.println("Cancelling a booking for " + email + "...");
    }

    /**
     * Displays all of the customer's booked slots.
     * @param email The email of the customer whose bookings are being viewed.
     */
    public void viewBookedSlots(String email) {
        System.out.println("Displaying booked slots for " + email + "...");
    }

    /**
     * Displays the customer-specific menu and handles user input.
     * @param email The email of the currently logged-in customer.
     */
    public void customerMenu(String email) {
        System.out.println("Welcome to the customer menu, " + email + "!");
    }
}
