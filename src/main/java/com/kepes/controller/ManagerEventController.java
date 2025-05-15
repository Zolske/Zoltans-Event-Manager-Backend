package com.kepes.controller;

import com.kepes.model.User;
import com.kepes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ManagerEventController {

    @Autowired
    private UserService userService;

    @GetMapping("/greeting")
    public ResponseEntity<String> greet() {
        return ResponseEntity.ok("Hello, from Zoltan's Event Manager!");
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }
}
