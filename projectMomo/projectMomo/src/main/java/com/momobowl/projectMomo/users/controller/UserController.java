package com.momobowl.projectMomo.users.controller;

import com.momobowl.projectMomo.users.model.Users;
import com.momobowl.projectMomo.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow requests from all origins
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users users) {
        if (userService.emailExists(users.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists.");
        }

        userService.saveUser(users);
        return ResponseEntity.ok("Signup successful!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users users) {
        boolean isUserValid = userService.validateLogin(users.getEmail(), users.getPassword());
        if (!isUserValid) {
            // Return a simple string message when login is successful
            return ResponseEntity.badRequest().body("Username or email doesn't match");
        }else {
            return ResponseEntity.ok("Login successful");
        }
    }
}
