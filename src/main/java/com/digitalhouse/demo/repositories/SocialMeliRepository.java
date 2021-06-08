package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.DTOs.ResponseFollowedListDTO;
import com.digitalhouse.demo.DTOs.ResponseFollowedPostsDTO;
import com.digitalhouse.demo.DTOs.ResponseFollowersCountDTO;
import com.digitalhouse.demo.DTOs.ResponseFollowersListDTO;
import com.digitalhouse.demo.models.Seller;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestParam;

public interface SocialMeliRepository {
    void follow(Long userId, Long userIdToFollow);
    void unfollow(Long userId, Long userIdToUnfollow);
    ResponseFollowersCountDTO followersCount(Long userId);
    ResponseFollowersListDTO followersList(Long userId, @RequestParam(required = false) String order);
    ResponseFollowedListDTO followedList(Long userId, @RequestParam(required = false) String order);
    ResponseFollowedPostsDTO postsByFollowedUsers(Long userId);
}
