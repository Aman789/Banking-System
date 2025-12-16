package com.bankingsystem.model;


import javax.swing.plaf.basic.BasicColorChooserUI;
import java.math.BigDecimal;

/*
    The class will be used to perform account related activities
        - Transactions
        - Account status update
 */
public class AccountService {
    public void deposit(Account account, BigDecimal ammount){
        account.setBalance(account.getBalance().add(ammount));
    }

    public void withdraw(Account account, BigDecimal ammount){
        BigDecimal currBalance = account.getBalance();

        if (currBalance.compareTo(ammount) >= 0) {
            account.setBalance(account.getBalance().subtract(ammount));
        }
        else{
            System.out.println("Not enough funds");
        }
    }

    public void transaction(Account fromAcc, Account toAcc, BigDecimal ammount){
        BigDecimal currBalance = fromAcc.getBalance();

        if (currBalance.compareTo(ammount) >= 0) {
            fromAcc.setBalance(fromAcc.getBalance().subtract(ammount));
            //call withdraw function to take out money from account
            withdraw(fromAcc, ammount);

            //call deposit function to deposit money to account
            deposit(toAcc,ammount);
        }
        else {
            System.out.println("Not enough funds to transfer");
        }

    }

    public void closeAccount(Account account){
        if (account.getStatus() == AccountStatus.CLOSED){
            System.out.println("Account is closed");
        }
        else{
            account.setStatus(AccountStatus.CLOSED);
        }
    }

    public void suspendAccount(Account account){
        if (account.getStatus() == AccountStatus.CLOSED){
            System.out.println("Account is closed");
        } else if (account.getStatus() == AccountStatus.SUSPENDED) {
            System.out.println("Account is Suspended");
        } else{
            account.setStatus(AccountStatus.SUSPENDED);
        }
    }

    public void activateAccount(Account account){
        if (account.getStatus() == AccountStatus.ACTIVE){
            System.out.println("Account is already active");
        }  else{
            account.setStatus(AccountStatus.ACTIVE);
        }
    }


}
