package com.bankingsystem;

import com.bankingsystem.config.DatabaseConfig;
import com.bankingsystem.ui.BankingUi;

public class Main {
    public static void main(String[] args) {
        System.out.println("Classpath: " + System.getProperty("java.class.path"));
        //Database connection
        DatabaseConfig.testConnection();
        /*
        // CLI ui
        BankingUi ui = new BankingUi();
        ui.start();
        */
    }

}