package com.digitalhouse.demo.services;


import com.digitalhouse.demo.dtos.ContFollowsDTO;
import com.digitalhouse.demo.dtos.CountFollowersDTO;
import com.digitalhouse.demo.dtos.UserDTO;
import com.digitalhouse.demo.entities.Seller;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.SellerRepository;
import com.digitalhouse.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    CountFollowersDTO countFollowersDTO = new CountFollowersDTO();

    public CountFollowersDTO countFollewers(Long id){
        Seller seller = sellerFindById(id);
        countFollowersDTO.setId(seller.getId());
        countFollowersDTO.setName(seller.getName());
        countFollowersDTO.setFollowersCount(seller.getFollowers().size());
        return countFollowersDTO;
    }

    public ContFollowsDTO listSellerFollows(Long id){
        ContFollowsDTO contFollowsDTO = new ContFollowsDTO();
        Seller seller = sellerFindById(id);
        contFollowsDTO.setName(seller.getName());
        contFollowsDTO.setId(seller.getId());
        for(User s : seller.getFollowers()){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(s.getId());
            userDTO.setName(s.getName());
            contFollowsDTO.getFollows().add(userDTO);
        }
        return contFollowsDTO;
    }

    public List<Seller> findByAll(){
        return sellerRepository.findAll();
    }

    public Seller  sellerFindById(Long id){
        Optional<Seller> obj = sellerRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
