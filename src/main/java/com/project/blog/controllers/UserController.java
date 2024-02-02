package com.project.blog.controllers;

import com.project.blog.entities.User;
import com.project.blog.models.UserModel;
import com.project.blog.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Tested
    @PostMapping("/create-user")
    public User createUser(@RequestBody UserModel userModel) {
        User user = new User();
        user.setContact(userModel.getContact());
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setAddress(userModel.getAddress());
        user.setLastName(userModel.getLastName());

        return userService.createUser(user);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long userId){
        User user = userService.getUser(userId);
        if(user == null) return ResponseEntity.badRequest().body("User not found");
        return ResponseEntity.ok(user);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }
}