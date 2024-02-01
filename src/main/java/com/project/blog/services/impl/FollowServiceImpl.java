package com.project.blog.services.impl;

import com.project.blog.entities.User;
import com.project.blog.repositories.UserRepository;
import com.project.blog.services.FollowService;
import com.project.blog.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FollowServiceImpl implements FollowService {

    private UserService userService;
    private UserRepository userRepository;

    public FollowServiceImpl(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<String> follow(Long followerId, Long followingId) {
        Optional<User> follower = userService.getUser(followerId);
        Optional<User> following = userService.getUser(followingId);

        if(follower.isEmpty()) return ResponseEntity.badRequest().body("Invalid Follower");
        if(following.isEmpty()) return ResponseEntity.badRequest().body("Invalid Following");


        Set<User> followers = follower.get().getFollowers();
        followers.add(following.get());


        userRepository.save(follower.get());
        userRepository.save(following.get());

        return ResponseEntity.ok("Success");
    }

    @Override
    public ResponseEntity<Set<User>> getFollowers(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user.get().getFollowers());
    }

    @Override
    public ResponseEntity<Set<User>> getFollowings(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(user.get().getFollowings());
    }

    @Override
    public ResponseEntity<?> unfollow(Long userId, Long id) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        User currentUser = userOptional.get();

        Optional<User> followsOptional = userRepository.findById(id);
        if (followsOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User to unfollow not found");
        }

        User userToUnfollow = followsOptional.get();

        Set<User> newFollowings = new HashSet<>(currentUser.getFollowings());

        if (!newFollowings.contains(userToUnfollow)) {
            return ResponseEntity.badRequest().body("User does not follow!");
        }

        newFollowings.remove(userToUnfollow);
        currentUser.setFollowings(newFollowings);

        Set<User> userToUnfollowFollowers = new HashSet<>(userToUnfollow.getFollowers());
        userToUnfollowFollowers.remove(currentUser);
        userToUnfollow.setFollowers(userToUnfollowFollowers);

        userRepository.save(currentUser);
        userRepository.save(userToUnfollow);

        return ResponseEntity.ok("Unfollowed successfully");
    }


}
