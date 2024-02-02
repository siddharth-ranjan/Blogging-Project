package com.project.blog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
public class Blog {

    @Id
    @SequenceGenerator(name = "blog_seq", sequenceName = "blog_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blog_seq")
    private Long blogId;

    private String title;

    private String content;

    @OneToMany()
    @JoinColumn(name = "user_id")
    private Set<User> likes = new HashSet<>();

    @OneToMany()
    @JoinColumn(name = "user_id")
    private Set<User> dislikes = new HashSet<>();

    private Long shared = 0L;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments;

    public Blog(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Blog() {
    }

    public void setBlogId(Long blogId) {
        this.blogId = blogId;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Long getBlogId() {
        return blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<User> getDislikes() {
        return dislikes;
    }

    public void setDislikes(Set<User> dislikes) {
        this.dislikes = dislikes;
    }

    public Long getShared() {
        return shared;
    }

    public void setShared(Long shared) {
        this.shared = shared;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "blogId=" + blogId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", shared=" + shared +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}
