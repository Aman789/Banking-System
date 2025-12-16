package com.bankingsystem;

import com.bankingsystem.model.SavingsAccount;

import java.math.BigDecimal;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.println("Setup account");
        SavingsAccount savAcct = new SavingsAccount(500L, new BigDecimal("500"), new BigDecimal("0.05"));

        System.out.println(savAcct.getAccountType());
        System.out.println(savAcct.getInterestRate());

    }
}