package com.flipfit.business;
import com.flipfit.bean.Customer;
//import com.flipfit.dao.UserDAOImpl;
import com.flipfit.bean.User;
import com.flipfit.bean.GymOwner;

import java.util.ArrayList;
import java.util.List;

public class UserBusiness implements UserBusinessInterface {
//    UserDAOImpl userDao = new UserDAOImpl();
    private List<User> users=new ArrayList<>();;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users =users;
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