package com.bankingsystem.model;

import java.math.BigDecimal;

public class ChequingAccount extends Account {

    private BigDecimal overdraftLimit;

    public ChequingAccount(Long accountNumber, BigDecimal balance, BigDecimal overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.CHECKING;
    }

    public BigDecimal getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(BigDecimal overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }
}
