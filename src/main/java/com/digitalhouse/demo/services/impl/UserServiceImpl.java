package com.digitalhouse.demo.services.impl;

import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.entities.*;
import com.digitalhouse.demo.repositories.*;
import com.digitalhouse.demo.services.UserService;
import com.digitalhouse.demo.validations.Validation;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private SellerRepository sellerRepository;
    private Validation validation;

    public UserServiceImpl(UserRepository userRepository, SellerRepository sellerRepository, Validation validation) {
        this.userRepository = userRepository;
        this.sellerRepository = sellerRepository;
        this.validation = validation;
    }

    @Override
    public Boolean follow(Integer userId, Integer userIdToFollow) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Seller> userToFollow = sellerRepository.findById(userIdToFollow);

        if (!validation.validateRepeatFollower(user, userToFollow)) {
            user.get().getFollowed().add((Seller) userToFollow.get());
            userRepository.save(user.get());
            return true;
        }
        return false;
    }

    @Override
    public NumberOfUsersFollowASellerDTO countFollowers(Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);
        return new NumberOfUsersFollowASellerDTO(seller.get().getUserId(), seller.get().getUserName(), seller.get().getFollowers().size());
    }

    @Override
    public ListOfUsersFollowASellerDTO listOfUsersFollowASeller(Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);
        List<UserDTO> userDTO = new ArrayList<>();

        for (User user : seller.get().getFollowers()) {
            userDTO.add(new UserDTO(user.getUserId(), user.getUserName()));
        }

        return new ListOfUsersFollowASellerDTO(seller.get().getUserId(), seller.get().getUserName(), userDTO);
    }

    @Override
    public ListOfSellersFollowAUserDTO listOfSellersFollowAUser(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        List<SellerDTO> sellerDTO = new ArrayList<>();

        for (Seller seller : user.get().getFollowed()) {
            sellerDTO.add(new SellerDTO(seller.getUserId(), seller.getUserName()));
        }

        return new ListOfSellersFollowAUserDTO(user.get().getUserId(), user.get().getUserName(), sellerDTO);
    }

    @Override
    public Boolean unfollow(Integer userId, Integer userIdToUnfollow) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Seller> seller = sellerRepository.findById(userIdToUnfollow);
        Integer i = 0;

        List<Seller> list = user.get().getFollowed();

        if (validation.validateRepeatFollower(user, seller)) {
            while (i < list.size()) {
                if (list.get(i).getUserId().equals(userIdToUnfollow)) {
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
    public SortByNameDTO sortFollowersByNameAsc(Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);

        SortByNameDTO sortByNameDTO = new SortByNameDTO();
        sortByNameDTO.setUsers(seller.get().getFollowers().stream()
                .sorted(Comparator.comparing(User::getUserName))
                .collect(Collectors.toList()));

        return sortByNameDTO;
    }

    @Override
    public SortByNameDTO sortFollowersByNameDesc(Integer userId) {
        Optional<Seller> seller = sellerRepository.findById(userId);

        SortByNameDTO sortByNameDTO = new SortByNameDTO();
        sortByNameDTO.setUsers(seller.get().getFollowers().stream()
                .sorted(Comparator.comparing(User::getUserName).reversed())
                .collect(Collectors.toList()));
        return sortByNameDTO;
    }

    @Override
    public SortByNameDTO sortFollowedByNameAsc(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        SortByNameDTO sortByNameDTO = new SortByNameDTO();
        sortByNameDTO.setUsers(user.get().getFollowed().stream()
                .sorted(Comparator.comparing(Seller::getUserName))
                .collect(Collectors.toList()));
        return sortByNameDTO;
    }

    @Override
    public SortByNameDTO sortFollowedByNameDesc(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        SortByNameDTO sortByNameDTO = new SortByNameDTO();
        sortByNameDTO.setUsers(user.get().getFollowed().stream()
                .sorted(Comparator.comparing(Seller::getUserName).reversed())
                .collect(Collectors.toList()));
        return sortByNameDTO;
    }
}
