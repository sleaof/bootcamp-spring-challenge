package com.digitalhouse.demo.DTOs;

import java.util.List;

public class SellerPostDTO {
    private Integer userId;
    private String userName;
    private List<SellerProductsDTO> posts;

    public SellerPostDTO(Integer userId, String userName, List<SellerProductsDTO> posts) {
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

    public List<SellerProductsDTO> getFollowed() {
        return posts;
    }

    public void setFollowed(List<SellerProductsDTO> followed) {
        this.posts = posts;
    }
}

