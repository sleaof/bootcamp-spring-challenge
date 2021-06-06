package com.digitalhouse.demo.DTOs;

import com.digitalhouse.demo.Model.User;

import javax.persistence.ManyToMany;

public class UserDTO {
    private Integer userId;
    private String userName;
    private Boolean seller;

    public UserDTO(Integer userId, String userName, Boolean seller) {
        this.userId = userId;
        this.userName = userName;
        this.seller = seller;
    }

    public UserDTO() {
    }

    public UserDTO(User user){
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.seller = user.getSeller();
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

    public Boolean getSeller() {
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
    }
}
