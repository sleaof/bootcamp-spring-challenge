package com.digitalhouse.demo.services;

import com.digitalhouse.demo.DTOs.*;
import com.digitalhouse.demo.models.Seller;
import com.digitalhouse.demo.models.User;
import com.digitalhouse.demo.repositories.PostRepository;
import com.digitalhouse.demo.repositories.SellerRepository;
import com.digitalhouse.demo.repositories.SocialMeliRepository;
import com.digitalhouse.demo.repositories.UserRepository;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class SocialMeliService {
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final SocialMeliRepository socialMeliRepository;
    private final PostRepository postRepository;

    public SocialMeliService(UserRepository userRepository,
                             SellerRepository sellerRepository,
                             SocialMeliRepository socialMeliRepository,
                             PostRepository postRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.socialMeliRepository = socialMeliRepository;
        this.postRepository = postRepository;
    }

    public void follow(Long userId, Long userIdToFollow) {
        socialMeliRepository.follow(userId, userIdToFollow);
    }

    public ResponseFollowersCountDTO followersCount(Long userId) {
        return socialMeliRepository.followersCount(userId);
    }

    public ResponseFollowersListDTO followersList(Long userId, @RequestParam(required = false) String order) {
        return socialMeliRepository.followersList(userId, order);
    }

    public ResponseFollowedListDTO followedList(Long userId, @RequestParam(required = false) String order) {
        return socialMeliRepository.followedList(userId, order);
    }

    public void createPost(PostDTO postDTO) {
        postRepository.createPost(postDTO);
    }

    public ResponseFollowedPostsDTO postsByFollowedUsers(Long userId) {
        return socialMeliRepository.postsByFollowedUsers(userId);
    }

    public void unfollow(Long userId, Long userIdToUnfollow) {
        socialMeliRepository.unfollow(userId, userIdToUnfollow);
    }

    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public Seller getSellerById(Long id) {
        return sellerRepository.getById(id);
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.getAll();
    }

    public List<PostDTO> getAllPosts() {
        return postRepository.getAll();
    }
}
