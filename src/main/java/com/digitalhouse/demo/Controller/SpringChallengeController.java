package com.digitalhouse.demo.Controller;

import com.digitalhouse.demo.DTOs.*;
import com.digitalhouse.demo.Model.*;
import com.digitalhouse.demo.Repository.*;
import com.digitalhouse.demo.Service.OrdenacaoService;
import com.digitalhouse.demo.Service.ProductsServices;
import com.digitalhouse.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping
public class SpringChallengeController {
    @Autowired
    public OrdenacaoService ordenacaoService;
    @Autowired
    private ProductsServices productsServices;
    @Autowired
    private UserService userService;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private DetailRepository detailRepository;

    @PostMapping("/seller")
    public Seller createUsuario(@RequestBody Seller seller) {
        return sellerRepository.save(seller);
    }

    @PostMapping("/user")
    public User createUsuario(@RequestBody Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    @GetMapping("/users/{userId}/followers/count")
    public UserCountDTO geralFollow(@PathVariable Integer userId) {

        return userService.count(userId);
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public BuyerDTO followTosellerById(@PathVariable Integer userId, @PathVariable int userIdToFollow) {
        return userService.followed(userId, userIdToFollow);

    }

    @GetMapping("/users/{userId}/followers/list")
    public FollowersDTO ListFollowers(@PathVariable Integer userId) {
        return userService.followersTotal(userId);

    }

    @GetMapping("/users/{userId}/followed/list")
    public FollowedDTO ListFollowed(@PathVariable Integer userId) {
        return userService.followedTotal(userId);

    }

    @PostMapping("/seller/{sellerId}/post/{postId}/newproduct/{productId}")
    public Seller createPost(@PathVariable Integer sellerId, @RequestBody Post post, @PathVariable Integer postId, @PathVariable Integer productId) {
        postRepository.save(post);
        return productsServices.post(postId, productId, sellerId);
    }

    @PostMapping("/products")
    public Detail createDetail(@RequestBody Detail detail) {
        return detailRepository.save(detail);
    }

    @GetMapping("/products/followed/{userId}/list")
    public SellerPostDTO listPost(@PathVariable Integer userId) {
        return productsServices.listPost(userId);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnFollow}")
    public String unfollow(@PathVariable Integer userId, @PathVariable Integer userIdToUnFollow) {
        return userService.unFollow(userId, userIdToUnFollow);

    }
    @GetMapping("/{userId}/followers/orderedList")
    public FollowedDTO sortFollowersByName(@PathVariable Integer userId, @RequestParam(value = "order") String order) {
        if(order.equalsIgnoreCase("name_asc")) {
            return ordenacaoService.sortFollowersByNameAsc(userId);
        } else {
            return ordenacaoService.sortFollowersByNameDesc(userId);
        }
    }

}
