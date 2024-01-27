package com.project.blog.controllers;

import com.project.blog.entities.Blog;
import com.project.blog.entities.Comment;
import com.project.blog.repositories.BlogRepository;
import com.project.blog.repositories.CommentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {


    private BlogRepository blogRepository;

    private CommentRepository commentRepository;

    public CommentController(BlogRepository blogRepository, CommentRepository commentRepository) {
        this.blogRepository = blogRepository;
        this.commentRepository = commentRepository;
    }

    @PostMapping("/blog-{id}/comment")
    public ResponseEntity<Object> setComment(@PathVariable("id") Long blogId){
        Optional<Blog> blog = blogRepository.findById(blogId);

        if(blog.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
        }

        Comment comment = new Comment("Comment 1", blog.orElseThrow(), blog.get().getUser());
        commentRepository.save(comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/blog-{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("id") Long blogId){
        Optional<Blog> blog = blogRepository.findById(blogId);

        if(blog.isEmpty()  || blog.get().getComments().isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }

        List<Comment> comments = blog.get().getComments();
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/comment-{c_id}")
    public ResponseEntity<Comment> getCommentsById(@PathVariable("c_id") Long commentId){
        Optional<Comment> comment = commentRepository.findById(commentId);

        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(null));

    }
}