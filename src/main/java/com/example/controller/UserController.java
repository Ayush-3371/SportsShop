package com.example.controller;

import com.example.entity.User;
import com.example.exceptions.UserNotFoundException;
import com.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    public UserController() {
    }

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    ResponseEntity<?> addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @DeleteMapping("/{id}")
    void deleteUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        this.userService.deleteUserById(id);
    }

    @PutMapping("/update")
    User updateUser(@RequestBody User user) throws UserNotFoundException {
        return this.userService.updateUser(user);
    }

    @GetMapping("/{id}")
    User getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
        return this.userService.getUser(id);
    }

    @GetMapping("/getByUserId/{userId}")
    User getUserByUserId(@PathVariable("userId") String userId) throws UserNotFoundException {
        return this.userService.getUserByUserId(userId);
    }

    @GetMapping("/getAllUser")
    List<User> getAllUser() {
        return this.userService.getAllUser();
    }
}

