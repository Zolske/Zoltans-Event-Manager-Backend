package com.kepes.controller;

import com.kepes.auth.GoogleAuth;
import com.kepes.auth.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> user) {
        GoogleAuth googleAuth = new GoogleAuth();
        String username = user.get("username");
        String password = user.get("password");
        String idToken = user.get("idToken");
        if(googleAuth.isIdTokenValid(idToken))
            System.out.println("Is valid token");

        // Fake auth logic. Replace with real user validation.
        if ("admin".equals(username) && "password".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok(Map.of("token", token, "userId", "123456789"));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}

