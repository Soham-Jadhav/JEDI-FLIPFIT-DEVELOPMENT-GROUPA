package com.flipfit.dao;

import com.flipfit.bean.Customer;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.User;

public interface UserDAOInterface {

    boolean registerCustomer(Customer customer);

    boolean authenticateUser(User user);

    boolean editProfile(Customer customer);

    boolean registerGymOwner(GymOwner gymOwner);

    GymOwner getGymOwnerDetails(String gymOwnerEmailId);

    void editGymOwnerDetails(GymOwner gymOwnerDetails);
}