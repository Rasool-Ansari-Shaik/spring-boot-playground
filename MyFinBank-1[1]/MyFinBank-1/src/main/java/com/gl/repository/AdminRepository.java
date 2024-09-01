package com.gl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>
{
     
}
