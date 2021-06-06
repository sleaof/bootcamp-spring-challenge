package com.digitalhouse.demo.dtos;

import java.util.List;

public class WhoIsFollowingMeDTO {
        private Integer userId;
        private String userName;
        private List<SellerDTO> followed;

    public WhoIsFollowingMeDTO() {
    }

    public WhoIsFollowingMeDTO(Integer userId, String userName, List<SellerDTO> followed) {
        this.userId = userId;
        this.userName = userName;
        this.followed = followed;
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

    public List<SellerDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<SellerDTO> followed) {
        this.followed = followed;
    }
}
