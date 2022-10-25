package com.example.fuelqueue.correct.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("userId")
    @Expose
    private String Id;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("userPassword")
    @Expose
    private String password;

    @SerializedName("userName")
    @Expose
    private String userName;

    @SerializedName("userType")
    @Expose
    private String role;

    public User(String id, String email, String password, String userName, String role) {
        Id = id;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.role = role;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}