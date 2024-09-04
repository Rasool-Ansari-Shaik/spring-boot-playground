package com.myfinbank.controller;

import com.myfinbank.entity.User;
import com.myfinbank.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    // login
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        boolean isLogin = authService.login(user);
        if (isLogin) {
            return ResponseEntity.ok().body("User logged in successfully .......");
        } else {
            return ResponseEntity.badRequest().body("User login failed .......");
        }
    }
    // logout
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam("username") String username) {
        boolean isLogout = authService.logout(username);
        if (isLogout) {
            return ResponseEntity.ok().body("User logged out successfully .......");
        } else {
            return ResponseEntity.badRequest().body("User logout failed .......");
        }
    }

    // register
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        boolean register = authService.register(user);
        if (register)
            return "User created successfully";
        else
            return "User creation failed";
    }

    // forgot password

    // reset password

}
