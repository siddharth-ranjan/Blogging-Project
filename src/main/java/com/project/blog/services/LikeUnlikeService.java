package com.project.blog.services;

import org.springframework.http.ResponseEntity;

public interface LikeUnlikeService {

    public ResponseEntity<String> likeBlog(Long blogId);

    public ResponseEntity<String> likeComment(Long commentId);

    public ResponseEntity<String> dislikeBlog(Long blogId);

    public ResponseEntity<String> dislikeComment(Long commentId);
}
