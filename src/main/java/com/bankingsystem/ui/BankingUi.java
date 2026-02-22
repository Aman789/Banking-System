package com.bankingsystem.ui;

import com.bankingsystem.models.Account;
import com.bankingsystem.models.AccountType;
import com.bankingsystem.models.User;
import com.bankingsystem.services.BankingService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BankingUi {
    private final BankingService bankingService = new BankingService();
    private final Scanner scanner = new Scanner(System.in);
    private User currentUser = null;

    public void start() {
        int choice;
        do {
            if (currentUser == null) {
                displayGuestMenu();
                choice = scanner.nextInt();
                scanner.nextLine();
                handleGuestChoice(choice);
            } else {
                displayUserMenu();
                choice = scanner.nextInt();
                scanner.nextLine();
                handleUserChoice(choice);
            }
        } while (choice != 0);

        System.out.println("Goodbye!");
    }

    private void displayGuestMenu() {
        System.out.println("\n--- Welcome to the Bank ---");
        System.out.println("1. Create User");
        System.out.println("2. Login");
        System.out.println("0. Exit");
        System.out.print("Selection: ");
    }

    private void displayUserMenu() {
        System.out.println("\n--- User Dashboard [" + currentUser.getFullName() + "] ---");
        System.out.println("1. Open New Account");
        System.out.println("2. View My Accounts");
        System.out.println("3. Transactions (Deposit/Withdraw/Transfer)");
        System.out.println("4. Logout");
        System.out.println("0. Exit");
        System.out.print("Selection: ");
    }

    private void handleGuestChoice(int choice) {
        switch (choice) {
            case 1 -> createUserFlow();
            case 2 -> login();
            case 0 -> System.out.println("Exiting...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private void handleUserChoice(int choice) {
        switch (choice) {
            case 1 -> createAccountFlow();
            case 2 -> displayAccounts();
            case 3 -> displayTransactionMenu();
            case 4 -> logout();
            case 0 -> System.out.println("Exiting...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private void login(){
        System.out.println("Enter email: ");
        String email = scanner.nextLine();
        User user = bankingService.getUserbyEmail(email);
        if (user == null){
            System.out.println("Invalid email");
            return;
        }

        System.out.println("Enter pin: ");
        String pin = scanner.nextLine();
        if (!pin.equals(user.getPin())){
            System.out.println("Invalid pin");
            return;
        }
        this.currentUser = user;
        System.out.println("Welcome to Banking System: "+ user.getFullName());
    }

    private void displayTransactionMenu() {
        int selection;
        do {

            System.out.println("\n******* Transaction Menu *******");
            System.out.println("1. Deposit\n2. Withdraw\n3.Transfer\n4. Main Menu");
            System.out.print("Selection: ");
            selection = scanner.nextInt();
            scanner.nextLine();
            transactionChoice(selection);
        } while (selection != 4);

    }

    private void transactionChoice(int  selection) {

        switch (selection) {
            case 1 -> depositFunds();
            case 2 -> withdrawFunds();
            case 3 -> TransferFunds();
            case 4 -> System.out.println("Exiting...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private void TransferFunds() {
        Account fromAccount = selectAccount("Enter account to transfer funds from: ");
        if (fromAccount == null) { System.out.println("Source account not found."); return; }

        Account toAccount = selectAccount("Enter account to transfer funds to: ");
        if (toAccount == null) { System.out.println("Destination account not found."); return; }

        if (fromAccount.getAccountId().equals(toAccount.getAccountId())) {
            System.out.println("Error: Cannot transfer to the same account.");
            return;
        }

        System.out.print("Enter amount to transfer: ");
        BigDecimal amount = new BigDecimal(scanner.nextLine());

        // Call service and capture the result
        boolean success = bankingService.transfer(fromAccount.getAccountId(), toAccount.getAccountId(), amount);

        if (success) {
            System.out.println("Transfer successful!");
            System.out.println("New balance for " + fromAccount.getAccountId() + ": $" + fromAccount.getBalance());
        } else {
            System.out.println("Transfer failed. Insufficient funds or invalid amount.");
        }
    }

    private void withdrawFunds() {
        Account account = selectAccount("Enter account number: ");

        if (account == null){
            System.out.println("Invalid account number.");
            return;
        }
        System.out.println("Enter amount to withdraw: ");
        BigDecimal amount = new BigDecimal(scanner.nextLine());
        account.withdraw(amount);
        System.out.println("Withdrawal successful. New balance: " + account.getBalance());

    }

    private void depositFunds() {
        Account account = selectAccount("Enter account number: ");

        if (account == null) {
            System.out.println("Error: Account not found.");
            return; // Exit the method early
        }

        System.out.print("Enter Amount: ");
        // Reading as String is safer for BigDecimal
        String amountInput = scanner.nextLine();
        BigDecimal amount = new BigDecimal(amountInput);

        account.deposit(amount);
        System.out.println("Successfully deposited " + amount + " to account " + account.getAccountId());
    }

    private Account selectAccount(String msg) {
        System.out.print(msg);
        Long accountNumber = scanner.nextLong();
        scanner.nextLine(); // Always clear that buffer!
        return bankingService.getAccounts().get(accountNumber);
    }

    private void displayAccounts() {
        System.out.println("\n--- All Bank Accounts ---");
        List<Long> accounts = bankingService.getUserAccounts(this.currentUser.getId());
        for (var entry : accounts) {
            Account account = bankingService.getAccounts().get(entry);
            System.out.printf("ID: %d | Type: %-10s | Balance: $%.2f%n",
                    account.getAccountId(), account.getAccountType(), account.getBalance());
        }

//        for (var entry : bankingService.getAccounts().entrySet()) {
//            Long id = entry.getKey();
//            Account account = entry.getValue();
//            System.out.printf("ID: %d | Type: %-10s | Balance: $%.2f%n",
//                    id, account.getAccountType(), account.getBalance());
//        }
    }

    private void createUserFlow() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter pin: ");
        String pin = scanner.nextLine();
        currentUser = bankingService.createUser(email, name, pin);
        System.out.println("User created!");

    }

    private void createAccountFlow() {

        System.out.println("1. Chequing  2. Saving  3. Credit");
        System.out.print("Type: ");
        String typeChoice = scanner.nextLine();

        AccountType accountType = null;
        if (typeChoice.equals("1")) accountType = AccountType.CHECKING;
        else if (typeChoice.equals("2")) accountType = AccountType.SAVINGS;
        else if (typeChoice.equals("3")) accountType = AccountType.CREDIT;

        if (accountType != null) {
            Account newAcc = bankingService.createAccount(this.currentUser.getId(), accountType);
            if (newAcc != null) {
                System.out.println("Account #" + newAcc.getAccountId() + " opened.");
            } else {
                System.out.println("Error: User ID not found.");
            }
        } else {
            System.out.println("Invalid type selected.");
        }
    }
    private void logout() {
        System.out.println("Logging out " + currentUser.getFullName() + "...");
        this.currentUser = null;
    }

}
