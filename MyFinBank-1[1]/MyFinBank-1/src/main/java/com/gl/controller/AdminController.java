package com.gl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gl.entity.Customer;
import com.gl.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
    private AdminService adminService;

    // Create or Update Customer
    @PostMapping("/customers")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = adminService.saveCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    // Get All Customers
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = adminService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Get Customer by ID
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customerOpt = adminService.getCustomerById(id);
        return customerOpt.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deactivate Customer
    @PutMapping("/customers/{id}/deactivate")
    public ResponseEntity<Customer> deactivateCustomer(@PathVariable Long id) {
        Customer deactivatedCustomer = adminService.deactivateCustomer(id);
        if (deactivatedCustomer != null) {
            return ResponseEntity.ok(deactivatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
