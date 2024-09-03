package com.myfinbank.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myfinbank.repository.RoleRepository;
import com.myfinbank.entity.Role;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
