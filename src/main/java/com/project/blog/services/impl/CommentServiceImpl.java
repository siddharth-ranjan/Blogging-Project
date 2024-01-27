package com.project.blog.services.impl;

import com.project.blog.entities.Blog;
import com.project.blog.entities.Comment;
import com.project.blog.entities.User;
import com.project.blog.repositories.BlogRepository;
import com.project.blog.repositories.CommentRepository;
import com.project.blog.repositories.UserRepository;
import com.project.blog.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    private BlogRepository blogRepository;

    private UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, BlogRepository blogRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Object> setComment(Long userId, Long blogId, Comment comment) {
        Optional<Blog> blog = blogRepository.findById(blogId);
//        System.out.println("blog = " + blog);
        if(blog.isEmpty()) return ResponseEntity.badRequest().body("Blog not found");


//        userRepository.findById(userId);
//        System.out.println("Till here");
        Optional<User> user = userRepository.findById(userId);
//        System.out.println("user = " + user);
        if(user.isEmpty()) return ResponseEntity.badRequest().body("User not accepted");

        comment.setBlog(blog.get());
        comment.setUser(user.get());



        //        System.out.println("blog = " + blog);
//        comment.setBlog(blog.get());
//        System.out.println("blog = " + blog);
//        commentRepository.save(comment);
//        System.out.println("blog = " + blog);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentRepository.save(comment));
    }

    @Override
    public ResponseEntity<List<Comment>> getComments(Long blogId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
//        return blog.map(value -> ResponseEntity.ok(value.getComments())).orElseGet(() -> ResponseEntity.badRequest().body(null));
        if(blog.isEmpty()) return ResponseEntity.badRequest().body(null);
        else{
            return ResponseEntity.ok(blog.get().getComments());
        }
    }

    @Override
    public ResponseEntity<Comment> getCommentsById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isEmpty()) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(comment.get());
    }
}
