package com.bankingsystem.model;

import java.math.BigDecimal;

/*
    represents the savings account
    interest build up on daily basis
 */

public class SavingsAccount extends Account {

    private BigDecimal interestRate;

    public SavingsAccount(Long accountNumber, BigDecimal balance, BigDecimal interestRate) {
        super(accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.SAVINGS;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
// getters & setters
}
