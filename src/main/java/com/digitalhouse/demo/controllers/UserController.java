package com.digitalhouse.demo.controllers;

import com.digitalhouse.demo.dtos.CountFollowersDTO;
import com.digitalhouse.demo.dtos.WhoFollowsMeDTO;
import com.digitalhouse.demo.dtos.WhoIsFollowingMeDTO;
import com.digitalhouse.demo.entities.Seller;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.SellerRepository;
import com.digitalhouse.demo.repositories.UserRepository;
import com.digitalhouse.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{userId}/follow/{userIdToFollow}")
    public ResponseEntity followById(@PathVariable Integer userId, @PathVariable Integer userIdToFollow) {
        if(!service.validateById(userId) || !service.validateById(userIdToFollow))
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        if (service.follow(userId, userIdToFollow) && !userId.equals(userIdToFollow))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userId}/followers/count")
    public ResponseEntity<CountFollowersDTO> numberOfUsersFollowASeller(@PathVariable Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);

        if(seller.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(!service.validateById(userId))
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(service.countFollowers(userId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followers/list")
    public ResponseEntity<WhoFollowsMeDTO> listOfUsersFollowASeller(@PathVariable Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);

        return new ResponseEntity<WhoFollowsMeDTO>(service.listOfUsersFollowASeller(seller), HttpStatus.OK);
    }

    @GetMapping("/{userId}/followed/list")
    public ResponseEntity<WhoIsFollowingMeDTO> listOfSellersFollowAUser(@PathVariable Integer userId) {
        Optional<User> user = userRepository.findById(userId);

        return new ResponseEntity<WhoIsFollowingMeDTO>(service.listOfSellersFollowAUser(user), HttpStatus.OK);
    }

    @PostMapping("/{userId}/unfollow/{userIdToUnfollow}")
    public ResponseEntity unfollowASeller(@PathVariable Integer userId, @PathVariable Integer userIdToUnfollow) {
        if (service.unfollow(userId, userIdToUnfollow))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{userId}/followers/orderedList")
    public List<User> sortFollowersByName(@PathVariable Integer userId, @RequestParam(value = "order") String order) {
        if(order.equalsIgnoreCase("name_asc")) {
            return service.sortFollowersByNameAsc(userId);
        } else {
            return service.sortFollowersByNameDesc(userId);
        }
    }

    @GetMapping("/{userId}/followed/orderedList")
    public List<User> sortFollowedByName(@PathVariable Integer userId, @RequestParam(value = "order") String order) {
        if(order.equalsIgnoreCase("name_asc")) {
            return service.sortFollowedByNameAsc(userId);
        } else {
            return service.sortFollowedByNameDesc(userId);
        }
    }
}
