package com.kepes.controller;

import com.kepes.helper.GetHeader;
import com.kepes.model.User;
import com.kepes.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), GetHeader.success("Got all users."), HttpStatus.OK);
    }

    @DeleteMapping("/delete/user/{user-id}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.deleteUser(userId), GetHeader.success("Deleted user: " + userId), HttpStatus.OK);
    }

    //    @GetMapping("/toggle_admin/{user-id}")
    @GetMapping("/toggle_admin")
    public ResponseEntity<String> toggleAdminSetting(@RequestHeader("user-id") String userId) {
        return new ResponseEntity<String>(userService.toggleAdminSetting(userId),
                GetHeader.success("Toggled admin setting for user: " + userId), HttpStatus.OK);
    }
}

