package com.example.service;

import com.example.entity.User;
import com.example.exceptions.UserNotFoundException;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepo;

    @Override
    public ResponseEntity addUser(User user) {
        userRepo.save(user);
        return new ResponseEntity("New user details added successfully.", HttpStatus.CREATED);
    }

    @Override
    public void deleteUserById(long id) throws UserNotFoundException {
        Optional<User> optObj = userRepo.findById(id);
        if(optObj.isPresent()) {
            this.userRepo.deleteById(id);
            System.out.println("User deleted...");
        }

        else throw new UserNotFoundException("Cannot find any user with id " + id);
    }

    @Override
    public User updateUser(User user) throws UserNotFoundException {
        Optional<User> optObj  = userRepo.findById(user.getId());
        if(optObj.isEmpty()) {
            throw new UserNotFoundException("Cannot find any user with id " + user.getId());
        }

        return this.userRepo.save(user);

    }

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        Optional<User> optObj = userRepo.findById(id);
        if(optObj.isPresent()) {
            return optObj.get();
        }

        throw new UserNotFoundException("Cannot find any user with id " + id);
    }

    @Override
    public User getUserByUserId(String userId) throws UserNotFoundException {
        User u = userRepo.findUserByUserId(userId);
        if(u != null) {
            return u;
        }

        throw new UserNotFoundException("Cannot find any user with user id " + userId);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepo.findAll();
    }
}
