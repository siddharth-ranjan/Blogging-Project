package com.project.blog.services;

import com.project.blog.entities.Blog;
import com.project.blog.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {

    public Blog createBlog(Blog blog, User user);

    public List<Blog> getBlogs();

    public List<Blog> getBlogsByUserId(Long userId);

    public Blog getBlogById(Long blogId);
}
