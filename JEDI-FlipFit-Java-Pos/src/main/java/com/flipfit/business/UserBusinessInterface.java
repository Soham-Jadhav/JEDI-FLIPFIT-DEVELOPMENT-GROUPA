package com.flipfit.business;

import com.flipfit.bean.Customer;
import com.flipfit.bean.User;
import com.flipfit.bean.GymOwner;

public interface UserBusinessInterface {

    boolean registerCustomer(Customer customer);

    boolean authenticateUser(User user);

    boolean editProfile(Customer customer);

    boolean registerGymOwner(GymOwner gymOwner);
}