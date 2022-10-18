package com.example.fuelqueue.model;

public class User {
    private final int Id;
    private final String email;
    private final String password;
    private String userName;


    public User(int id, String email, String password, String userName) {
        Id = id;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public int getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

}
