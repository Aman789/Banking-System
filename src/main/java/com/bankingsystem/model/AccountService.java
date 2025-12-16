package com.bankingsystem.model;


import com.bankingsystem.exception.AccountFrozenException;
import com.bankingsystem.exception.InsufficientBalanceException;
import com.bankingsystem.exception.InvalidAmountException;

import javax.swing.plaf.basic.BasicColorChooserUI;
import java.math.BigDecimal;

/*
    The class will be used to perform account related activities
        - Transactions
        - Account status update
 */
public class AccountService {

    /*
    To avoid any errors, we will always validate the account status and ammount
     */
    public void deposit(Account account, BigDecimal ammount) {
        // Generic methods to validate account and ammount being processed
        validateAccount(account);
        validateAmount(ammount);

        account.setBalance(account.getBalance().add(ammount));
    }


    public void withdraw(Account account, BigDecimal ammount) {
        validateAccount(account);
        validateAmount(ammount);
        BigDecimal currBalance = account.getBalance();

        if (currBalance.compareTo(ammount) >= 0) {
            account.setBalance(account.getBalance().subtract(ammount));
        } else {
            throw new InsufficientBalanceException("Not enough funds");
        }
    }

    public void transfer(Account fromAcc, Account toAcc, BigDecimal ammount) {
        validateAccount(fromAcc);
        validateAccount(toAcc);
        validateAmount(ammount);
        BigDecimal currBalance = fromAcc.getBalance();


        fromAcc.setBalance(fromAcc.getBalance().subtract(ammount));
        //call withdraw function to take out money from account
        withdraw(fromAcc, ammount);
        //call deposit function to deposit money to account
        deposit(toAcc, ammount);


    }

    public void closeAccount(Account account) {
        if (account.getStatus() == AccountStatus.CLOSED) {
            System.out.println("Account is closed");
        } else {
            account.setStatus(AccountStatus.CLOSED);
        }
    }

    public void suspendAccount(Account account) {
        if (account.getStatus() == AccountStatus.CLOSED) {
            System.out.println("Account is closed");
        } else if (account.getStatus() == AccountStatus.SUSPENDED) {
            System.out.println("Account is Suspended");
        } else {
            account.setStatus(AccountStatus.SUSPENDED);
        }
    }

    public void activateAccount(Account account) {
        if (account.getStatus() == AccountStatus.ACTIVE) {
            System.out.println("Account is already active");
        } else {
            account.setStatus(AccountStatus.ACTIVE);
        }
    }

    private void validateAccount(Account account) {
        if (account.getStatus() != AccountStatus.ACTIVE) {
            throw new AccountFrozenException("Account is not active");
        }
    }

    private void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount not valid to process the transaction");
        }
    }


}
