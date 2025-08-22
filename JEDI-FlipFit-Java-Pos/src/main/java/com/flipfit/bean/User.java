package com.flipfit.bean;

//import io.dropwizard.validation.InterpolationHelper;
//import org.apache.commons.lang3.IntegerRange;

/**
 * Represents a generic user in the FlipFit system.
 * This is the base class for different user types like {@link Admin},
 * {@link Customer}, and {@link GymOwner}, providing common properties
 * such as email, password, and role ID.
 *
 * @author Kriti
 */
public class User {
    private String email;
    private String password;
    private String roleId;

    /**
     * Constructs a new User with the specified details.
     *
     * @param email The unique email address of the user.
     * @param password The account password.
     * @param roleId The unique identifier for the user's role.
     */
    public User(String email, String password, String roleId) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }

    /**
     * Constructs a default User object.
     */
    public User() {
        // Default constructor
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email The new email address.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password for the user's account.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the user's account.
     *
     * @param password The new password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retrieves the unique identifier for the user's role.
     *
     * @return The role ID.
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * Sets the unique identifier for the user's role.
     *
     * @param roleId The new role ID.
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}