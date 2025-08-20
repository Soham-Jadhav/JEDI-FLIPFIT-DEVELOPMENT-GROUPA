package com.flipfit.client;

import com.flipfit.bean.Customer;
import com.flipfit.bean.User;
import com.flipfit.business.CustomerBusiness;
import com.flipfit.business.UserBusiness;
import com.flipfit.bean.GymOwner;

import java.util.List;
import java.util.Scanner;

public class ApplicationClient {
    /**
     * Handles the user login process.
     */
    UserBusiness userBusiness = new UserBusiness();

    public void login() {
        System.out.println("Please enter your email");
        Scanner input = new Scanner(System.in);
        String email = input.nextLine();
        System.out.println("Please enter your password");
        String password = input.nextLine();
        System.out.println("Please enter your Role");
        System.out.println("Press 1 for Customer");
        System.out.println("Press 2 for GymOwner");
        System.out.println("Press 3 for Admin");
        int role = input.nextInt();

        List<User> users= userBusiness.getUsers();

        boolean loginSuccessful = false;
        User loggedInUser = null;
        if(!(users ==null)) {
            for (User user : users) {
                // Compare username, password, and the selected role
                // Note: Comparing passwords directly is insecure. Use hashing in real applications.
                if (user.getEmail().equals(email) &&
                        user.getPassword().equals(password) &&
                        user.getRoleId() == role) {

                    loginSuccessful = true;
                    loggedInUser = user; // Store the found user
                    break; // Exit the loop once a match is found
                }
            }
        }

        // 3. Provide feedback based on whether a match was found
        if (loginSuccessful) {
            System.out.println("\nLogin successful! Welcome, " + loggedInUser.getEmail() + ".");
            if(loggedInUser.getRoleId() == 1) {
                CustomerBusiness customer = new CustomerBusiness();
                customer.showMenu();
            }
            // Next, you would likely call a method to show the user's specific dashboard
            // For example: showCustomerDashboard(loggedInUser);
        } else {
            System.out.println("\nLogin failed. Try Registering or enter correct email or password or role");
        }
    }

    public void registerAsCustomer() {
        System.out.println("Please enter your email");
        Scanner input = new Scanner(System.in);
        String email = input.nextLine();
        System.out.println("Please enter your password");
        String password = input.nextLine();
        System.out.println("Please enter your age");
        int age = input.nextInt();
        input.nextLine();
        System.out.println("Please enter your name");
        String name = input.nextLine();
        System.out.println("Please enter your phone");
        String phone = input.nextLine();
        System.out.println("Please enter your address");
        String address = input.nextLine();

        Customer customer = new Customer(email, password,1, name, phone, age, address);

        userBusiness.registerCustomer(customer);

    }
    public void registerAsGymOwner() {
        System.out.println("Please enter your email");
        Scanner input = new Scanner(System.in);
        String email = input.nextLine();

        System.out.println("Please enter your password");
        String password = input.nextLine();

        System.out.println("Please enter your name");
        String name = input.nextLine();

        System.out.println("Please enter your phone number");
        String phoneNumber = input.nextLine();

        System.out.println("Please enter your Aadhar number");
        String aadharNumber = input.nextLine();

        System.out.println("Please enter your PAN number");
        String panNumber = input.nextLine();

        GymOwner gymOwner = new GymOwner(email, password, 2, name, phoneNumber, aadharNumber, panNumber);

        userBusiness.registerGymOwner(gymOwner);
    }

    /**
     * Displays the main application menu for user interaction.
     */
    public static void applicationMenu() {
        System.out.println("Displaying main application menu...");
    }

    /**
     * The main entry point for the FlipFit application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        ApplicationClient client = new ApplicationClient();
        System.out.println("Welcome to FlipFit Application");
        System.out.println("Press 1 to login");
        System.out.println("Press 2 to register as customer");
        System.out.println("Press 3 to register as gymowner");
        System.out.println("Press 4 to exit");

        Scanner scanner = new Scanner(System.in);

        Boolean flag =true;
        while(true) {
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            if (option == 1) {
                client.login();
            } else if (option == 2) {
                client.registerAsCustomer();
            } else if (option == 3) {
                client.registerAsGymOwner();
            } else if (option == 4) {
                System.out.println("Exiting... Re run to start again");
                break;
            } else {
                System.out.println("Invalid option");
            }
        }

        scanner.close();

    }
}