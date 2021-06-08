package com.digitalhouse.demo.DTOs;

import com.digitalhouse.demo.Model.User;

import java.util.List;

public class FollowersDTO {
    private Integer userId;
    private String userName;
    private List<SellerDTO> followers;

    public FollowersDTO(Integer userId, String userName, List<SellerDTO> followers) {
        this.userId = userId;
        this.userName = userName;
        this.followers = followers;
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

    public List<SellerDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<SellerDTO> followers) {
        this.followers = followers;
    }
}
