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
}
