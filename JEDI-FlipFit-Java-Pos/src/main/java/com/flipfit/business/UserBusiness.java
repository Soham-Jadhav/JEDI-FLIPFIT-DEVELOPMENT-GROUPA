package com.flipfit.business;
import com.flipfit.bean.Customer;
import com.flipfit.dao.UserDAOImpl;
import com.flipfit.bean.User;
import com.flipfit.bean.GymOwner;

import java.util.ArrayList;
import java.util.List;

public class UserBusiness implements UserBusinessInterface {
    UserDAOImpl userDao = new UserDAOImpl();
    private static List<User> users=new ArrayList<>();;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        UserBusiness.users =users;
    }

    public boolean registerCustomer(Customer customer) {
        boolean registerSuccess = false;
        registerSuccess = userDao.registerCustomer(customer);
        return registerSuccess;
    }

    public boolean authenticateUser(User userToAuth) {
        // Loop through every user in the static list
        boolean authenticateSuccess = false;
        authenticateSuccess = userDao.authenticateUser(userToAuth);
        return authenticateSuccess; // No match found after checking all users
    }

    public boolean editProfile(Customer customer) {
        boolean editSuccess = false;
        editSuccess = userDao.editProfile(customer);
        return editSuccess;
    }

    public boolean registerGymOwner(GymOwner gymOwner) {
        boolean registerSuccess = false;
        registerSuccess = userDao.registerGymOwner(gymOwner);
        return registerSuccess;
    }
}