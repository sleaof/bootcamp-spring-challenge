package com.digitalhouse.demo.Controller;

import com.digitalhouse.demo.Model.Buyer;
import com.digitalhouse.demo.Model.Seller;
import com.digitalhouse.demo.Model.User;
import com.digitalhouse.demo.Repository.BuyerRepository;
import com.digitalhouse.demo.Repository.SellerRepository;
import com.digitalhouse.demo.Repository.UserRepository;
import com.digitalhouse.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class SpringChallengeController {

    @Autowired
    private UserService userService;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @PostMapping("/seller")
    public Seller createUsuario(@RequestBody Seller seller){
        return sellerRepository.save(seller);
    }
    @GetMapping("/seller")
    public List<Seller> pegarVendedor(){
        return sellerRepository.findAll();
    }

    @PostMapping("/user")
    public User createUsuario(@RequestBody Buyer buyer){
        return buyerRepository.save(buyer);
    }
    @GetMapping("/user")
    public List<Buyer> pegarUsuario(){
        return buyerRepository.findAll();
    }
    @GetMapping("/geral")
    public List<User> geralUsuario(){
        return userRepository.findAll();
    }

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public Optional<User> followTosellerById(@PathVariable Integer userId, @PathVariable int userIdToFollow){
        return userService.followed(userId,userIdToFollow);

    }
}
