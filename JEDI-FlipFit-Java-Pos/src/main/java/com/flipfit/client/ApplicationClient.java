package com.flipfit.client;

import com.flipfit.bean.User;
import com.flipfit.business.UserBusiness;

import java.text.ParseException;
import java.util.Scanner;

/**
 * @author Kriti
 * Main entry point for the FlipFit application.
 * This class serves as the top-level client, handling the initial user
 * interactions, such as login and registration, and then directing
 * the user to the appropriate sub-menu (Admin, Customer, or Gym Owner)
 * based on their role.
 */
public class ApplicationClient {

    /**
     * Handles the user login process.
     * It prompts for user credentials, authenticates them, and then
     * redirects to the relevant client-side menu based on the user's role.
     * @exception ParseException if there is an issue with parsing dates.
     */
    public static void login() throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("__________________________________________________________________________________\n");
        System.out.println("Enter LogIn Details\n");
        System.out.print("Enter Email: ");
        String userEmail = in.next();
        System.out.print("Enter Password: ");
        String password = in.next();
        System.out.print("Enter Role Name: ");
        String roleId = in.next();

        // Create a User object with the provided credentials.
        User user = new User(userEmail, password, roleId);
        UserBusiness userBusiness = new UserBusiness();

        // Check the role to handle admin login separately, as it doesn't require
        // general authentication business logic.
        if (roleId.equalsIgnoreCase("Admin")) {
            AdminClient admin = new AdminClient();
            admin.adminMenu(in);
        }
        // Authenticate the user for Customer and GymOwner roles.
        else if (userBusiness.authenticateUser(user)) {
            System.out.println("__________________________________________________________________________________\n");
            System.out.println(
                    "Welcome " + userEmail + "! You are logged in.");

            // Redirect to the appropriate menu based on the role.
            if (roleId.equalsIgnoreCase("Customer")) {
                CustomerClient customer = new CustomerClient();
                customer.customerMenu(userEmail);
            } else if (roleId.equalsIgnoreCase("GymOwner")) {
                GymOwnerClient gymOwner = new GymOwnerClient();
                gymOwner.gymOwnerMenu(in, userEmail);
            } else {
                System.out.println("Wrong Choice!");
            }
        } else {
            System.out.println("\nSorry! You are not Registered! Please Register Yourself!");
        }
    }

    /**
     * Displays the main application menu and manages the user's initial choices.
     * This is the starting point of the application's user flow.
     * @exception Exception if there is an issue with displaying the applicationMenu
     */
    public static void applicationMenu() throws Exception {
        boolean recur = true;
        System.out.println("Welcome to the FlipFit Application!");

        while (recur) {
            System.out.println("\nChoose your action:");
            System.out.println("1. Login");
            System.out.println("2. Customer Registration");
            System.out.println("3. Gym Owner Registration");
            System.out.println("4. Exit");
            System.out.print("\nEnter Your Choice: ");

            Scanner in = new Scanner(System.in);

            int choice = in.nextInt();
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    CustomerClient customer = new CustomerClient();
                    customer.registerCustomer();
                    login(); // Automatically logs in the user after registration
                    break;
                case 3:
                    GymOwnerClient owner = new GymOwnerClient();
                    owner.gymOwnerRegistration(in);
                    login(); // Automatically logs in the user after registration
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.out.println("Exited Successfully");
                    recur = false;
                    System.exit(0); // Exits the application.
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }

    }

    /**
     * The main method, which starts the application by calling the applicationMenu.
     *
     * @param args Command-line arguments.
     * @exception Exception if there is an issue with displaying the menu
     */
    public static void main(String[] args) throws Exception {
        applicationMenu();
    }
}