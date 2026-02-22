package com.bankingsystem.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {
    private final Long transactionId;
    private final Long accountId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final LocalDateTime timestamp;
    private final String description;

    public Transaction(Long transactionId, Long accountId, BigDecimal amount,
                       TransactionType type, String description) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.timestamp = LocalDateTime.now();
    }


    public Long getTransactionId() { return transactionId; }
    public Long getAccountId() { return accountId; }
    public BigDecimal getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDescription() { return description; }

    @Override
    public String toString() {
        return String.format("[%s] %-12s: $%s - %s",
                timestamp.toLocalDate(), type, amount, description);
    }
}