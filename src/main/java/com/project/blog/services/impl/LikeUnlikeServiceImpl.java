package com.project.blog.services.impl;

import com.project.blog.entities.Blog;
import com.project.blog.entities.Comment;
import com.project.blog.services.BlogService;
import com.project.blog.services.CommentService;
import com.project.blog.services.LikeUnlikeService;
import com.project.blog.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LikeUnlikeServiceImpl implements LikeUnlikeService {

    private BlogService blogService;

    private CommentService commentService;

    private UserService userService;

    public LikeUnlikeServiceImpl(BlogService blogService, CommentService commentService, UserService userService) {
        this.blogService = blogService;
        this.commentService = commentService;
        this.userService = userService;
    }

    @Override
    public ResponseEntity<String> likeBlog(Long blogId) {
        try{
            Blog blog = blogService.getBlogById(blogId);
            blog.setLikes(blog.getLikes() + 1);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Invalid Request");
        }
        return ResponseEntity.ok("Liked");
    }

    @Override
    public ResponseEntity<String> likeComment(Long commentId) {
        try{
            Comment comment = commentService.getCommentsById(commentId).getBody();
//            assert comment != null;
            comment.setLikes(comment.getLikes() + 1);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Invalid Request");
        }
        return ResponseEntity.ok("Liked");
    }

    @Override
    public ResponseEntity<String> dislikeBlog(Long blogId) {
        try{
            Blog blog = blogService.getBlogById(blogId);
            blog.setLikes(blog.getLikes() - 1);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Invalid Request");
        }
        return ResponseEntity.ok("Unliked");
    }

    @Override
    public ResponseEntity<String> dislikeComment(Long commentId) {
        try{
            Comment comment = commentService.getCommentsById(commentId).getBody();
//            assert comment != null;
            comment.setLikes(comment.getLikes() - 1);
        }catch(Exception e){
            return ResponseEntity.badRequest().body("Invalid Request");
        }
        return ResponseEntity.ok("Disliked");
    }
}
