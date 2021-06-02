package com.digitalhouse.demo.Service;

import com.digitalhouse.demo.DTOs.SellerDTO;
import com.digitalhouse.demo.Model.Buyer;
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
    @Autowired
    private SellerRepository seller;

    // Pega o usuario por Id e vendedor
    public Optional<User> followed(Integer userId, int userIdToFollow) {

        Boolean sellerUser = user.findById(userId).get().getSeller();
        Boolean buyer = user.findById(userId).get().getSeller();
        Boolean seller = user.findById(userIdToFollow).get().getSeller();

        if (buyer.equals(false) && seller.equals(true) || seller.equals(true) && sellerUser.equals(true)) {
           User user1 = user.findById(userId).get();
           user1.getFollowers().add(user.findById(userIdToFollow).get());
            user.save(user1);
        }else{
            System.out.println("nao foi possivel");
        }

        return user.findById(userId);
    }


}
