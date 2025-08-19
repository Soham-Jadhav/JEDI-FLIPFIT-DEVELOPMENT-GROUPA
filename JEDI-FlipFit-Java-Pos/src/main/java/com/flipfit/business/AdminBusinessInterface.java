package com.flipfit.business;

import com.flipfit.bean.GymCenter;
import com.flipfit.bean.GymOwner;
import java.util.List;

public interface AdminBusinessInterface {

    List<GymOwner> getGymOwners();

    List<GymCenter> getGym();

    List<GymOwner> viewAllPendingGymOwnerRequests();

    boolean approveSingleGymOwnerRequest(String gymOwnerEmail);

    boolean approveAllPendingGymOwnerRequests();

    List<GymCenter> viewAllPendingGymRequests();

    boolean approveSingleGymRequest(String gymId);

    boolean approveAllPendingGymRequests();
}