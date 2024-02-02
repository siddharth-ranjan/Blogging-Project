package com.project.blog.services;

import com.project.blog.entities.Blog;
import com.project.blog.entities.User;
import org.springframework.http.ResponseEntity;

public interface LikeDislikeService {

//    public ResponseEntity<String> likeBlog(Long blogId);
//
//    public ResponseEntity<String> likeComment(Long commentId);
//
//    public ResponseEntity<String> dislikeBlog(Long blogId);
//
//    public ResponseEntity<String> dislikeComment(Long commentId);

    public ResponseEntity<?> likeUnlikeBlog(Long userId, Long blogId);

    public ResponseEntity<?> dislikeUndislike(Long userId, Long blogId);

    int getDislikes(Long blogId);

    int getLikes(Long blogId);

}
