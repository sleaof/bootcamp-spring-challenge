package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.CountFollowersDTO;
import com.digitalhouse.demo.dtos.WhoFollowsMeDTO;
import com.digitalhouse.demo.dtos.WhoIsFollowingMeDTO;
import com.digitalhouse.demo.entities.Seller;
import com.digitalhouse.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Boolean validateById(Integer userId);
    public Boolean follow(Integer userId, Integer userIdToFollow);
    public CountFollowersDTO countFollowers(Integer userId);
    public WhoFollowsMeDTO listOfUsersFollowASeller(Optional<Seller> seller);
    public WhoIsFollowingMeDTO listOfSellersFollowAUser(Optional<User> user);
    public Boolean unfollow(Integer userId, Integer userIdToUnfollow);
    public List<User> sortFollowersByNameAsc(Integer userId);
    public List<User> sortFollowersByNameDesc(Integer userId);
    public List<User> sortFollowedByNameAsc(Integer userId);
    public List<User> sortFollowedByNameDesc(Integer userId);
}
