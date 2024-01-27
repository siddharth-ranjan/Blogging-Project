package com.project.blog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Comment {

    @Id
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    private Long comment_id;

    private String comment;

    private Long likes = 0L;

    private Long dislikes = 0L;

    @OneToMany()
    @JoinColumn(name = "tagged_user_id")
    @JsonBackReference
    private Set<User> taggedUsers;

    @ManyToOne()
    @JoinColumn(name = "blog_id", nullable = false)
    @JsonBackReference
    private Blog blog;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Comment() {
    }

    public Comment(String comment, Blog blog, User user) {
        this.comment = comment;
        this.blog = blog;
        this.user = user;
    }

    public Comment(String comment, Blog blog, User user, Set<User> taggedUsers) {
        this.comment = comment;
        this.taggedUsers = taggedUsers;
        this.blog = blog;
        this.user = user;
    }

    public Long getComment_id() {
        return comment_id;
    }

    public void setComment_id(Long comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    public Set<User> getTaggedUsers() {
        return taggedUsers;
    }

    public void setTaggedUsers(Set<User> taggedUsers) {
        this.taggedUsers = taggedUsers;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", comment='" + comment + '\'' +
                ", likes=" + likes +
                ", dislikes=" + dislikes +
                ", taggedUsers=" + taggedUsers +
                ", blog=" + blog.getTitle() +
                ", user=" + user.getId() +
                '}';
    }
}
