package com.project.blog.controllers;

import com.project.blog.entities.Blog;
import com.project.blog.entities.User;
import com.project.blog.models.BlogModel;
import com.project.blog.models.UserModel;
import com.project.blog.repositories.BlogRepository;
import com.project.blog.repositories.UserRepository;
import com.project.blog.services.BlogService;
import com.project.blog.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class BlogController {

    private BlogService blogService;

    private UserService userService;

    public BlogController(BlogService blogService, UserService userService) {
        this.blogService = blogService;
        this.userService = userService;
    }

    @PostMapping("/user-{id}/create-blog")
    public Blog createBlog(@PathVariable("id") Long userId, @RequestBody BlogModel blogModel){
        Blog blog = new Blog();
        blog.setContent(blogModel.getContent());
        blog.setTitle(blogModel.getTitle());

        Optional<User> user = userService.getUser(userId);

        if(user.isEmpty()){
            return null;
        }

        return blogService.createBlog(blog, user.get());
    }

    @GetMapping("/blogs")
    public List<Blog> getBlogs(){
        return blogService.getBlogs();
    }


    @GetMapping("/user-{id}/blogs")
    public List<Blog> getBlog(@PathVariable("id") Long userId){
        return blogService.getBlogsById(userId);
    }
}
