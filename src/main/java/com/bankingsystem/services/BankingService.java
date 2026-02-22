package com.bankingsystem.services;

import com.bankingsystem.models.Account;
import com.bankingsystem.models.AccountType;
import com.bankingsystem.models.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class BankingService {
    private HashMap<Long, User> users = new HashMap<>();
    private HashMap<Long, Account> accounts = new HashMap<>();
    private HashMap<Long, List<Long>> userAccounts = new HashMap<>();
    private long userIdcounter = 1;
    private long accountIdcounter = 1;

    public User createUser(String email, String fullName, String pin){
        User user = new User(userIdcounter, email, fullName, pin);
        users.put(userIdcounter, user);
        userIdcounter++;
        return user;
    }

    public Account createAccount(Long userId, AccountType accountType){
        if (!users.containsKey(userId)){
            System.out.println("User " + userId + " does not exist");
            return null;
        }
        if (!userAccounts.containsKey(userId)) {
            userAccounts.put(userId, new ArrayList<>());
        }
        userAccounts.get(userId).add(accountIdcounter);
        Account account = new Account(accountIdcounter, userId,BigDecimal.ZERO, accountType);
        accounts.put(accountIdcounter, account);
        accountIdcounter++;
        return account;
    }

    public boolean transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        Account fromAccount = accounts.get(fromAccountId);
        Account toAccount = accounts.get(toAccountId);

        if (fromAccount == null || toAccount == null) {
            return false;
        }

        if (fromAccount.withdraw(amount)) {
            toAccount.deposit(amount);
            return true;
        }

        return false;
    }

    public User getUserbyId(Long userId) {
        return users.get(userId);
    }

    public HashMap<Long, Account> getAccounts() {
        return accounts;
    }


    public List<Long> getUserAccounts(Long userId) {
        return userAccounts.get(userId);
    }

    public User getUserbyEmail(String email) {
        for (User user : users.values()){
            if (user.getEmail().equals(email)){
                return user;
            }
        }
        return null;
    }
}
