package com.digitalhouse.demo.services;


import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.entities.Seller;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.SellerRepository;
import com.digitalhouse.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SellerService {

    @Autowired
    SellerRepository sellerRepository;

    CountFollowersDTO countFollowersDTO = new CountFollowersDTO();

    public CountFollowersDTO countFollewers(Long id){
        Seller seller = sellerFindById(id);
        return creatCountFollowersdDTO(seller);
    }

    public CountFollowsDTO listSellerFollows(Long id){
        Seller seller = sellerFindById(id);
        ;
        return creatCountFollowsDTO(seller);
    }

    //Retorna a ordenado por ordem crescente
    public ListFollowersOrderDTO listFollowersAsc(Long id){

        Seller seller = sellerFindById(id);
        ListFollowersOrderDTO listFollowersAsc = new ListFollowersOrderDTO();

        List<UserDTO> list = new ArrayList<>();

        listFollowersAsc.setId(seller.getId());
        listFollowersAsc.setName(seller.getName());

        for(User u : seller.getFollowers()){
            UserDTO userDTO = new UserDTO();
            userDTO.setName(u.getName());
            userDTO.setId(u.getId());
            list.add(userDTO);
        }

        Collections.sort(list, new SortedFollowersDTO());
        for(int i=0; i<list.size(); i++){
            listFollowersAsc.getFollowers().add(list.get(i));
        }
        return listFollowersAsc;
    }
    public ListFollowersOrderDTO listFollowersDesc(Long id){

        Seller seller = sellerFindById(id);
        ListFollowersOrderDTO listFollowersAsc = new ListFollowersOrderDTO();

        List<UserDTO> list = new ArrayList<>();

        listFollowersAsc.setId(seller.getId());
        listFollowersAsc.setName(seller.getName());

        for(User u : seller.getFollowers()){
            UserDTO userDTO = new UserDTO();
            userDTO.setName(u.getName());
            userDTO.setId(u.getId());
            list.add(userDTO);
        }

        Collections.sort(list, new SortedFollowersDTO());
        for(int i=0; i<list.size(); i++){
            listFollowersAsc.getFollowers().add(list.get(i));
        }
        listFollowersAsc.setFollowers(listFollowersAsc.getFollowers().stream().sorted(Comparator.comparing(UserDTO::getName).reversed()).collect(Collectors.toList()));
        return listFollowersAsc;
    }

    public List<Seller> findByAll(){
        return sellerRepository.findAll();
    }

    public Seller  sellerFindById(Long id){
        Optional<Seller> obj = sellerRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public CountFollowersDTO creatCountFollowersdDTO(Seller seller){
        countFollowersDTO.setId(seller.getId());
        countFollowersDTO.setName(seller.getName());
        countFollowersDTO.setFollowersCount(seller.getFollowers().size());
        return countFollowersDTO;
    }

    public CountFollowsDTO creatCountFollowsDTO(Seller seller){
        CountFollowsDTO countFollowsDTO = new CountFollowsDTO();
        countFollowsDTO.setName(seller.getName());
        countFollowsDTO.setId(seller.getId());
        for(User s : seller.getFollowers()){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(s.getId());
            userDTO.setName(s.getName());
            countFollowsDTO.getFollows().add(userDTO);
        }
        return countFollowsDTO;
    }

}
