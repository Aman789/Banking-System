package com.bankingsystem.model;

import java.math.BigDecimal;

/*
    Represents a basic bank account
    Creating abstract class for base class as blueprint for all types of accounts.
    ex. cheq and sav
    Common behaviour:
    Status, balance, id
     */
public abstract class Account {

    //unique id for each account number
    protected Long accountNumber;


    //Balance for account
    protected BigDecimal balance;

    protected AccountStatus status;

    protected Account(Long accountNumber, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = AccountStatus.ACTIVE;
    }


    public abstract AccountType getAccountType();
}
