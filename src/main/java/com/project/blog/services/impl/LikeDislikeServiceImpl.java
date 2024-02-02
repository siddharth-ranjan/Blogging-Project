package com.project.blog.services.impl;

import com.project.blog.entities.Blog;
import com.project.blog.entities.Comment;
import com.project.blog.entities.User;
import com.project.blog.repositories.BlogRepository;
import com.project.blog.services.BlogService;
import com.project.blog.services.CommentService;
import com.project.blog.services.LikeDislikeService;
import com.project.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class LikeDislikeServiceImpl implements LikeDislikeService {

    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @Autowired
    private BlogRepository blogRepository;

    @Override
    public ResponseEntity<?> likeUnlikeBlog(Long userId, Long blogId) {

        Optional<User> userOptional = Optional.ofNullable(userService.getUser(userId));

//        User userOptional = userService.getUser(userId);

        if(userOptional.isEmpty()) return ResponseEntity.badRequest().body("User not present");

        User currentUser = userOptional.get();

        Optional<Blog> blogOptional = Optional.ofNullable(blogService.getBlogById(blogId));

        if(blogOptional.isEmpty()) return ResponseEntity.badRequest().body("Blog not present");

        Blog currentBlog = blogOptional.get();

        Set<User> likes = currentBlog.getLikes();

        if(likes.contains(currentUser)) likes.remove(currentUser);
        else likes.add(currentUser);

        currentBlog.setLikes(likes);
        blogRepository.save(currentBlog);
        return ResponseEntity.ok("Success");

    }

    @Override
    public ResponseEntity<?> dislikeUndislike(Long userId, Long blogId) {
        Optional<User> userOptional = Optional.ofNullable(userService.getUser(userId));

        if(userOptional.isEmpty()) return ResponseEntity.badRequest().body("User not present");

        User currentUser = userOptional.get();

        Optional<Blog> blogOptional = Optional.ofNullable(blogService.getBlogById(blogId));

        if(blogOptional.isEmpty()) return ResponseEntity.badRequest().body("Blog not present");

        Blog currentBlog = blogOptional.get();

        Set<User> dislikes = currentBlog.getDislikes();

        if(dislikes.contains(currentUser)) dislikes.remove(currentUser);
        else dislikes.add(currentUser);

        currentBlog.setDislikes(dislikes);
        blogRepository.save(currentBlog);
        return ResponseEntity.ok("Success");

    }

    public int getLikes(Long blogId){
        Blog blog = blogService.getBlogById(blogId);
        if(blog == null) return 0;
//        return blogService.getBlogById(blogId).getDislikes().size();

        Set<User> likes = blog.getLikes();
        return likes.size();
    }

    public int getDislikes(Long blogId){
        Blog blog = blogService.getBlogById(blogId);
        if(blog == null) return 0;
//        return blogService.getBlogById(blogId).getDislikes().size();

        Set<User> dislikes = blog.getDislikes();
        return dislikes.size();
    }
}
