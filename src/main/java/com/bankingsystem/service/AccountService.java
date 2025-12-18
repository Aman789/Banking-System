package com.bankingsystem.service;

import com.bankingsystem.exception.*;
import com.bankingsystem.model.*;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.utility.DatabaseUtility;

import java.math.BigDecimal;
import java.sql.Connection;

/**
 * Core business logic for accounts.
 */
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionService transactionService;

    public AccountService(AccountRepository accountRepository,
                          TransactionService transactionService) {
        this.accountRepository = accountRepository;
        this.transactionService = transactionService;
    }

    public void deposit(Account account, BigDecimal amount) {

        validateAccount(account);
        validateAmount(amount);

        try (Connection connection = DatabaseUtility.getConnection()) {

            connection.setAutoCommit(false);

            account.setBalance(account.getBalance().add(amount));
            accountRepository.updateBalance(
                    account.getAccountNumber(),
                    account.getBalance(),
                    connection
            );

            transactionService.recordDeposit(account, amount, connection);

            connection.commit();

        } catch (Exception e) {
            throw new RuntimeException("Deposit failed", e);
        }
    }

    public void withdraw(Account account, BigDecimal amount) {

        validateAccount(account);
        validateAmount(amount);

        BigDecimal available = getAvailableBalance(account);
        if (amount.compareTo(available) > 0) {
            throw new InsufficientBalanceException("Insufficient funds");
        }

        try (Connection connection = DatabaseUtility.getConnection()) {

            connection.setAutoCommit(false);

            account.setBalance(account.getBalance().subtract(amount));
            accountRepository.updateBalance(
                    account.getAccountNumber(),
                    account.getBalance(),
                    connection
            );

            transactionService.recordWithdrawal(account, amount, connection);

            connection.commit();

        } catch (Exception e) {
            throw new RuntimeException("Withdrawal failed", e);
        }
    }

    public void transfer(Account from, Account to, BigDecimal amount) {

        validateAccount(from);
        validateAccount(to);
        validateAmount(amount);

        BigDecimal available = getAvailableBalance(from);
        if (amount.compareTo(available) > 0) {
            throw new InsufficientBalanceException("Insufficient funds");
        }

        try (Connection connection = DatabaseUtility.getConnection()) {

            connection.setAutoCommit(false);

            from.setBalance(from.getBalance().subtract(amount));
            to.setBalance(to.getBalance().add(amount));

            accountRepository.updateBalance(
                    from.getAccountNumber(),
                    from.getBalance(),
                    connection
            );

            accountRepository.updateBalance(
                    to.getAccountNumber(),
                    to.getBalance(),
                    connection
            );

            transactionService.recordTransfer(from, to, amount, connection);

            connection.commit();

        } catch (Exception e) {
            throw new RuntimeException("Transfer failed", e);
        }
    }

    // -------- Helper Methods --------

    private void validateAccount(Account account) {
        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountFrozenException("Account is not active");
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Invalid amount");
        }
    }

    private BigDecimal getAvailableBalance(Account account) {
        if (account instanceof ChequingAccount checking) {
            return checking.getBalance().add(checking.getOverdraftLimit());
        }
        return account.getBalance();
    }
}
