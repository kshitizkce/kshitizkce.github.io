package com.momobowl.projectMomo.users.controller;

import com.momobowl.projectMomo.users.model.Users;
import com.momobowl.projectMomo.users.secConfig.JwtService;
import com.momobowl.projectMomo.users.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Allow requests from all origins
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private JwtService jwtService;


    private final AuthenticationManager authenticationManager;
    public UserController(AuthenticationManager authenticationManager , JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Users users) {
        if (userService.emailExists(users.getEmail())) {
            return ResponseEntity.badRequest().body("Sorry !! Email already exists. Dear : " + users.getEmail());
        }

        // Encode the password before saving
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        userService.saveUser(users);
        return ResponseEntity.ok("Signup successful!! Redirecting to login page.Welcome Dear : " + users.getEmail());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Users users) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(users.getEmail(), users.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Generate a JWT token for the authenticated user
            String token = jwtService.generateToken(users.getEmail());

            // Create a response containing email and token
            Map<String, String> response = new HashMap<>();
            response.put("email", users.getEmail());
            response.put("token", token);

            // Return the response as JSON
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid credentials : " + users.getEmail()));
        }
    }

//    @GetMapping("/userDetails")
//    public ResponseEntity<String> getUserDetails() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = authentication.getName();// Get the logged-in user's email
//        System.out.println(email);
//        return ResponseEntity.ok("Logged in as: " + email);
//    }
}