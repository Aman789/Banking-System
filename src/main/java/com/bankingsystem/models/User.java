package com.bankingsystem.models;

public class User {


    private Long id;
    private String email;
    private String fullName;
    // 4 digit pin
    private String pin;

    public User(Long id, String email, String fullName, String pin) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.pin = pin;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
