package com.digitalhouse.demo.DTOs;

import java.util.List;

public class BuyerDTO {
    private Integer userId;
    private String userName;
    private List<SellerDTO> follows;

    public BuyerDTO(Integer userId, String userName, List<SellerDTO> follows) {
        this.userId = userId;
        this.userName = userName;
        this.follows = follows;
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

    public List<SellerDTO> getFollows() {
        return follows;
    }

    public void setFollows(List<SellerDTO> follows) {
        this.follows = follows;
    }
}
