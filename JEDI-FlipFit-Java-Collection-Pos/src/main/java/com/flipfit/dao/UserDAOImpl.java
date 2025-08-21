package com.flipfit.dao;
import java.util.*;
import com.flipfit.bean.Customer;
import com.flipfit.bean.User;
import com.flipfit.bean.GymOwner;

public class UserDAOImpl {
    private static List<Customer> customers = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static List<GymOwner> gymOwners = new ArrayList<>();

    public UserDAOImpl(){
        Customer customer1 = new Customer("c1@gmail.com", "c1", "Customer", "Vaishnavi", "0000", 22, "Kanpur");
        Customer customer2 = new Customer("c2@gmail.com", "c2", "Customer", "Anjali", "0000", 32, "Vadodara");
        Customer customer3 = new Customer("c3@gmail.com", "c3", "Customer", "Sudha", "0000", 42, "Kolkata");
        Customer customer4 = new Customer("c4@gmail.com", "c4", "Customer", "Aaishu", "0000", 52, "Mumbai");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        customers.add(customer4);

        User user1 = new User(customer1.getEmail(), customer1.getPassword(), customer1.getRoleId());
        users.add(user1);
        User user2 = new User(customer2.getEmail(), customer2.getPassword(), customer2.getRoleId());
        users.add(user2);
        User user3 = new User(customer3.getEmail(), customer3.getPassword(), customer3.getRoleId());
        users.add(user3);
        User user4 = new User(customer4.getEmail(), customer4.getPassword(), customer4.getRoleId());
        users.add(user4);

        GymOwner owner1 = new GymOwner("o1@gmail.com", "o1", "GymOwner", "Rohan Mehra", "9999", "123456789012", "ABCDE1234F");
        GymOwner owner2 = new GymOwner("o2@gmail.com", "o2", "GymOwner", "Priya Desai", "8888", "234567890123", "BCDEA2345G");
        GymOwner owner3 = new GymOwner("o3@gmail.com", "o3", "GymOwner", "Amit Singh", "7777", "345678901234", "CDEAB3456H");
        GymOwner owner4 = new GymOwner("o4@gmail.com", "o4", "GymOwner", "Sneha Nair", "6666", "456789012345", "DEABC4567I");
        gymOwners.add(owner1);
        gymOwners.add(owner2);
        gymOwners.add(owner3);
        gymOwners.add(owner4);

        User user5 = new User(owner1.getEmail(), owner1.getPassword(), owner1.getRoleId());
        users.add(user5);
        User user6 = new User(owner2.getEmail(), owner2.getPassword(), owner2.getRoleId());
        users.add(user6);
        User user7 = new User(owner3.getEmail(), owner3.getPassword(), owner3.getRoleId());
        users.add(user7);
        User user8 = new User(owner4.getEmail(), owner4.getPassword(), owner4.getRoleId());
        users.add(user8);

    }

    public boolean registerCustomer(Customer customer) {
        // Check if customer already exists
        for (Customer existing : customers) {
            if (existing.getEmail().equals(customer.getEmail())) {
                System.out.println("Customer already exists.");
                return false;
            }
        }

        // Add customer to collection
        customers.add(customer);

        // Create corresponding user account
        User user = new User(customer.getEmail(),  customer.getPassword(), customer.getRoleId());
        users.add(user);

        System.out.println("Customer registered successfully.");
        return true;
    }

    public boolean authenticateUser(User user) {
        for (User u : users) {
            if (u.getEmail() != null && u.getPassword() != null && u.getRoleId() != null &&
                    user.getEmail() != null && user.getPassword() != null && user.getRoleId() != null &&
                    u.getEmail().equalsIgnoreCase(user.getEmail()) &&
                    u.getPassword().equals(user.getPassword()) &&
                    u.getRoleId().equalsIgnoreCase(user.getRoleId())) {
                System.out.println("User authenticated: " + u.getEmail());
                return true;
            }
//            System.out.println(u.getEmail());
//            System.out.println(u.getPassword());
//            System.out.println(u.getRoleId());
        }
        System.out.println("Invalid credentials for: " + user.getEmail());
        return false;
    }

    public boolean editProfile(Customer customer) {
        boolean flag = false;
        for(Customer cust : customers) {
            if (cust.getEmail().equals(customer.getEmail())) {
                flag = true;
                cust.setPassword(customer.getPassword());
                cust.setAddress(customer.getAddress());
                cust.setAge(customer.getAge());
                cust.setName(customer.getName());
                cust.setPhoneNumber(customer.getPhoneNumber());
                break;
            }
        }
        if(flag==false)System.out.println("Customer does not exists.");
        for(User u : users) {
            if (u.getEmail().equalsIgnoreCase(customer.getEmail())) {
                u.setPassword(customer.getPassword());
                break;
            }
        }
        return true;
    }

    public boolean registerGymOwner(GymOwner gymOwner) {
        // Check if the gym owner already exists
        for (GymOwner existing : gymOwners) {
            if (existing.getEmail().equalsIgnoreCase(gymOwner.getEmail())) {
                System.out.println("Gym Owner already exists.");
                return false;
            }
        }

        // Add gym owner to the collection
        gymOwners.add(gymOwner);

        // Create corresponding user account for login
        User user = new User();
        user.setEmail(gymOwner.getEmail());
        user.setPassword(gymOwner.getPassword());
        user.setRoleId("GymOwner");
        users.add(user);

        System.out.println("Gym Owner registered successfully.");
        return true;
    }

    public GymOwner getGymOwnerDetails(String gymOwnerEmailId) {
        for (GymOwner owner : gymOwners) {
            if (owner.getEmail().equalsIgnoreCase(gymOwnerEmailId)) {
                return owner;
            }
        }
        System.out.println("GymOwner with email " + gymOwnerEmailId + " not found.");
        return null; // or return new GymOwner(); based on your design preference
    }

    public void editGymOwnerDetails(GymOwner gymOwnerDetails) {
        boolean updated = false;

        // Update in gymOwners list
        for (GymOwner owner : gymOwners) {
            if (owner.getEmail().equalsIgnoreCase(gymOwnerDetails.getEmail())) {
                owner.setName(gymOwnerDetails.getName());
                owner.setPhoneNumber(gymOwnerDetails.getPhoneNumber());
                owner.setAadharNumber(gymOwnerDetails.getAadharNumber());
                owner.setPanNumber(gymOwnerDetails.getPanNumber());
                owner.setVerified(gymOwnerDetails.isVerified());
                owner.setPassword(gymOwnerDetails.getPassword());
                updated = true;
                System.out.println("Gym owner profile updated.");
                break;
            }
        }

        // Update in users list
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(gymOwnerDetails.getEmail())) {
                user.setPassword(gymOwnerDetails.getPassword());
                user.setRoleId("GymOwner"); // Ensure role stays correct
                break;
            }
        }

        if (!updated) {
            System.out.println("Gym owner with email " + gymOwnerDetails.getEmail() + " not found.");
        }
    }

}