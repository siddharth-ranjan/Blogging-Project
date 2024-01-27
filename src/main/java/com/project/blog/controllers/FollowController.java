package com.project.blog.controllers;

import com.project.blog.entities.User;
import com.project.blog.services.FollowService;
import com.project.blog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@RestController
public class FollowController {

    private FollowService followService;

    public FollowController(FollowService followService) {
        this.followService = followService;
    }

//    public FollowController(UserService userService) {
//        this.userService = userService;
//    }

    @PostMapping("/user-{followerId}/follow-{id}")
    public ResponseEntity<?> follow(
            @PathVariable("followerId") Long followerId,
            @PathVariable("id") Long followingId){

        if(Objects.equals(followerId, followingId)) return ResponseEntity.badRequest().body("User can not follow itself.");
        return followService.follow(followerId, followingId);
    }

    @GetMapping("user/{id}/followers")
    public ResponseEntity<Set<User>> getFollowers(@PathVariable("id") Long userId){
        return followService.getFollowers(userId);
    }


    @GetMapping("user/{id}/followings")
    public ResponseEntity<Set<User>> getFollowings(@PathVariable("id") Long userId){
        return followService.getFollowings(userId);
    }
}
