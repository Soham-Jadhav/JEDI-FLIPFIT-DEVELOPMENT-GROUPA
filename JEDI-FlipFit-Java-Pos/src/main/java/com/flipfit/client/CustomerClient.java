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

public class CustomerClient {
    Customer customer = new Customer();
    CustomerBusiness customerBusiness = new CustomerBusiness();
    Scanner sc = new Scanner(System.in);

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

    public void viewGyms(String email) throws ParseException {
        getGyms();
        System.out.print("Enter gym ID: ");
        String gymId = sc.next();
        System.out.print("Enter Date (yyyy-mm-dd): ");
        String dateStr = sc.next();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateStr);

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
        //System.out.println("Successfully edited your profile");

        UserBusiness userBusiness = new UserBusiness();
        userBusiness.editProfile(customer);

        System.out.println("Profile Updated successfully!");
    }

    public void cancelBooking(String email) {
        System.out.print("Enter booking ID that you want to cancel: ");
        String bookingId = sc.next();
        customerBusiness.cancelBooking(bookingId, email);
    }

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