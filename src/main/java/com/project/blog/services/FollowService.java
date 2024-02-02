package com.project.blog.services;

import com.project.blog.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.ResourceBundle;
import java.util.Set;

public interface FollowService {

    public ResponseEntity<String> follow(Long followerId, Long followingId);

    public ResponseEntity<?> getFollowers(Long userId);

    public ResponseEntity<?> getFollowings(Long userId);

    ResponseEntity<?> unfollow(Long userId, Long id);
}
