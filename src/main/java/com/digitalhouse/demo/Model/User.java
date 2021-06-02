package com.digitalhouse.demo.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Entity
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column
    private String userName;
    @Column
    private Boolean seller;
    @ManyToMany
    @JoinTable
    private List<User> followers = new ArrayList<>();

    public User(Integer userId, String userName, Boolean seller, List<User> followers) {
        this.userId = userId;
        this.userName = userName;
        this.seller = seller;
        followers = followers;
    }
    public User(){

    }

    public List<User> getFollowers() {
        return followers;
    }
    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public Boolean getSeller() {
        return seller;
    }

    public void setSeller(Boolean seller) {
        this.seller = seller;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserId().equals(user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
