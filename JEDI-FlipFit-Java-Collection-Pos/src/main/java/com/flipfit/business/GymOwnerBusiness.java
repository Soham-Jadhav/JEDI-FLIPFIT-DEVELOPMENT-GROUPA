package com.flipfit.business;
import java.util.*;
import com.flipfit.dao.GymOwnerDAOImpl;
import com.flipfit.bean.GymOwner;
import com.flipfit.dao.UserDAOImpl;
import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;

public class GymOwnerBusiness implements GymOwnerBusinessInterface {
    UserDAOImpl userDAO = new UserDAOImpl();
    GymOwnerDAOImpl gymOwnerDAO = new GymOwnerDAOImpl();

    public GymOwner getProfile(String email) {
        System.out.println("Fetched Gym owner details successfully! " + email);
        return userDAO.getGymOwnerDetails(email);
    }

    public void editProfile(GymOwner gymOwnerNew) {
        userDAO.editGymOwnerDetails(gymOwnerNew);
        System.out.println("\nEdited your profile Successfully!");
    }

    public boolean addGym(GymCenter gym) {
        gymOwnerDAO.addGym(gym);
        System.out.println("\nAdded Gym Successfully!" + gym.getGymId());
        return true;
    }

    public void editGym(GymCenter gym) {
        gymOwnerDAO.editGym(gym);
        System.out.println("\nEdited Gym Details Successfully! ");
    }

    public void addSlot(Slot slot) {
        gymOwnerDAO.addSlot(slot);
        System.out.println("\nAdded slot successfully!");
    }

    public List<GymCenter> getGymDetail(String gymOwnerEmail) {
        System.out.println("\nFetched gym details successfully! " + gymOwnerEmail);
        return gymOwnerDAO.getGymsOfGymOwner(gymOwnerEmail);
    }
}