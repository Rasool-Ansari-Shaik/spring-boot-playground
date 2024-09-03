package com.myfinbank.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myfinbank.entity.Transaction;


@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findByUsername(String username);

	List<Transaction> findByTransactionId(String accountId);
}
