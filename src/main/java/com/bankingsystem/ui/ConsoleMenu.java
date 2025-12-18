package com.bankingsystem.ui;

import com.bankingsystem.model.Account;
import com.bankingsystem.service.AccountService;
import com.bankingsystem.repository.AccountRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Console-based user interface for the banking system.
 */
public class ConsoleMenu {

    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(AccountService accountService,
                       AccountRepository accountRepository) {
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }

    public void start() {

        boolean running = true;

        while (running) {
            printMenu();
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1" -> deposit();
                    case "2" -> withdraw();
                    case "3" -> transfer();
                    case "4" -> running = false;
                    default -> System.out.println("Invalid option");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                
                === Banking System ===
                1. Create Customer
                2. Create Account 
                3. Deposit
                4. Withdraw
                5. Transfer
                6. Exit
                """);
        System.out.print("Choose option: ");
    }

    private void deposit() throws SQLException {
        Account account = getAccount("Enter account number: ");
        BigDecimal amount = getAmount("Enter deposit amount: ");

        accountService.deposit(account, amount);
        System.out.println("Deposit successful");
    }

    private void withdraw() throws SQLException {
        Account account = getAccount("Enter account number: ");
        BigDecimal amount = getAmount("Enter withdrawal amount: ");

        accountService.withdraw(account, amount);
        System.out.println("Withdrawal successful");
    }

    private void transfer() throws SQLException {
        Account from = getAccount("From account number: ");
        Account to = getAccount("To account number: ");
        BigDecimal amount = getAmount("Enter transfer amount: ");

        accountService.transfer(from, to, amount);
        System.out.println("Transfer successful");
    }

    private Account getAccount(String prompt) throws SQLException {
        System.out.print(prompt);
        Long accountNumber = Long.parseLong(scanner.nextLine());

        Account account = accountRepository
                .findByAccountNumber(accountNumber);

        if (account == null) {
            throw new IllegalArgumentException("Account not found");
        }
        return account;
    }

    private BigDecimal getAmount(String prompt) {
        System.out.print(prompt);
        return new BigDecimal(scanner.nextLine());
    }
}
