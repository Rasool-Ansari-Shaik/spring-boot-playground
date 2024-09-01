package com.gl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController 
{
	@GetMapping("/home")
	public String handleWelcome() {
		return "home";

	}

	@GetMapping("/admin/home")
	public String handleAdminHome() {

		return "home_admin";

	}

	@GetMapping("/customers/home")
	public String handleUserHome() {

		return "home_customer";

	}
}
