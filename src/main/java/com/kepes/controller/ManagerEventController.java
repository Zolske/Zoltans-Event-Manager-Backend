package com.kepes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ManagerEventController {

    @GetMapping("/greeting")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello, from Zoltan's Event Manager!");
    }
}
