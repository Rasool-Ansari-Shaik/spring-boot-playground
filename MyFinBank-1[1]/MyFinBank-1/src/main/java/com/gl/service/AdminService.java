package com.gl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.entity.Customer;
import com.gl.repository.CustomerRepository;

@Service
public class AdminService 
{
	    @Autowired
	    private CustomerRepository customerRepository;

	    // Create or Update Customer
	    public Customer saveCustomer(Customer customer) {
	        return customerRepository.save(customer);
	    }

	    // Read all Customers
	    public List<Customer> getAllCustomers() {
	        return customerRepository.findAll();
	    }

	    // Read Customer by ID
	    public Optional<Customer> getCustomerById(Long id) {
	        return customerRepository.findById(id);
	    }

	    // Deactivate Customer (logical delete)
	    public Customer deactivateCustomer(Long id) {
	        Optional<Customer> customerOpt = customerRepository.findById(id);
	        if (customerOpt.isPresent()) {
	            Customer customer = customerOpt.get();
	            customer.setActive(false);  // Set the customer as inactive
	            return customerRepository.save(customer);
	        }
	        return null;  // Or throw an exception if preferred
	    }

	    // Activate Customer
	    public Customer activateCustomer(Long id) {
	        Optional<Customer> customerOpt = customerRepository.findById(id);
	        if (customerOpt.isPresent()) {
	            Customer customer = customerOpt.get();
	            customer.setActive(true);  // Set the customer as active
	            return customerRepository.save(customer);
	        }
	        return null;  // Or throw an exception if preferred
	    }

}
