package com.digitalhouse.demo.dtos;

public class numberOfUsersFollowASellerDTO {
    private Integer userId;
    private String userName;
    private Integer followers_count;

    public numberOfUsersFollowASellerDTO() {
    }

    public numberOfUsersFollowASellerDTO(Integer userId, String userName, Integer followers_count) {
        this.userId = userId;
        this.userName = userName;
        this.followers_count = followers_count;
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

    public Integer getFollowers_count() {
        return followers_count;
    }

    public void setFollowers_count(Integer followers_count) {
        this.followers_count = followers_count;
    }
}
