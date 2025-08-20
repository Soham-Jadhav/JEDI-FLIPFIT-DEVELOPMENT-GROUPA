package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import com.flipfit.bean.Slot;
import java.util.List;

public interface GymOwnerBusinessInterface {

    GymOwner getProfile(String email);

    void editProfile(GymOwner gymOwnerNew);

    boolean addGym(GymCenter gym);

    void editGym(GymCenter gym);

    void addSlot(Slot slot);

    List<GymCenter> getGymDetail(String gymOwnerEmail);
}