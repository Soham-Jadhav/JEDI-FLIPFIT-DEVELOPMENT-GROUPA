/**
 *
 */
package com.flipfit.bean;

/**
 *
 */
public class Waitlist {
    private String waitlistId;
    private String userId;
    private String slotId;
    private int position;

    /**
     * @return the waitlistId
     */
    public String getWaitlistId() {
        return waitlistId;
    }
    /**
     * @param waitlistId the waitlistId to set
     */
    public void setWaitlistId(String waitlistId) {
        this.waitlistId = waitlistId;
    }
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the slotId
     */
    public String getSlotId() {
        return slotId;
    }
    /**
     * @param slotId the slotId to set
     */
    public void setSlotId(String slotId) {
        this.slotId = slotId;
    }
    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }
    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    public void notifyPromotion() {}
    public void addToWaitlist() {}
    public void removeFromWaitlist() {}
}