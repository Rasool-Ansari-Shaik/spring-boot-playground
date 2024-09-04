package com.myfinbank.service;

import com.myfinbank.entity.Account;
import com.myfinbank.entity.Loan;
import com.myfinbank.repository.AccountRepository;
import com.myfinbank.repository.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    private AccountRepository accountRepository;

    private LoanRepository loanRepository;

    public CustomerService(AccountRepository accountRepository, LoanRepository loanRepository) {
        this.accountRepository = accountRepository;
        this.loanRepository = loanRepository;
    }
    // Deposit amount
    public Account deposit(Account account) {
        // get account number
        String accountNumber = account.getAccountNumber();
        // get amount
        double depositAmount = account.getBalance();
        // get account by account number
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent()) {
            Account account1 = accountOptional.get();
            double existingBalance = account1.getBalance();
            double newBalance = existingBalance + depositAmount;
            account1.setBalance(newBalance);
            return accountRepository.save(account1);
        }
        return null;
    }

    // Withdraw amount
    public Account withdraw(Account account) {
        // get account number
        String accountNumber = account.getAccountNumber();
        // get amount
        double withdrawAmount = account.getBalance();
        // get account by account number
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent()) {
            Account account1 = accountOptional.get();
            double existingBalance = account1.getBalance();
            double newBalance = existingBalance - withdrawAmount;
            if (newBalance < 0) {
                logger.error("Insufficient balance");
                return null;
            }
            account1.setBalance(newBalance);
            return accountRepository.save(account1);
        }
        return null;
    }

    // Transfer amount
    public Account transfer(Account account, String toAccount) {
        // get account number
        String accountNumber = account.getAccountNumber();
        // get amount
        double transferAmount = account.getBalance();
        // get account by account number
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        Optional<Account> toAccountOptional = accountRepository.findByAccountNumber(toAccount);
        if (accountOptional.isPresent() && toAccountOptional.isPresent()) {
            Account account1 = accountOptional.get();
            double existingBalance = account1.getBalance();
            double newBalance = existingBalance - transferAmount;
            if (newBalance < 0) {
                logger.error("Insufficient balance");
                return null;
            }
            account1.setBalance(newBalance);

            // update toAccount
            Account toAccount1 = toAccountOptional.get();
            double toExistingBalance = toAccount1.getBalance();
            double toNewBalance = toExistingBalance + transferAmount;
            toAccount1.setBalance(toNewBalance);
            accountRepository.save(toAccount1);
            // update from account
            return accountRepository.save(account1);
        }
        return null;
    }


    // emi calculator
    public double calculateEmi(double loanAmount, int tenure) {
        double rateOfInterest = 10.0;
        double monthlyInterest = rateOfInterest / 100 / 12;
        int totalMonths = tenure * 12;
        double emi = (loanAmount * monthlyInterest * Math.pow(1 + monthlyInterest, totalMonths)) /
                (Math.pow(1 + monthlyInterest, totalMonths) - 1);
        return emi;
    }

    // apply for loan
    public double applyLoan(Loan loan) {
        // get account number
        String accountNumber = loan.getAccountNumber();
        // get account by account number
        Optional<Account> accountOptional = accountRepository.findByAccountNumber(accountNumber);
        if (accountOptional.isPresent()) {
            Account account1 = accountOptional.get();
            double emi = calculateEmi(loan.getLoanAmount(), loan.getTenure());
            // Create Loan Account Number
            String uuid = java.util.UUID.randomUUID().toString().substring(0, 10);

            Loan loan1 = new Loan();
            loan1.setUsername(account1.getUsername());
            loan1.setAccountNumber(account1.getAccountNumber());
            loan1.setLoanAccountNumber(uuid);
            loan1.setLoanAmount(loan.getLoanAmount());
            loan1.setTenure(loan.getTenure());
            loan1.setRateOfInterest(10.0);
            loan1.setEmi(emi);
            loan1.setLoanStatus(false);
            loan1.setCreatedAt(LocalDateTime.now());

            Loan savedLoan = loanRepository.save(loan);
            if (savedLoan != null) {
                return emi;
            } else {
                logger.error("Loan not applied");
                return 0;
            }
        } else {
            logger.error("Account not found: {}", accountNumber);
            return 0;
        }
    }
}
