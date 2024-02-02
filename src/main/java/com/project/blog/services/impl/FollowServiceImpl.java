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

        User follower = userService.getUser(followerId);
        User following = userService.getUser(followingId);

        if(follower == null) return ResponseEntity.badRequest().body("Invalid Follower");
        if(following == null) return ResponseEntity.badRequest().body("Invalid Following");

        Set<User> followers = follower.getFollowers();
        followers.add(following);


        userRepository.save(follower);
        userRepository.save(following);

        return ResponseEntity.ok("Success");
    }

    @Override
    public ResponseEntity<?> getFollowers(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) return ResponseEntity.badRequest().body("User not valid");

        Set<User> followers = user.get().getFollowers();
        if(followers.isEmpty()) return ResponseEntity.badRequest().body("User is yet to be followed");

        return ResponseEntity.ok(followers);
    }

    @Override
    public ResponseEntity<?> getFollowings(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) return ResponseEntity.badRequest().body("User not present");

        Set<User> followings = user.get().getFollowings();
        if(followings.isEmpty()) return ResponseEntity.badRequest().body("No followers found");
        return ResponseEntity.ok(followings);
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
