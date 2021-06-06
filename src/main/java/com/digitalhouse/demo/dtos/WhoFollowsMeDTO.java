package com.digitalhouse.demo.dtos;

import java.util.List;

public class WhoFollowsMeDTO {
    private Integer userId;
    private String userName;
    private List<UserDTO> followers;

    public WhoFollowsMeDTO() {
    }

    public WhoFollowsMeDTO(Integer userId, String userName, List<UserDTO> followers) {
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

    public List<UserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserDTO> followers) {
        this.followers = followers;
    }
}
