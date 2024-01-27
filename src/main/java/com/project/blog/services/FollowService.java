package com.project.blog.services;

import com.project.blog.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.ResourceBundle;
import java.util.Set;

public interface FollowService {

    public ResponseEntity<String> follow(Long followerId, Long followingId);

    public ResponseEntity<Set<User>> getFollowers(Long userId);

    public ResponseEntity<Set<User>> getFollowings(Long userId);
}
