package com.digitalhouse.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    @Column
    private String userName;
    @Column
    private Boolean seller;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable
    private List<Seller> follows = new ArrayList<>();


    public User(Integer userId, String userName, Boolean seller, List<User> follows) {
        this.userId = userId;
        this.userName = userName;
        this.seller = seller;
        follows = follows;
    }

    public User(){

    }


    public User(Integer userId, String userName, Boolean seller) {
    }

    public List<Seller> getFollows() {
        return follows;
    }

    public void setFollows(List<Seller> follows) {
        this.follows = follows;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", seller=" + seller +
                ", followers=" + follows +
                '}';
    }
}
