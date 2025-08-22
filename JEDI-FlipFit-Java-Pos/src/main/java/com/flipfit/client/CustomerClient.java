package com.flipfit.client;

import com.flipfit.bean.Booking;
import com.flipfit.bean.Customer;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;
import com.flipfit.business.CustomerBusiness;
import com.flipfit.business.UserBusiness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author Kriti
 * Client-side class for the Customer user role.
 * This class handles the user interface and interaction for customers,
 * providing a menu-driven system to perform actions such as registering,
 * viewing and booking gym slots, and managing their profile. It communicates
 * with the CustomerBusiness and UserBusiness layers to execute the logic.
 */
public class CustomerClient {

    // Instance of the Customer bean to hold user data during registration/editing.
    Customer customer = new Customer();
    // Instance of the CustomerBusiness layer to access customer-specific business logic.
    CustomerBusiness customerBusiness = new CustomerBusiness();
    // Scanner object to read user input from the console.
    Scanner sc = new Scanner(System.in);

    /**
     * Prompts the user for details and registers a new customer.
     * The method collects all required information and then passes the Customer
     * object to the UserBusiness layer for registration.
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
     * Guides the user through the process of viewing gyms and booking a slot.
     * It first prompts for a city to find gyms, then a gym ID and a date to find
     * available slots, and finally allows the user to book a slot.
     *
     * @param email The email of the logged-in customer.
     * @exception ParseException if the date format is incorrect.
     */
    public void viewGyms(String email) throws ParseException {
        getGyms();
        System.out.print("Enter gym ID: ");
        String gymId = sc.next();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        String dateStr = sc.next();

        // SimpleDateFormat is used to parse the date string into a Date object.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);

        // Retrieve and display available slots for the selected gym and date.
        List<Slot> slots = customerBusiness.getSlotInGym(gymId);
        boolean slotfound = false;
        for (Slot slot : slots) {
            System.out.print("Slot Id: " + slot.getSlotId());
            if(!customerBusiness.isSlotBooked(slot.getSlotId(), date))slotfound = true;
            System.out.print("Availability: " + !customerBusiness.isSlotBooked(slot.getSlotId(), date));
        }

        if(!slotfound){
            System.out.println("Slot not found");
            return;
        }

        System.out.print("Enter the slot ID which you want to book: ");
        String slotId = sc.next();

        // Book the selected slot and provide feedback based on the business layer's response.
        int bookingResponse = customerBusiness.bookSlot(gymId,slotId, email, date);
        switch (bookingResponse) {
            case 0:
                System.out.println("You have already booked this time. Cancelling the previous one and booking this slot");
                break;
            case 1:
                System.out.println("Slot is already booked, added to the waiting list");
                break;
            case 2:
                System.out.println("Successfully booked the slot");
                break;
            case 3:
                System.out.println("Slot not found");
                break;
            default:
                System.out.println("Booking failed");
        }
    }

    /**
     * Prompts the user for a city and displays the list of gyms in that city.
     */
    public void getGyms() {
        System.out.print("Enter your city: ");
        List<GymCenter> gyms = customerBusiness.getGymInCity(sc.next());
        for (GymCenter gym : gyms) {
            System.out.print("Gym Id: " + gym.getGymId());
            System.out.print("Gym Owner Email: " + gym.getOwnerEmail());
            System.out.print("Gym Name: " + gym.getGymName());
            System.out.println();
        }

        // TODO integrate it with get gyms function.
    }

    /**
     * Allows a customer to edit their profile details.
     * It collects new information and sends it to the UserBusiness layer for update.
     *
     * @param email The email of the customer whose profile is to be edited.
     */
    public void editProfile(String email) {
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
        customer.setEmail(email);

        UserBusiness userBusiness = new UserBusiness();
        userBusiness.editProfile(customer);

        System.out.println("Profile Updated successfully!");
    }

    /**
     * Prompts the user for a booking ID and cancels the corresponding booking.
     *
     * @param email The email of the customer who wants to cancel the booking.
     */
    public void cancelBooking(String email) {
        System.out.print("Enter booking ID that you want to cancel: ");
        String bookingId = sc.next();
        customerBusiness.cancelBooking(bookingId, email);
    }

    /**
     * Retrieves and displays all bookings made by the logged-in customer.
     *
     * @param email The email of the customer.
     */
    public void viewBookedSlots(String email) {
        List<Booking> bookingslots = new ArrayList<>(customerBusiness.getBookings(email));
        for (Booking booking : bookingslots) {
            System.out.println("Booking Id: " + booking.getBookingId());
            System.out.println("Slot Id: " + booking.getSlotId());
            System.out.println("Gym Id: " + booking.getGymId());
            System.out.println("Type: " + booking.getType());
        }
        // TODO : if there are no booking prompt the user

    }

    /**
     * Displays the main menu for the customer and handles their choices.
     * This method acts as the control flow for the customer's interaction with the system.
     *
     * @param email The email of the logged-in customer.
     * @exception ParseException if date parsing fails in `viewGyms`.
     */
    public void customerMenu(String email) throws ParseException {
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