package com.kepes.controller;

import com.kepes.helper.GetHeader;
import com.kepes.model.User;
import com.kepes.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserContorller {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/data")
    public ResponseEntity<User> getUserById(@RequestHeader("user-id") String idUser) {
        return new ResponseEntity<>(userService.getUser(idUser), HttpStatus.OK);
    }

    @GetMapping("/get/get_all_users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), GetHeader.success("Event created."), HttpStatus.OK);
    }
}
