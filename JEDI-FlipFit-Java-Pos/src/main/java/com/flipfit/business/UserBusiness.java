package com.flipfit.business;
import com.flipfit.bean.Customer;
//import com.flipfit.dao.UserDAOImpl;
import com.flipfit.bean.User;
import com.flipfit.bean.GymOwner;

import java.util.ArrayList;
import java.util.List;

public class UserBusiness implements UserBusinessInterface {
//    UserDAOImpl userDao = new UserDAOImpl();
    private static List<User> users=new ArrayList<>();;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        UserBusiness.users =users;
    }

    public boolean registerCustomer(Customer customer) {
        // Implementation removed
        User user = new User();
        user.setEmail(customer.getEmail());
        user.setPassword(customer.getPassword());
        user.setRoleId(customer.getRoleId());
        users.add(user);
        return true;
    }

    public boolean authenticateUser(User userToAuth) {
        // Loop through every user in the static list
        System.out.println("Size of user is " + users.size());
        for (User registeredUser : users) {
            if (registeredUser.getEmail().equalsIgnoreCase(userToAuth.getEmail()) &&
                    registeredUser.getPassword().equals(userToAuth.getPassword()) && registeredUser.getRoleId().equalsIgnoreCase(userToAuth.getRoleId())) {
                System.out.println("authenticating");
                return true; // Found a match!
            }
        }
        return false; // No match found after checking all users
    }

    public boolean editProfile(Customer customer) {
        // Implementation removed
        return false;
    }

    public boolean registerGymOwner(GymOwner gymOwner) {
        User user = new User();
        user.setEmail(gymOwner.getEmail());
        user.setPassword(gymOwner.getPassword());
        user.setRoleId(gymOwner.getRoleId());
        users.add(user);
        return true;
    }
}