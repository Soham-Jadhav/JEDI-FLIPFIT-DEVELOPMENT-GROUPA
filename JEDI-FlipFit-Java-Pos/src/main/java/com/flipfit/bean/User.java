package com.flipfit.bean;

import io.dropwizard.validation.InterpolationHelper;
import org.apache.commons.lang3.IntegerRange;

public class User {
    private String email;
    private String password;
    private Integer roleId;
    public User(String email, String password, Integer roleId) {
        this.email = email;
        this.password = password;
        this.roleId = roleId;
    }
    public User(){

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
