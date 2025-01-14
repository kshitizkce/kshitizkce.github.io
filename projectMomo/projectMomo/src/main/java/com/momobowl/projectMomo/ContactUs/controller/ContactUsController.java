package com.momobowl.projectMomo.ContactUs.controller;

import com.momobowl.projectMomo.ContactUs.model.ContactUs;
import com.momobowl.projectMomo.ContactUs.service.ContactUsService;
import com.momobowl.projectMomo.users.secConfig.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contactus")
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    @Autowired
    JwtService jwtService;

    @PostMapping
    public ResponseEntity<String> createMessage(@RequestBody ContactUs contactUs, HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Authorization header missing or invalid");
        }

        String token = authorizationHeader.substring(7); // Extract token after "Bearer "
        try {
            // Validate the token
            String username = jwtService.validateToken(token);

            System.out.println(username);

            // If valid, proceed with saving the contact message
            String result = contactUsService.createContactUsMessage(
                    contactUs.getName(),
                    contactUs.getEmail(),
                    contactUs.getMessage(),
                    username
            );
            return ResponseEntity.ok(result);
        } catch (RuntimeException e) {
            // Handle invalid tokens
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}
