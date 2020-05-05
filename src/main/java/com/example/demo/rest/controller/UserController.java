package com.example.demo.rest.controller;

import com.example.demo.rest.model.user.UserRequestModel;
import com.example.demo.rest.model.user.UserResponseModel;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/user")
    public ResponseEntity<UserResponseModel> create(@RequestBody UserRequestModel user){
        LOGGER.info("Request to create user - {}",user);
        UserResponseModel createUser = userService.create(user);
        LOGGER.info("Response of: user is successfully created - {}",createUser);
        return ResponseEntity.ok(createUser);
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<UserResponseModel>> selectAllUsers(){
        LOGGER.info("Request to select all users");
        List<UserResponseModel> users = userService.selectAllUsers();
        LOGGER.info("Response of: all users are successfully selected - {}",users);
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserResponseModel> findUserById(@PathVariable Long id){
        LOGGER.info("Request to find user by id - {}",id);
        UserResponseModel user = userService.findUserById(id);
        LOGGER.info("Response of: user is successfully find by id - {}",user);
        return ResponseEntity.ok(user);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<UserResponseModel> update(@PathVariable Long id,@RequestBody UserRequestModel user){
        LOGGER.info("Request to update user by id - {} - {}",id,user);
        UserResponseModel updatedUser = userService.update(id, user);
        LOGGER.info("Response of: user is successfully updated by id - {}",updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping(value = "/user/{id}")
    public void delete(@PathVariable Long id){
        LOGGER.info("Request to delete user by id - {}",id);
        userService.delete(id);
        LOGGER.info("Response of: user is successfully deleted by id");
    }
}
