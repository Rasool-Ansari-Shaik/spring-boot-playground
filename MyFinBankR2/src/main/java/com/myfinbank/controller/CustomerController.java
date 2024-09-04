package com.myfinbank.controller;

import com.myfinbank.entity.Account;
import com.myfinbank.entity.Loan;
import com.myfinbank.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Deposit amount
    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody Account account) {
        Account depositAccount = customerService.deposit(account);
        if (depositAccount != null) {
            double newBalance = depositAccount.getBalance();
            return ResponseEntity.ok().body("Amount deposited successfully with new balance: " + newBalance);
        } else {
            return ResponseEntity.badRequest().body("Amount deposit failed");
        }
    }

    // Withdraw amount
    @PostMapping("/withdraw")
    public ResponseEntity<String> withdraw(@RequestBody Account account) {
        Account withdrawAccount = customerService.withdraw(account);
        if (withdrawAccount != null) {
            double newBalance = withdrawAccount.getBalance();
            return ResponseEntity.ok().body("Amount withdrawn successfully with new balance: " + newBalance);
        } else {
            return ResponseEntity.badRequest().body("Amount withdraw failed");
        }
    }

    // Transfer amount
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody Account account, @RequestParam("toAccount") String toAccount) {
        Account transferAccount = customerService.transfer(account, toAccount);
        if (transferAccount != null) {
            double newBalance = transferAccount.getBalance();
            return ResponseEntity.ok().body("Amount transferred successfully with new balance: " + newBalance);
        } else {
            return ResponseEntity.badRequest().body("Amount transfer failed");
        }
    }

    // Apply Loan - Calculate EMI
    @PostMapping("/loan/emiCalculator")
    public ResponseEntity<String> applyLoan(@RequestParam("loanAmount") double loanAmount, @RequestParam("tenure") int tenure) {
        double emi = customerService.calculateEmi(loanAmount, tenure);
        if (emi > 0) {
            return ResponseEntity.ok().body("EMI would be : " + emi);
        } else {
            return ResponseEntity.badRequest().body("EMI cannot be calculated");
        }
    }

    // Apply Loan - Calculate EMI
    @PostMapping("/loan/apply")
    public ResponseEntity<String> applyLoan(@RequestBody Loan loan) {
        double emi = customerService.applyLoan(loan);
        if (emi > 0) {
            return ResponseEntity.ok().body("Loan applied successfully with EMI: " + emi);
        } else {
            return ResponseEntity.badRequest().body("Loan apply failed");
        }
    }



}
