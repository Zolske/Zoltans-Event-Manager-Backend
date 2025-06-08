package com.kepes.controller;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.kepes.auth.JwtUtil;
import com.kepes.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserServiceImpl userService;

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("")
    //public ResponseEntity<?> login(@RequestHeader("google-id-token") String googleIdToken, @RequestBody Map<String, String> user)
    public ResponseEntity<?> authenticateGoogleIdToken(@RequestHeader("google-id-token") String googleIdToken) {
        GoogleIdToken result = userService.verifyGoogleIdToken(googleIdToken);
        if (result != null) {
            System.out.println("valid google ID token");
            GoogleIdToken.Payload payload = result.getPayload();
            String jsonWebToken = jwtUtil.generateToken(googleIdToken);
            HttpHeaders responseHeaders = new HttpHeaders();
            responseHeaders.set("json-web-token", jsonWebToken);
            responseHeaders.set("user-id", payload.getSubject());
            Map<String, String> map = new HashMap<>();
            map.put("idUser", payload.getSubject());
            map.put("name", (String) payload.get("name"));
            map.put("pictureUrl", (String) payload.get("picture"));
            map.put("email",  payload.getEmail());
            return ResponseEntity.status(200).headers(responseHeaders).body(map);
        } else {
            return ResponseEntity.status(401).body("Invalid Google credentials");
        }


//        if(userLogin.hasAccount())
//            System.out.println("Is valid token");
//
//        // Fake auth logic. Replace with real user validation.
//        if ("admin".equals(username) && "password".equals(password)) {
//            String token = jwtUtil.generateToken(username);
//            return ResponseEntity.ok(Map.of("token", token, "userId", "123456789"));
//        } else {
//            return ResponseEntity.status(401).body("Invalid credentials");
//        }
    }



}