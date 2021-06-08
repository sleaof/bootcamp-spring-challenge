package com.digitalhouse.demo.Service;

import com.digitalhouse.demo.DTOs.*;
import com.digitalhouse.demo.Model.Seller;
import com.digitalhouse.demo.Model.User;
import com.digitalhouse.demo.Repository.BuyerRepository;
import com.digitalhouse.demo.Repository.SellerRepository;
import com.digitalhouse.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private BuyerRepository buyerList;

    @Autowired
    private UserRepository user;

    // @Autowired
    //  private FollowersTotalRepository followersTotalRepository;


    @Autowired
    private SellerRepository sellerRepository;


    // Pega o usuario por Id e vendedor
    public BuyerDTO followed(Integer userId, int userIdToFollow) {

        Boolean sellerUser = user.findById(userId).get().getSeller();
        Boolean buyer = user.findById(userId).get().getSeller();
        Boolean seller = user.findById(userIdToFollow).get().getSeller();

        User us1 = user.findById(userIdToFollow).get();
        User us2 = user.findById(userId).get();

        SellerDTO s1 = new SellerDTO(us1.getUserId(), us1.getUserName());
        List<SellerDTO> l1 = new ArrayList<>();
        l1.add(s1);
        BuyerDTO b1 = new BuyerDTO(us2.getUserId(), us2.getUserName(), l1);

        if (buyer.equals(false) && seller.equals(true) || seller.equals(true) && sellerUser.equals(true)) {

            User user1 = user.findById(userId).get();
            user1.getFollows().add(sellerRepository.findById(userIdToFollow).get());
            user.save(user1);
            System.out.println();

        } else {
            System.out.println("nao foi possivel");
        }

        return b1;
    }

    public UserCountDTO count(Integer userId) {
        UserCountDTO userCountDTO = null;

        if (user.findById(userId).get().getSeller().equals(true)) {
            long sum = sellerRepository.findById(userId).get().getFollowers().stream().count();
            User userDTO = user.findById(userId).get();
            userCountDTO = new UserCountDTO(userDTO.getUserId(), userDTO.getUserName(), sum);

        } else {
            System.out.println("usuario não é vendendor");
        }

        return userCountDTO;
    }

    public FollowersDTO followersTotal(Integer userId){

        FollowersDTO followersDTO = null;
        SellerDTO sellerDTO = null;
        List<SellerDTO> list = new ArrayList<>();
        if (user.findById(userId).get().getSeller().equals(true)) {
            int size = sellerRepository.findById(userId).get().getFollowers().size();

            for (int i = 0; i < size; i++ ) {
                User user1 = sellerRepository.findById(userId).get().getFollowers().get(i);
               sellerDTO = new SellerDTO(user1.getUserId(), user1.getUserName());
                list.add(sellerDTO);
                User userDTO = user.findById(userId).get();
                followersDTO = new FollowersDTO(userDTO.getUserId(), userDTO.getUserName(), list);
            }
        } else {
            System.out.println("usuario não é vendendor");
        }
        return followersDTO;
    }
    public FollowedDTO followedTotal(Integer userId){

        FollowedDTO followedDTO = null;
        SellerDTO sellerDTO = null;
        List<SellerDTO> list = new ArrayList<>();
        if (user.findById(userId).get().getSeller().equals(false)) {
            int size = user.findById(userId).get().getFollows().size();

            for (int i = 0; i < size; i++ ) {
                User user1 = user.findById(userId).get().getFollows().get(i);
                sellerDTO = new SellerDTO(user1.getUserId(), user1.getUserName());
                list.add(sellerDTO);
                User userDTO = user.findById(userId).get();
                followedDTO = new FollowedDTO(userDTO.getUserId(), userDTO.getUserName(), list);
            }
        } else {
            System.out.println("usuario é vendendor");
        }
        return followedDTO;
    }
}
