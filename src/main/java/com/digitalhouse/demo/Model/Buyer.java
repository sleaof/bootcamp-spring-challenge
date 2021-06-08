package com.digitalhouse.demo.Model;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;
@Entity
public class Buyer extends User implements Serializable {
//
//    public Buyer(Integer userId, String userName, Boolean seller, List<User> followers) {
//        super(userId, userName, seller, followers);
//    }

    public Buyer(Integer userId, String userName, Boolean seller) {
        super(userId, userName, seller);
    }

    public Buyer() {
    }

    @Override
    public List<Seller> getFollows() {
        return super.getFollows();
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
