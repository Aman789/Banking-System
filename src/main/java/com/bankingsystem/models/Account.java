package com.bankingsystem.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private Long accountId;
    private Long userId;
    private BigDecimal balance;
    private AccountType accountType;
    List<Transaction> transactions = new ArrayList<>();
    private Long transactionId = 1L;

    public Account(Long accountId, Long userId, BigDecimal balance, AccountType accountType) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.accountType = accountType;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }


    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public boolean deposit(BigDecimal amount) {
        // validating amount
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Deposit failed: Amount must be greater than zero.");
            return false;
        }

        this.balance = this.balance.add(amount);
        transactions.add(new Transaction(this.transactionId, this.accountId, amount, TransactionType.DEPOSIT, "Deposit"));
        this.transactionId++;

        return true;
    }
    public boolean withdraw(BigDecimal amount) {
        // Check if amount is positive and if balance is enough
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid amount.");
            return false;
        }

        if (amount.compareTo(balance) > 0) {
            System.out.println("Insufficient funds.");
            return false;
        }

        this.balance = this.balance.subtract(amount);
        transactions.add(new Transaction(this.transactionId, this.accountId, amount, TransactionType.WITHDRAWAL, "W/D"));
        this.transactionId++;

        return true;
    }
}
