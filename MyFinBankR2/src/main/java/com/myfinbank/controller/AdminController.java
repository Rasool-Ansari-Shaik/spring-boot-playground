package com.myfinbank.controller;

import com.myfinbank.entity.Account;
import com.myfinbank.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Activate Customer
    @PostMapping("/customer/activate")
    public ResponseEntity<String> activateCustomer(@RequestParam("username") String username) {
        // Activate Customer
        boolean isActive = adminService.activateCustomer(username);
        if (isActive) {
            return ResponseEntity.ok().body("Customer activated successfully");
        } else {
            return ResponseEntity.badRequest().body("Customer activation failed");
        }

    }

    // Deactivate Customer
    @PostMapping("/customer/deactivate")
    public ResponseEntity<String> deactivateCustomer(@RequestParam("username") String username) {
        // deactivate Customer
        boolean isDeactive = adminService.deactivateCustomer(username);
        if (isDeactive) {
            return ResponseEntity.ok().body("Customer deactivated successfully");
        } else {
            return ResponseEntity.badRequest().body("Customer deactivation failed");
        }
    }

    // Update Customer

    // Create Customer Account
    @PostMapping("/customer/account")
    public ResponseEntity<String> createCustomerAccount(@RequestParam("username") String username) {
        // Create Customer Account
        String customerAccount = adminService.createCustomerAccount(username);
        if (customerAccount == null) {
            return ResponseEntity.badRequest().body("User not found");
        } else {
            return ResponseEntity.ok().body("Customer account created: " + customerAccount);
        }
    }

    // Update Customer Account
    @PutMapping("/customer/account")
    public ResponseEntity<String> updateCustomerAccount(@RequestBody Account account) {
        // Update Customer Account
        boolean isUpdated = adminService.updateCustomerAccount(account);
        if (isUpdated) {
            return ResponseEntity.ok().body("Customer account updated successfully");
        } else {
            return ResponseEntity.badRequest().body("Customer account update failed");
        }
    }

    // Delete Customer Account
    @DeleteMapping("/customer/account")
    public ResponseEntity<String> deleteCustomerAccount(@RequestParam("accountNumber") String accountNumber) {
        // Delete Customer Account
        boolean isDeleted = adminService.deleteCustomerAccount(accountNumber);
        if (isDeleted) {
            return ResponseEntity.ok().body("Customer account deleted successfully");
        } else {
            return ResponseEntity.badRequest().body("Customer account delete failed");
        }
    }

    // Loan approval
    @PostMapping("/loan/approve")
    public ResponseEntity<String> approveLoan(@RequestParam("loanAccountNumber") String loanAccountNumber) {
        // Approve Loan
        boolean isApproved = adminService.approveLoan(loanAccountNumber);
        if (isApproved) {
            return ResponseEntity.ok().body("Loan approved successfully");
        } else {
            return ResponseEntity.badRequest().body("Loan approval failed");
        }
    }
}
