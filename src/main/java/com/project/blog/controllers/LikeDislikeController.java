package com.project.blog.controllers;

import com.project.blog.services.LikeDislikeService;
//import com.project.blog.services.LikeUnlikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LikeDislikeController {

    private LikeDislikeService likeDislikeService;
    
    public LikeDislikeController(LikeDislikeService likeDislikeService) {
        this.likeDislikeService = likeDislikeService;
    }

    @GetMapping("/blog-{id}/likes")
    public int getLikes(@PathVariable("id") Long blogId){
        return likeDislikeService.getLikes(blogId);
    }

    @GetMapping("/blog-{id}/dislikes")
    public int getDislikes(@PathVariable("id") Long blogId){
        return likeDislikeService.getDislikes(blogId);
    }


    @PostMapping("/blog-{bId}/user-{id}/like-unlike")
    public ResponseEntity<?> likeUnlike(@PathVariable("bId") Long blog_id, @PathVariable("id") Long userId){
        return likeDislikeService.likeUnlikeBlog(userId, blog_id);
    }

    @PostMapping("/blog-{bId}/user-{id}/dislike-undislike")
    public ResponseEntity<?> dislikeUndislike(@PathVariable("bId") Long blog_id, @PathVariable("id") Long userId){
        return likeDislikeService.dislikeUndislike(userId, blog_id);
    }
}
