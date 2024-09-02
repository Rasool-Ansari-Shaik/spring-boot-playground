package com.myfinbank.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfinbank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);

}
