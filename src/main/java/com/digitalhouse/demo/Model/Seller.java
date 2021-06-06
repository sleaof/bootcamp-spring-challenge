package com.digitalhouse.demo.Model;

import com.digitalhouse.demo.DTOs.UserDTO;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Seller extends User{
    @Column
    @OneToMany(cascade=CascadeType.ALL)
    @ElementCollection(targetClass=User.class)
    private List<User> followersTotal = new ArrayList<>();


    public Seller(Integer userId, String userName, Boolean seller, List<User> followers) {
        super(userId, userName, seller, followers);
    }

    public Seller() {
    }


    public List<User> getFollowersTotal() {
        return followersTotal;
    }

    public void setFollowersTotal(List<User> followersTotal) {
        this.followersTotal = followersTotal;
    }

    @Override
    public List<User> getFollowers() {
        return super.getFollowers();
    }

    @Override
    public void setFollowers(List<User> followers) {
        super.setFollowers(followers);
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
