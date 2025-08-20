package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.Slot;

import java.util.List;

public interface GymOwnerDAOInterface {

    void addGym(GymCenter gymDetails);

    void editGym(GymCenter gymDetails);

    void addSlot(Slot slot);

    List<GymCenter> getGymsOfGymOwner(String gymOwnerEmail);
}