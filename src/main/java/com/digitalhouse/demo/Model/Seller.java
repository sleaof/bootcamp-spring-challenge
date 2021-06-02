package com.digitalhouse.demo.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Seller extends User{


    public Seller(Integer userId, String userName, Boolean seller, List<User> followers) {
        super(userId, userName, seller, followers);
    }

    public Seller() {
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
