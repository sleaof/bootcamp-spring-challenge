package com.digitalhouse.demo.dtos;

import com.digitalhouse.demo.entities.Post;

import java.util.List;

public class PostsBySellersDTO {
    private Integer userId;
    private List<Post> posts;

    public PostsBySellersDTO() {
    }

    public PostsBySellersDTO(Integer userId, List<Post> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
