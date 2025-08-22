package com.flipfit.bean;

/**
 * Represents a user role in the FlipFit system.
 * This class defines a role with a unique ID and a name,
 * which can be used to manage permissions and access levels.
 *
 * @author Dhruv
 */
public class Role {
    /**
     * The unique identifier for the role.
     */
    public int RoleId;

    /**
     * The name of the role (e.g., "Admin", "Customer", "Gym Owner").
     */
    public String RoleName;

    /**
     * Retrieves the unique ID of the role.
     *
     * @return The role's unique identifier.
     */
    public int getRoleId() {
        return RoleId;
    }

    /**
     * Sets the unique ID of the role.
     *
     * @param roleId The new role ID.
     */
    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    /**
     * Retrieves the name of the role.
     *
     * @return The role's name.
     */
    public String getRoleName() {
        return RoleName;
    }

    /**
     * Sets the name of the role.
     *
     * @param roleName The new role name.
     */
    public void setRoleName(String roleName) {
        RoleName = roleName;
    }
}