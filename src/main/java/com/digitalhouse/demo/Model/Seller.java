package com.digitalhouse.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Seller extends User implements Serializable {

    @ManyToMany(mappedBy = "follows",cascade = CascadeType.ALL)
    private List<User> followers = new ArrayList<>();

    @ManyToMany
    @JoinTable
    private List<Post> posts = new ArrayList<>();

    public Seller(Integer userId, String userName, Boolean seller, List<User> follows, List<User> followers, List<Post> posts) {
        super(userId, userName, seller, follows);
        this.followers = followers;
        this.posts = posts;
    }

    public Seller() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    @Override
    public Boolean getSeller() {
        return super.getSeller();
    }

    @Override
    public void setSeller(Boolean seller) {
        super.setSeller(seller);
    }

    @Override
    public String getUserName() {
        return super.getUserName();
    }

    @Override
    public void setUserName(String userName) {
        super.setUserName(userName);
    }

    @Override
    public Integer getUserId() {
        return super.getUserId();
    }

    @Override
    public void setUserId(Integer userId) {
        super.setUserId(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return getUserId() == seller.getUserId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }
}
