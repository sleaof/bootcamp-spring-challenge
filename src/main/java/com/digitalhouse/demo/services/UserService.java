package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.CountUserFollowsDTO;
import com.digitalhouse.demo.dtos.SellerDTO;
import com.digitalhouse.demo.entities.Seller;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.SellerRepository;
import com.digitalhouse.demo.repositories.UserRepository;
import com.digitalhouse.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    SellerService sellerService;


    public User addUser(User user) {
        Seller seller = new Seller();
        if (user.isSeller()) {
            seller.setId(user.getId());
            seller.setName(user.getName());
            seller.setSeller(user.isSeller());
            return sellerRepository.save(seller);
        }
        return userRepository.save(user);

    }


    public void followNew(Long userId, Long userIdToFollow) {
        User user = userFindById(userId);
        Seller seller = sellerService.sellerFindById(userIdToFollow);
        //Adiciona o Seguidor na Lista de Followers
        seller.getFollowers().add(user);
        sellerRepository.save(seller);
        //Adiciona o Vendedor na Lista de Followed
        user.getFollowed().add(seller);
        userRepository.save(user);
    }

    public CountUserFollowsDTO listUserFollowed (Long id){
        CountUserFollowsDTO countUserFollowsDTO = new CountUserFollowsDTO();
        User user  = userFindById(id);
        countUserFollowsDTO.setId(user.getId());
        countUserFollowsDTO.setName(user.getName());
        for(User s : user.getFollowed()){
            SellerDTO sellerDTO = new SellerDTO();
            sellerDTO.setId(s.getId());
            sellerDTO.setName(s.getName());
            countUserFollowsDTO.getFollowed().add(sellerDTO);
        }
        return countUserFollowsDTO;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User userFindById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }


}
