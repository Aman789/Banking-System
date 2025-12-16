package com.bankingsystem.model;

import java.util.List;

/*
 Customer class will represent bank customer
 Customer can own multiple bank account(one to many)
 */
public class Customer {
    // unique ID for each unique customer
    private Long id;

    // Customer name
    private String name;

    // Customer email
    private String email;

    // Customer accounts
    private List<Account> accounts;

    public Customer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
