package com.flipfit.business;
import com.flipfit.bean.Customer;
import com.flipfit.dao.UserDAOImpl;
import com.flipfit.bean.User;
import com.flipfit.bean.GymOwner;

public class UserBusiness implements UserBusinessInterface {
    UserDAOImpl userDao = new UserDAOImpl();

    public boolean registerCustomer(Customer customer) {
        // Implementation removed
        return false;
    }

    public boolean authenticateUser(User user) {
        // Implementation removed
        return false;
    }

    public boolean editProfile(Customer customer) {
        // Implementation removed
        return false;
    }

    public boolean registerGymOwner(GymOwner gymOwner) {
        // Implementation removed
        return false;
    }
}