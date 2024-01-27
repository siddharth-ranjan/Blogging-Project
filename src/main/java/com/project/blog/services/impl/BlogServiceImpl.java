package com.project.blog.services.impl;

import com.project.blog.entities.Blog;
import com.project.blog.entities.User;
import com.project.blog.repositories.BlogRepository;
import com.project.blog.repositories.UserRepository;
import com.project.blog.services.BlogService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BlogServiceImpl implements BlogService{

    private BlogRepository blogRepository;

    private UserRepository userRepository;

    public BlogServiceImpl(BlogRepository blogRepository, UserRepository userRepository) {
        this.blogRepository = blogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Blog createBlog(Blog blog, User user) {
        blog.setUser(user);
        return blogRepository.save(blog);
    }

    @Override
    public List<Blog> getBlogs() {
        return blogRepository.findAll();
    }

    @Override
    public List<Blog> getBlogsById(Long id) {
        return blogRepository.findAllByUser(userRepository.findById(id));
    }
}
