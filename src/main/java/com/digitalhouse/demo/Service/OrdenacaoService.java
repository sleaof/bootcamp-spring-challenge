package com.digitalhouse.demo.Service;

import com.digitalhouse.demo.DTOs.FollowedDTO;
import com.digitalhouse.demo.DTOs.SellerDTO;
import com.digitalhouse.demo.Model.User;
import com.digitalhouse.demo.Repository.UserRepository;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
@Service
public class OrdenacaoService {

    @Autowired
    private UserRepository user;

    public FollowedDTO sortFollowersByNameAsc(Integer userId) {

        FollowedDTO followedDTO = null;
        SellerDTO sellerDTO = null;
        List<SellerDTO> list = new ArrayList<>();

        if (user.findById(userId).get().getSeller().equals(false)) {
            int size = user.findById(userId).get().getFollows().size();

            for (int i = 0; i < size; i++) {
                User user1 = user.findById(userId).get().getFollows().get(i);
                sellerDTO = new SellerDTO(user1.getUserId(), user1.getUserName());
                list.add(sellerDTO);
                }
            Collections.sort(list, Comparator.comparing(SellerDTO::getUserName));
            User userDTO = user.findById(userId).get();
            followedDTO = new FollowedDTO(userDTO.getUserId(), userDTO.getUserName(), list);

        } else {
            System.out.println("usuario é vendendor");
        }



        return followedDTO;

    }

    public FollowedDTO sortFollowersByNameDesc(Integer userId) {

        FollowedDTO followedDTO = null;
        SellerDTO sellerDTO = null;
        List<SellerDTO> list = new ArrayList<>();

        if (user.findById(userId).get().getSeller().equals(false)) {
            int size = user.findById(userId).get().getFollows().size();

            for (int i = 0; i < size; i++) {
                User user1 = user.findById(userId).get().getFollows().get(i);
                sellerDTO = new SellerDTO(user1.getUserId(), user1.getUserName());
                list.add(sellerDTO);
            }
            Collections.sort(list, Comparator.comparing(SellerDTO::getUserName).reversed());
            User userDTO = user.findById(userId).get();
            followedDTO = new FollowedDTO(userDTO.getUserId(), userDTO.getUserName(), list);

        } else {
            System.out.println("usuario é vendendor");
        }

        return followedDTO;
    }
}
