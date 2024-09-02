package com.myfinbank.service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinbank.Repository.AccountRepository;
import com.myfinbank.Repository.TransactionRepository;
import com.myfinbank.Repository.UserRepository;
import com.myfinbank.model.Account;
import com.myfinbank.model.Transaction;
import com.myfinbank.model.User;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;


    @Autowired
    private UserRepository userRepository ;
    public Account createAccount(Account account) { 
    	// Fetch the user by ID
        User user = userRepository.findById(account.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

// Set the user to the account
account.setUser(user);
        return accountRepository.save(account);
    }

    public Optional<Account> findAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    public Account deposit(Account account, Double amount) {
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setAmount(amount);
        transaction.setType("DEPOSIT");
        transaction.setAccount(account);
        transactionRepository.save(transaction);

        return account;
    }

    public Account withdraw(Account account, Double amount) {
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setAmount(amount);
        transaction.setType("WITHDRAWAL");
        transaction.setAccount(account);
        transactionRepository.save(transaction);

        return account;
    }

    public Transaction transfer(Account fromAccount, Account toAccount, Double amount) {
        if (fromAccount.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        accountRepository.save(fromAccount);

        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);

        Transaction transaction = new Transaction();
        transaction.setTransactionId(UUID.randomUUID().toString());
        transaction.setAmount(amount);
        transaction.setType("TRANSFER");
        transaction.setAccount(fromAccount);
        transactionRepository.save(transaction);

        return transaction;
    }

    public List<Transaction> getTransactionHistory(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
    
 // Find all accounts (used in AdminController)
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    // Update an account (used in AdminController)
    public Account updateAccount(Account account) {
        Optional<Account> existingAccount = accountRepository.findById(account.getId());
        if (existingAccount.isPresent()) {
            return accountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }

    // Delete an account by ID (used in AdminController)
    public void deleteAccount(Long accountId) {
        accountRepository.deleteById(accountId);
    }
}
