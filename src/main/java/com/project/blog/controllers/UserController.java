package com.project.blog.controllers;

import com.project.blog.entities.User;
import com.project.blog.models.UserModel;
import com.project.blog.services.UserService;
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
    public Optional<User> getUser(@PathVariable("id") Long userId){
        return userService.getUser(userId);
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }
}