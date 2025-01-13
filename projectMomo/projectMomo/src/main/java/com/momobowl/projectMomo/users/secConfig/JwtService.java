package com.momobowl.projectMomo.users.secConfig;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private final String SECRET_KEY = "kshitiz"; // Replace with a secure key
    private final long EXPIRATION_TIME = 3600000; // 1 hour in milliseconds

    // Generate JWT Token
    public String generateToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(Algorithm.HMAC256(SECRET_KEY.getBytes()));
    }

    // Validate JWT Token
    public String validateToken(String token) {
        try {
            return JWT.require(Algorithm.HMAC256(SECRET_KEY.getBytes()))
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid JWT token");
        }
    }
}
