package com.project.blog.services;

import com.project.blog.entities.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface CommentService {

    public ResponseEntity<Object> setComment(Long userId, Long blogId, Comment comment);

    public ResponseEntity<List<Comment>> getComments(Long blogId);

    public ResponseEntity<Comment> getCommentsById(Long commentId);
}
