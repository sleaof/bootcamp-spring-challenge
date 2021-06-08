package com.digitalhouse.demo.DTOs;

import java.util.HashMap;

public class ResponseFollowedListDTO {
    private Long userId;
    private String userName;
    private HashMap<Long, String> followed = new HashMap<>();

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

    public HashMap<Long, String> getFollowed() {
        return followed;
    }

    public void setFollowed(HashMap<Long, String> followed) {
        this.followed = followed;
    }
}
