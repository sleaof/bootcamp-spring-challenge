package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.NumberOfUsersFollowASellerDTO;
import com.digitalhouse.demo.dtos.ListOfUsersFollowASellerDTO;
import com.digitalhouse.demo.dtos.ListOfSellersFollowAUserDTO;
import com.digitalhouse.demo.dtos.SortByNameDTO;

public interface UserService {
    public Boolean follow(Integer userId, Integer userIdToFollow);
    public NumberOfUsersFollowASellerDTO countFollowers(Integer userId);
    public ListOfUsersFollowASellerDTO listOfUsersFollowASeller(Integer userId);
    public ListOfSellersFollowAUserDTO listOfSellersFollowAUser(Integer userId);
    public Boolean unfollow(Integer userId, Integer userIdToUnfollow);
    public SortByNameDTO sortFollowersByNameAsc(Integer userId);
    public SortByNameDTO sortFollowersByNameDesc(Integer userId);
    public SortByNameDTO sortFollowedByNameAsc(Integer userId);
    public SortByNameDTO sortFollowedByNameDesc(Integer userId);
}
