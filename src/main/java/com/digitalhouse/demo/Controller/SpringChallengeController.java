package com.digitalhouse.demo.Controller;

import com.digitalhouse.demo.DTOs.BuyerDTO;
import com.digitalhouse.demo.DTOs.FollowedDTO;
import com.digitalhouse.demo.DTOs.FollowersDTO;
import com.digitalhouse.demo.DTOs.UserCountDTO;
import com.digitalhouse.demo.Model.Buyer;
import com.digitalhouse.demo.Model.Seller;
import com.digitalhouse.demo.Model.User;
import com.digitalhouse.demo.Repository.BuyerRepository;
import com.digitalhouse.demo.Repository.SellerRepository;
import com.digitalhouse.demo.Repository.UserRepository;
import com.digitalhouse.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class SpringChallengeController {

    @Autowired
    private UserService userService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    //@Autowired
    //private FollowersTotalRepository followersTotalRepository;

    @PostMapping("/seller")
    public Seller createUsuario(@RequestBody Seller seller){
        return sellerRepository.save(seller);
    }

    @PostMapping("/user")
    public User createUsuario(@RequestBody Buyer buyer){
        return buyerRepository.save(buyer);
    }

    @GetMapping("/users/{userId}/followers/count")
    public UserCountDTO geralFollow(@PathVariable Integer userId){

        return userService.count(userId);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public BuyerDTO followTosellerById(@PathVariable Integer userId, @PathVariable int userIdToFollow){
        return userService.followed(userId,userIdToFollow);

    }
    @GetMapping("/users/{userId}/followers/list")
    public FollowersDTO ListFollowers(@PathVariable Integer userId){
        return userService.followersTotal(userId);

    }
    @GetMapping("/users/{userId}/followed/list")
    public FollowedDTO ListFollowed(@PathVariable Integer userId){
        return userService.followedTotal(userId);

    }
}
