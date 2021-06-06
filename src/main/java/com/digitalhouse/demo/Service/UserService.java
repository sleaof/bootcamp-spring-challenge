package com.digitalhouse.demo.Service;

import com.digitalhouse.demo.DTOs.UserDTO;
import com.digitalhouse.demo.Model.Seller;
import com.digitalhouse.demo.Model.User;
import com.digitalhouse.demo.Repository.BuyerRepository;
import com.digitalhouse.demo.Repository.SellerRepository;
import com.digitalhouse.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private BuyerRepository buyerList;

    @Autowired
    private UserRepository user;

    private Seller seller1;

    private UserDTO userDTO;

    @Autowired
    private SellerRepository sellerRepository;

    User follower = null;

    public User transformaUsuario(UserDTO userDTO){
        follower = new  User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getSeller());
        return new  User(userDTO.getUserId(), userDTO.getUserName(), userDTO.getSeller());
    }

    // Pega o usuario por Id e vendedor
    public User followed(Integer userId, int userIdToFollow) {

        Boolean sellerUser = user.findById(userId).get().getSeller();
        Boolean buyer = user.findById(userId).get().getSeller();
        Boolean seller = user.findById(userIdToFollow).get().getSeller();

        if (buyer.equals(false) && seller.equals(true) || seller.equals(true) && sellerUser.equals(true)) {
            Seller seller2 = sellerRepository.findById(userIdToFollow).get();
            seller2.getFollowersTotal().add(follower);
            sellerRepository.save(seller2);
           User user1 = user.findById(userId).get();
           user1.getFollowers().add(user.findById(userIdToFollow).get());
            user.save(user1);




        }else{
            System.out.println("nao foi possivel");
        }

        return user.findById(userId).get();
    }


}
