package com.myfinbank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myfinbank.entity.Account;
import com.myfinbank.entity.User;
import com.myfinbank.service.AccountService;
import com.myfinbank.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/users/deactivate")
    public User deactivateUser(@RequestParam Long userId) {
        return userService.deactivateUser(userId);
    }

    @PostMapping("/users/activate")
    public User activateUser(@RequestParam Long userId) {
        return userService.activateUser(userId);
    }

    @PostMapping("/accounts/create")
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        return accountService.findAll();
    }

    @PutMapping("/accounts/update")
    public Account updateAccount(@RequestBody Account account) {
        return accountService.updateAccount(account);
    }

    @DeleteMapping("/accounts/delete")
    public void deleteAccount(@RequestParam Long accountId) {
        accountService.deleteAccount(accountId);
    }
}
