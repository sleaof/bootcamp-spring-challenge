package com.digitalhouse.demo.DTOs;

import com.digitalhouse.demo.Model.Post;

import java.util.List;

public class SellerProductsDTO {
    private Integer userId;
    private String userName;
    private List<Post> posts;

    public SellerProductsDTO(Integer userId, String userName, List<Post> posts) {
        this.userId = userId;
        this.userName = userName;
        this.posts = posts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
