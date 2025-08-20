package com.flipfit.client;

import com.flipfit.bean.Customer;
import com.flipfit.business.CustomerBusiness;
import com.flipfit.business.UserBusiness;

import java.util.Scanner;

public class CustomerClient {
    Customer customer = new Customer();
    CustomerBusiness customerBusiness = new CustomerBusiness();
    Scanner sc = new Scanner(System.in);

    /**
     * Handles the customer registration process.
     */
    public void registerCustomer() {
        System.out.print("Enter email: ");
        customer.setEmail(sc.next());
        System.out.print("Enter password: ");
        customer.setPassword(sc.next());
        System.out.print("Enter Name: ");
        customer.setName(sc.next());
        System.out.print("Enter Phone Number: ");
        customer.setPhoneNumber(sc.next());
        System.out.print("Enter Age: ");
        customer.setAge(Integer.valueOf(sc.next()));
        System.out.print("Enter Address: ");
        customer.setAddress(sc.next());
        customer.setRoleId("Customer");
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.registerCustomer(customer);

        System.out.println("Customer registered successfully!");
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
        int choice = 0;

        while (choice != 5) {
            System.out.println("Menu:-");
            System.out.println("1.View Gyms \n2.View Booked Slots \n3.Cancel Booked Slots \n4. Edit Profile \n5.Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewGyms(email);
                    break;
                case 2:
                    viewBookedSlots(email);
                    break;
                case 3:
                    cancelBooking(email);
                    break;
                case 4:
                    editProfile(email);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}