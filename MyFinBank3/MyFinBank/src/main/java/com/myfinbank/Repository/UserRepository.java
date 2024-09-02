package com.myfinbank.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myfinbank.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}