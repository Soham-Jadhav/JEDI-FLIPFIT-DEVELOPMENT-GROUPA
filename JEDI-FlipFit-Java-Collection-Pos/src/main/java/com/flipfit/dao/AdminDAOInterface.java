package com.flipfit.dao;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import java.util.List;

public interface AdminDAOInterface {

    List<GymOwner> getAllGymOwners();

    List<GymCenter> getAllGyms();

    List<GymOwner> getPendingGymOwnerRequests();

    List<GymCenter> getPendingGymRequests();

    void approveSingleOwnerRequest(String gymOwnerEmail);

    void approveAllOwnerRequest();

    void approveSingleGymRequest(String gymId);

    void approveAllGymRequest();
}