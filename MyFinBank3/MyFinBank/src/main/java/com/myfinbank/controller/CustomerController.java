package com.myfinbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfinbank.model.Account;
import com.myfinbank.model.Transaction;
import com.myfinbank.service.AccountService;
import com.myfinbank.service.UserService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    @PostMapping("/deposit")
    public Account deposit(@RequestParam Long accountId, @RequestParam Double amount) {
        Account account = accountService.findAccountById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return accountService.deposit(account, amount);
    }

    @PostMapping("/withdraw")
    public Account withdraw(@RequestParam Long accountId, @RequestParam Double amount) {
        Account account = accountService.findAccountById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        return accountService.withdraw(account, amount);
    }

    @PostMapping("/transfer")
    public Transaction transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam Double amount) {
        Account fromAccount = accountService.findAccountById(fromAccountId).orElseThrow(() -> new RuntimeException("From Account not found"));
        Account toAccount = accountService.findAccountById(toAccountId).orElseThrow(() -> new RuntimeException("To Account not found"));
        return accountService.transfer(fromAccount, toAccount, amount);
    }

    @GetMapping("/transactions")
    public List<Transaction> getTransactionHistory(@RequestParam Long accountId) {
        return accountService.getTransactionHistory(accountId);
    }
}
