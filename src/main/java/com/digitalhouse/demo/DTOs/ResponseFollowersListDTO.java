package com.digitalhouse.demo.DTOs;

import java.util.HashMap;

public class ResponseFollowersListDTO {
    private Long userId;
    private String userName;
    private HashMap<Long, String> followers = new HashMap<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public HashMap<Long, String> getFollowers() {
        return followers;
    }

    public void setFollowers(HashMap<Long, String> followers) {
        this.followers = followers;
    }
}
