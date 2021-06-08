package com.digitalhouse.demo.cotrollers;

import com.digitalhouse.demo.DTOs.*;
import com.digitalhouse.demo.models.Seller;
import com.digitalhouse.demo.models.User;
import com.digitalhouse.demo.services.SocialMeliService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class SocialMeliController {
    private final SocialMeliService socialMeliService;

    public SocialMeliController(SocialMeliService socialMeliService) {
        this.socialMeliService = socialMeliService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return socialMeliService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return socialMeliService.getUserById(id);
    }

    @GetMapping("/seller")
    public List<Seller> getAllSellers() {
        return socialMeliService.getAllSellers();
    }

    @GetMapping("/seller/{id}")
    public Seller getSellerById(@PathVariable Long id) {
        return socialMeliService.getSellerById(id);
    }

    @GetMapping("/post")
    public List<PostDTO> getAllPosts() {
        return socialMeliService.getAllPosts();
    }

    @PostMapping("users/{userId}/follow/{userIdToFollow}")
    public void follow(@PathVariable Long userId, @PathVariable Long userIdToFollow) {
        socialMeliService.follow(userId, userIdToFollow);
    }

    @GetMapping("users/{userId}/followers/count")
    public ResponseFollowersCountDTO followersCount(@PathVariable Long userId) {
        return socialMeliService.followersCount(userId);
    }

    @GetMapping("users/{userId}/followers/list")
    public ResponseFollowersListDTO followersList(@PathVariable Long userId, @RequestParam(required = false) String order) {
        if(order != null) {
            return socialMeliService.followersList(userId, order);
        } else {
            return socialMeliService.followersList(userId, null);
        }
    }

    @GetMapping("users/{userId}/followed/list")
    public ResponseFollowedListDTO followedList(@PathVariable Long userId, @RequestParam(required = false) String order) {
        if(order != null) {
            return socialMeliService.followedList(userId, order);
        } else {
            return socialMeliService.followedList(userId, null);
        }
    }

    @PostMapping("products/newpost")
    public void newPost(@RequestBody PostDTO postDTO) {
        socialMeliService.createPost(postDTO);
    }

    @GetMapping("products/followed/{userId}/list")
    public ResponseFollowedPostsDTO postsByFollowedUsers(@PathVariable Long userId, @RequestParam(required = false) String order) {
        if(order != null) {
            return socialMeliService.postsByFollowedUsers(userId);
        } else {
            return socialMeliService.postsByFollowedUsers(userId);
        }
    }

    @PostMapping("users/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollow(@PathVariable Long userId, @PathVariable Long userIdToUnfollow) {
        socialMeliService.unfollow(userId, userIdToUnfollow);
    }

}
