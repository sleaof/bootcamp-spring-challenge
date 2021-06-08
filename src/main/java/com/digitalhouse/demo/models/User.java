package com.digitalhouse.demo.models;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long userId;
    private String userName;
    private List<Long> followed;

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

    public List<Long> getFollowed() {
        return followed;
    }

    public void setFollowed(List<Long> followed) {
        this.followed = followed;
    }
}
