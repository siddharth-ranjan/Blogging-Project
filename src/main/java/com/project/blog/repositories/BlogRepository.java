package com.project.blog.repositories;

import com.project.blog.entities.Blog;
import com.project.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    public List<Blog> findAllByUser(Optional<User> user);
}
