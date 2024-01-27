package com.project.blog.controllers;

import com.project.blog.entities.Blog;
import com.project.blog.entities.Comment;
import com.project.blog.entities.User;
import com.project.blog.models.CommentModel;
import com.project.blog.repositories.BlogRepository;
import com.project.blog.repositories.CommentRepository;
import com.project.blog.services.BlogService;
import com.project.blog.services.CommentService;
import com.project.blog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {


//    private BlogRepository blogRepository;
//
//    private CommentRepository commentRepository;

    private CommentService commentService;

    private BlogService blogService;

    private UserService userService;

    public CommentController(CommentService commentService, BlogService blogService, UserService userService) {
        this.commentService = commentService;
        this.blogService = blogService;
        this.userService = userService;
    }

    //    public CommentController(BlogRepository blogRepository, CommentRepository commentRepository, CommentService commentService) {
//        this.blogRepository = blogRepository;
//        this.commentRepository = commentRepository;
//        this.commentService = commentService;
//    }

//    @PostMapping("/blog-{id}/comment")
//    public ResponseEntity<Object> setComment(@PathVariable("id") Long blogId,){
//        Optional<Blog> blog = blogRepository.findById(blogId);
//
//        if(blog.isEmpty()){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad Request");
//        }
//
//        Comment comment = new Comment("Comment 1", blog.orElseThrow(), blog.get().getUser());
//        commentRepository.save(comment);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
//    }

    //    @GetMapping("/blog-{id}/comments")
//    public ResponseEntity<List<Comment>> getComments(@PathVariable("id") Long blogId){
//        Optional<Blog> blog = blogRepository.findById(blogId);
//
//        if(blog.isEmpty()  || blog.get().getComments().isEmpty()){
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        List<Comment> comments = blog.get().getComments();
//        return ResponseEntity.ok(comments);
//    }

//    @GetMapping("/comment-{c_id}")
//    public ResponseEntity<Comment> getCommentsById(@PathVariable("c_id") Long commentId){
//        Optional<Comment> comment = commentRepository.findById(commentId);
//
//        return comment.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(null));
//
//    }


    @PostMapping("/user-{uid}/blog-{id}/comment")
    public ResponseEntity<Object> setComment(@PathVariable("uid") Long userId, @PathVariable("id") Long blogId, @RequestBody CommentModel commentModel){

//        Optional<User> user = userService.getUser(userId);
//
//        Optional<Blog> blog = blogService.getBlogs()

        Comment comment = new Comment();
        comment.setComment(commentModel.getComment());

        return commentService.setComment(userId, blogId, comment);
    }

    @GetMapping("/blog-{id}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable("id") Long blogId){
        return commentService.getComments(blogId);
    }


    @GetMapping("/comment-{c_id}")
    public ResponseEntity<Comment> getCommentsById(@PathVariable("c_id") Long commentId){
        return commentService.getCommentsById(commentId);
    }
}