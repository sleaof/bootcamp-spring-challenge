package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.numberOfUsersFollowASellerDTO;
import com.digitalhouse.demo.dtos.listOfUsersFollowASellerDTO;
import com.digitalhouse.demo.dtos.listOfSellersFollowAUserDTO;
import com.digitalhouse.demo.dtos.SortByNameDTO;

public interface UserService {
    public Boolean follow(Integer userId, Integer userIdToFollow);
    public numberOfUsersFollowASellerDTO countFollowers(Integer userId);
    public listOfUsersFollowASellerDTO listOfUsersFollowASeller(Integer userId);
    public listOfSellersFollowAUserDTO listOfSellersFollowAUser(Integer userId);
    public Boolean unfollow(Integer userId, Integer userIdToUnfollow);
    public SortByNameDTO sortFollowersByNameAsc(Integer userId);
    public SortByNameDTO sortFollowersByNameDesc(Integer userId);
    public SortByNameDTO sortFollowedByNameAsc(Integer userId);
    public SortByNameDTO sortFollowedByNameDesc(Integer userId);
}
