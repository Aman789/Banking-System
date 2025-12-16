package com.bankingsystem.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private Long id;
    private Long accountNumber;
    private TransactionType type;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Transaction(Long id, Long accountNumber,
                       TransactionType type,
                       BigDecimal amount) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    // getters only (immutable)
}
