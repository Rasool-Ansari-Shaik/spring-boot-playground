package com.gl.entity;

import java.util.Set;

import javax.management.relation.Role;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="customers")
public class Customer {
	 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    @Column(nullable = false)
	    private String username;
	    @Column(nullable = false, unique = true)
	    private String email;
	    @Column(nullable = false)
	    private String password;
	    @Column(nullable = false)
	    private String phone;
	    @Column(nullable = false)
	    private String address;
	    
	    @Column(nullable = false)
	    private boolean active = true; // Default to true (active)
	    
	    // Many customers can be managed by one admin
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "admin_id")
	    @JsonIgnoreProperties({"customers","hiberanteLazyIntializer","handler"})
	    private Admin admin;
	    
	    private Set<Role> roles;
	    
	    
	
	    
	    

}
