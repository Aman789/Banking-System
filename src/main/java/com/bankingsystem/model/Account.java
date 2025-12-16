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

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public abstract AccountType getAccountType();
}
