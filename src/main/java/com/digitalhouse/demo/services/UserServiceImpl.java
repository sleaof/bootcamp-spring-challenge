package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.entities.Seller;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.SellerRepository;
import com.digitalhouse.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SellerRepository sellerRepository;


    @Override
    public Boolean validateById(Integer userId) {
        return userRepository.findById(userId).isPresent();
    }

    @Override
    public Boolean follow(Integer userId, Integer userIdToFollow) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Seller> seller = sellerRepository.findById(userIdToFollow);

        if (!repeatSeller(user, seller) && seller.get().getSeller() == Boolean.TRUE) {
            user.get().getFollowed().add(seller.get());
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    public Boolean repeatSeller(Optional<User> user, Optional<Seller> userToFollow) {
        for (Seller seller : user.get().getFollowed()) {
            if (seller.getUserId().equals(userToFollow.get().getUserId()))
                return true;
        }
        return false;
    }

    @Override
    public CountFollowersDTO countFollowers(Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);
        CountFollowersDTO countFollowersDTO = new CountFollowersDTO();

        countFollowersDTO.setUserId(seller.get().getUserId());
        countFollowersDTO.setUserName(seller.get().getUserName());
        countFollowersDTO.setFollowers_count(seller.get().getFollowers().size());

        return countFollowersDTO;
    }

    @Override
    public WhoFollowsMeDTO listOfUsersFollowASeller(Optional<Seller> seller) {
        WhoFollowsMeDTO whoFollowsMeDTO = new WhoFollowsMeDTO();
        whoFollowsMeDTO.setUserId(seller.get().getUserId());
        whoFollowsMeDTO.setUserName(seller.get().getUserName());
        
        List<UserDTO> userDTO = new ArrayList<>();

        for(User user : seller.get().getFollowers()) {
            userDTO.add(new UserDTO(user.getUserId(), user.getUserName()));
        }

        whoFollowsMeDTO.setFollowers(userDTO);

        return whoFollowsMeDTO;
    }

    @Override
    public WhoIsFollowingMeDTO listOfSellersFollowAUser(Optional<User> user) {
        WhoIsFollowingMeDTO whoIsFollowingMeDTO = new WhoIsFollowingMeDTO();
        whoIsFollowingMeDTO.setUserId(user.get().getUserId());
        whoIsFollowingMeDTO.setUserName(user.get().getUserName());

        List<SellerDTO> sellerDTO = new ArrayList<>();

        for(Seller seller : user.get().getFollowed()) {
            sellerDTO.add(new SellerDTO(seller.getUserId(), seller.getUserName()));
        }

        whoIsFollowingMeDTO.setFollowed(sellerDTO);

        return whoIsFollowingMeDTO;
    }

    @Override
    public Boolean unfollow(Integer userId, Integer userIdToUnfollow) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Seller> seller = sellerRepository.findById(userIdToUnfollow);
        Integer i = 0;

        List <Seller> list = user.get().getFollowed();

        if (repeatSeller(user, seller)) {
            while(i < list.size()) {
                if(list.get(i).getUserId().equals(userIdToUnfollow)) {
                    list.remove(list.get(i));
                    user.get().setFollowed(list);
                    userRepository.save(user.get());
                }
                i++;
            }
            return true;
        }
        return false;
    }

    @Override
    public List<User> sortFollowersByNameAsc(Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);
        return seller.get().getFollowers().stream()
                .sorted(Comparator.comparing(User::getUserName))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> sortFollowersByNameDesc(Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);
        return seller.get().getFollowers().stream()
                .sorted(Comparator.comparing(User::getUserName).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<User> sortFollowedByNameAsc(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.get().getFollowed().stream()
                .sorted(Comparator.comparing(Seller::getUserName))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> sortFollowedByNameDesc(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.get().getFollowed().stream()
                .sorted(Comparator.comparing(Seller::getUserName).reversed())
                .collect(Collectors.toList());
    }
}
