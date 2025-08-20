package com.flipfit.client;

import com.flipfit.bean.Customer;
import com.flipfit.bean.User;
import com.flipfit.business.UserBusiness;
import com.flipfit.bean.GymOwner;

import java.util.List;
import java.util.Scanner;

public class ApplicationClient {
    /**
     * Handles the user login process.
     */

    public static void login(){
        Scanner in = new Scanner(System.in);
        System.out.println("__________________________________________________________________________________\n");
        System.out.println("Enter LogIn Details\n");
        System.out.print("Enter Email: ");
        String userEmail = in.next();
        System.out.print("Enter Password: ");
        String password = in.next();
        System.out.print("Enter Role Name: ");
        String roleId = in.next();
        User user = new User(userEmail, password, roleId);
        UserBusiness userBusiness = new UserBusiness();
        if(roleId.equalsIgnoreCase("Admin")){
            AdminClient admin = new AdminClient();
            admin.adminMenu(in);
        }
        else if (userBusiness.authenticateUser(user)) {
            System.out.println("__________________________________________________________________________________\n");
            System.out.println(
                    "Welcome " + userEmail + "! You are logged in." );

            if (roleId.equalsIgnoreCase("Customer")) {

                CustomerClient customer = new CustomerClient();
                customer.customerMenu(userEmail);
            }
            else if (roleId.equalsIgnoreCase("GymOwner")) {

                GymOwnerClient gymOwner = new GymOwnerClient();
                gymOwner.gymOwnerMenu(in, userEmail);

            } else {
                System.out.println("Wrong Choice!");
            }
        } else {
            System.out.println( "\nSorry! You are not Registered! Please Register Yourself!" );
        }
    }
//    UserBusiness userBusiness = new UserBusiness();
//
//    public static void login() {
//        System.out.println("Please enter your email");
//        Scanner input = new Scanner(System.in);
//        String email = input.nextLine();
//        System.out.println("Please enter your password");
//        String password = input.nextLine();
//        System.out.println("Please enter your Role");
//        System.out.println("Press 1 for Customer");
//        System.out.println("Press 2 for GymOwner");
//        System.out.println("Press 3 for Admin");
//        int role = input.nextInt();
//
//        List<User> users= userBusiness.getUsers();
//
//        boolean loginSuccessful = false;
//        User loggedInUser = null;
//        if(!(users ==null)) {
//            for (User user : users) {
//                // Compare username, password, and the selected role
//                // Note: Comparing passwords directly is insecure. Use hashing in real applications.
//                if (user.getEmail().equals(email) &&
//                        user.getPassword().equals(password) &&
//                        user.getRoleId() == role) {
//
//                    loginSuccessful = true;
//                    loggedInUser = user; // Store the found user
//                    break; // Exit the loop once a match is found
//                }
//            }
//        }
//
//        // 3. Provide feedback based on whether a match was found
//        if (loginSuccessful) {
//            System.out.println("\nLogin successful! Welcome, " + loggedInUser.getEmail() + ".");
//            // Next, you would likely call a method to show the user's specific dashboard
//            // For example: showCustomerDashboard(loggedInUser);
//        } else {
//            System.out.println("\nLogin failed. Try Registering or enter correct email or password or role");
//        }
//    }

//    public void registerAsCustomer() {
//        System.out.println("Please enter your email");
//        Scanner input = new Scanner(System.in);
//        String email = input.nextLine();
//        System.out.println("Please enter your password");
//        String password = input.nextLine();
//        System.out.println("Please enter your age");
//        int age = input.nextInt();
//        input.nextLine();
//        System.out.println("Please enter your name");
//        String name = input.nextLine();
//        System.out.println("Please enter your phone");
//        String phone = input.nextLine();
//        System.out.println("Please enter your address");
//        String address = input.nextLine();
//
//        Customer customer = new Customer(email, password,1, name, phone, age, address);
//
//        userBusiness.registerCustomer(customer);
//
//    }
//    public void registerAsGymOwner() {
//        System.out.println("Please enter your email");
//        Scanner input = new Scanner(System.in);
//        String email = input.nextLine();
//
//        System.out.println("Please enter your password");
//        String password = input.nextLine();
//
//        System.out.println("Please enter your name");
//        String name = input.nextLine();
//
//        System.out.println("Please enter your phone number");
//        String phoneNumber = input.nextLine();
//
//        System.out.println("Please enter your Aadhar number");
//        String aadharNumber = input.nextLine();
//
//        System.out.println("Please enter your PAN number");
//        String panNumber = input.nextLine();
//
//        GymOwner gymOwner = new GymOwner(email, password, 2, name, phoneNumber, aadharNumber, panNumber);
//
//        userBusiness.registerGymOwner(gymOwner);
//    }

//    /**
//     * Displays the main application menu for user interaction.
//     */
//    public static void applicationMenu() {
//        System.out.println("Displaying main application menu...");
//    }
//
//    /**
//     * The main entry point for the FlipFit application.
//     * @param args Command-line arguments.
//     */
//    public static void main(String[] args) {
//        ApplicationClient client = new ApplicationClient();
//        System.out.println("Welcome to FlipFit Application");
//        System.out.println("Press 1 to login");
//        System.out.println("Press 2 to register as customer");
//        System.out.println("Press 3 to register as gymowner");
//        System.out.println("Press 4 to exit");
//
//        Scanner scanner = new Scanner(System.in);
//
//        Boolean flag =true;
//        while(true) {
//            System.out.print("Enter your choice: ");
//            int option = scanner.nextInt();
//            if (option == 1) {
//                client.login();
//            } else if (option == 2) {
//                client.registerAsCustomer();
//            } else if (option == 3) {
//                client.registerAsGymOwner();
//            } else if (option == 4) {
//                System.out.println("Exiting... Re run to start again");
//                break;
//            } else {
//                System.out.println("Invalid option");
//            }
//        }
//
//        scanner.close();
//
//    }

    public static void applicationMenu() throws Exception {
        boolean recur = true;
        System.out.println("Welcome to the FlipFit Application!" );

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
                    login();
                    break;
                case 3:
                    GymOwnerClient owner = new GymOwnerClient();
                    owner.gymOwnerRegistration(in);
                    login();
                    break;
                case 4:
                    System.out.println("Exiting..." );
                    System.out.println("Exited Successfully" );
                    recur = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Wrong choice" );
            }
        }

    }

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        applicationMenu();
    }

}