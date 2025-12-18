package com.bankingsystem.service;

import com.bankingsystem.model.*;
import com.bankingsystem.repository.TransactionRepository;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Handles transaction creation and persistence.
 */
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void recordDeposit(Account account,
                              BigDecimal amount,
                              Connection connection) throws SQLException {

        record(account, TransactionType.DEPOSIT, amount, connection);
    }

    public void recordWithdrawal(Account account,
                                 BigDecimal amount,
                                 Connection connection) throws SQLException {

        record(account, TransactionType.WITHDRAWAL, amount, connection);
    }

    public void recordTransfer(Account from,
                               Account to,
                               BigDecimal amount,
                               Connection connection) throws SQLException {

        record(from, TransactionType.TRANSFER, amount, connection);
        record(to, TransactionType.TRANSFER, amount, connection);
    }

    private void record(Account account,
                        TransactionType type,
                        BigDecimal amount,
                        Connection connection) throws SQLException {

        Transaction transaction = new Transaction(
                null,
                account.getAccountNumber(),
                type,
                amount
        );

        transactionRepository.save(transaction, connection);
    }
}
