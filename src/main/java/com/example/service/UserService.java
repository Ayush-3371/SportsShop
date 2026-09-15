package com.example.service;

import com.example.entity.User;
import com.example.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    //Add
    ResponseEntity addUser(User user);

    //DELETE
    void deleteUserById(long id) throws UserNotFoundException;

    //UPDATE
    User updateUser(User user) throws UserNotFoundException;

    //Get
    User getUser(Long id) throws UserNotFoundException;

    //GET By UserId
    User getUserByUserId(String userId) throws UserNotFoundException;

    //GET all users
    List<User> getAllUser();
}
