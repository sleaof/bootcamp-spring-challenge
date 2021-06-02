package com.digitalhouse.demo.Model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;
@Entity
public class Buyer extends User{

    public Buyer(Integer userId, String userName, Boolean seller, List<User> followers) {
        super(userId, userName, seller, followers);
    }

    public Buyer() {
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


}
