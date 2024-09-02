package com.myfinbank.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfinbank.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String accountNumber);
}
