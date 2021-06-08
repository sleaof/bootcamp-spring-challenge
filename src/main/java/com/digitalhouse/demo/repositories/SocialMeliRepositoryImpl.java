package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.DTOs.*;
import com.digitalhouse.demo.models.Seller;
import com.digitalhouse.demo.models.User;
import com.fasterxml.jackson.databind.node.POJONode;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.GenericArrayType;
import java.util.*;

@Repository
public class SocialMeliRepositoryImpl implements SocialMeliRepository {
    private final UserRepository userRepository;
    private final SellerRepository sellerRepository;
    private final PostRepository postRepository;

    public SocialMeliRepositoryImpl(UserRepository userRepository,
                                    SellerRepository sellerRepository,
                                    PostRepository postRepository) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void follow(Long userId, Long userIdToFollow) {
        User user = userRepository.getById(userId);
        Seller seller = sellerRepository.getById(userIdToFollow);

        userRepository.follow(user, userIdToFollow);
        sellerRepository.follow(seller, userId);
    }

    @Override
    public void unfollow(Long userId, Long userIdToUnfollow) {
        User user = userRepository.getById(userId);
        Seller seller = sellerRepository.getById(userIdToUnfollow);

        userRepository.unfollow(user, userIdToUnfollow);
        sellerRepository.unfollow(seller, userId);
    }

    @Override
    public ResponseFollowersCountDTO followersCount(Long userId) {
        ResponseFollowersCountDTO followersCountDTO = new ResponseFollowersCountDTO();
        Seller seller = new Seller();
        seller = sellerRepository.getById(userId);

        followersCountDTO.setUserId(seller.getUserId());
        followersCountDTO.setUserName(seller.getUserName());
        followersCountDTO.setFollowers_count((long) seller.getFollowers().size());
        return followersCountDTO;
    }

    @Override
    public ResponseFollowersListDTO followersList(Long userId, @RequestParam(required = false) String order) {
        ResponseFollowersListDTO followersListDTO = new ResponseFollowersListDTO();
        Seller seller = new Seller();
        seller = sellerRepository.getById(userId);
        HashMap<Long, String> followers = new HashMap<>();

        for (int i = 0; i < seller.getFollowers().size(); i++) {
            User user = new User();
            user = userRepository.getById(seller.getFollowers().get(i));
            followers.put(user.getUserId(), user.getUserName());
        }

        if (order != null) {
            followers = sortByValue(followers, order);
        }

        followersListDTO.setUserId(seller.getUserId());
        followersListDTO.setUserName(seller.getUserName());
        followersListDTO.setFollowers(followers);

        return followersListDTO;
    }

    @Override
    public ResponseFollowedListDTO followedList(Long userId, @RequestParam(required = false) String order) {
        ResponseFollowedListDTO followedListDTO = new ResponseFollowedListDTO();
        User user = new User();
        user = userRepository.getById(userId);
        HashMap<Long, String> followed = new HashMap<>();

        for (int i = 0; i < user.getFollowed().size(); i++) {
            Seller seller = new Seller();
            seller = sellerRepository.getById(user.getFollowed().get(i));
            followed.put(seller.getUserId(), seller.getUserName());
        }

        if (order != null) {
            followed = sortByValue(followed, order);
        }

        followedListDTO.setUserId(user.getUserId());
        followedListDTO.setUserName(user.getUserName());
        followedListDTO.setFollowed(followed);
        return followedListDTO;
    }

    @Override
    public ResponseFollowedPostsDTO postsByFollowedUsers(Long userId) {
        ResponseFollowedPostsDTO responseFollowedPostsDTO = new ResponseFollowedPostsDTO();
        User user = new User();
        List<Long> followed = new ArrayList<>();
        List<PostDTO> postsDTO = new ArrayList<>();

        user = userRepository.getById(userId);
        followed = user.getFollowed();

        for (int i = 0; i < followed.size(); i++) {
            postsDTO.addAll(postRepository.getPostsById(followed.get(i)));
        }

        responseFollowedPostsDTO.setUserId(userId);
        responseFollowedPostsDTO.setPosts(postsDTO);

        return responseFollowedPostsDTO;
    }

    public static HashMap<Long, String> sortByValue(HashMap<Long, String> hm, String order)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Long, String> > list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        if(order.equals("name_asc")){
            Collections.sort(list, Map.Entry.comparingByValue());
        } else if (order.equals("name_desc")) {
            Collections.sort(list, Map.Entry.<Long, String>comparingByValue().reversed());
        }

        // put data from sorted list to hashmap
        HashMap<Long, String> temp = new LinkedHashMap<>();
        for (Map.Entry<Long, String> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
