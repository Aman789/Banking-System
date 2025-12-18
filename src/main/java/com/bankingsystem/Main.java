package com.bankingsystem;

import com.bankingsystem.model.Account;
import com.bankingsystem.repository.AccountRepository;
import com.bankingsystem.repository.TransactionRepository;
import com.bankingsystem.service.AccountService;
import com.bankingsystem.model.ChequingAccount;
import com.bankingsystem.model.SavingsAccount;
import com.bankingsystem.service.TransactionService;
import com.bankingsystem.ui.ConsoleMenu;

import java.math.BigDecimal;
import java.sql.SQLException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {

        AccountRepository accountRepository = new AccountRepository();
        TransactionRepository transactionRepository =
                new TransactionRepository();

        TransactionService transactionService =
                new TransactionService(transactionRepository);

        AccountService accountService =
                new AccountService(accountRepository, transactionService);

        ConsoleMenu menu =
                new ConsoleMenu(accountService, accountRepository);

        menu.start();




    }
}