package com.bankingsystem;

import com.bankingsystem.model.AccountService;
import com.bankingsystem.model.ChequingAccount;
import com.bankingsystem.model.SavingsAccount;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Setup account");
        SavingsAccount savAcct = new SavingsAccount(100L, new BigDecimal("500"), new BigDecimal("0.05"));
        ChequingAccount chqAcct = new ChequingAccount(200L, new BigDecimal("900"), new BigDecimal("100"));
        System.out.println(savAcct.getAccountType());

        AccountService accServe= new AccountService();
//        accServe.withdraw(savAcct, new BigDecimal("700"));
//        System.out.println(savAcct.getBalance());
        accServe.withdraw(savAcct, new BigDecimal("500"));
        System.out.println(savAcct.getBalance());
        accServe.deposit(savAcct, new BigDecimal("500"));
        System.out.println(savAcct.getBalance());

        accServe.transfer(chqAcct,savAcct, new BigDecimal("9000"));

    }
}