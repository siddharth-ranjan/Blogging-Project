package com.project.blog.services;

import com.project.blog.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface UserService {

    public User createUser(User user);

    public List<User> getUsers();

    public User getUser(Long id);
}
