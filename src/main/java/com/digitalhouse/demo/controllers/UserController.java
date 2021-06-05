package com.digitalhouse.demo.controllers;

import com.digitalhouse.demo.dtos.ContFollowsDTO;
import com.digitalhouse.demo.dtos.CountFollowersDTO;
import com.digitalhouse.demo.dtos.CountUserFollowsDTO;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.services.SellerService;
import com.digitalhouse.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SocialMeli")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    SellerService sellerService;


    //US 0001: Ser capaz de realizar a ação de “Follow” (seguir) a um determinado vendedor
    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    @ResponseStatus(HttpStatus.OK)
    public void followNew(@PathVariable Long userId, @PathVariable Long userIdToFollow) {
        userService.followNew(userId, userIdToFollow);
    }

    //US 0002: Obter o resultado do número de usuários que seguem um determinado
    //vendedor
    @GetMapping("users/{userId}/followers/count")
    public CountFollowersDTO sellerFollowersCount(@PathVariable Long userId){
        return sellerService.countFollewers(userId);
    }

    //US 0003: Obter uma lista de todos os usuários que seguem um determinado vendedor
    @GetMapping("/users/{UserID}/followers/list")
    public ContFollowsDTO listSellerFollowers(@PathVariable Long UserID){
        return sellerService.listSellerFollows(UserID);
    }

    //US 0004: Obter uma lista de todos os vendedores que um determinado usuário segue
    @GetMapping("/users/{UserID}/followed/list")
    public CountUserFollowsDTO listUserFollowed(@PathVariable Long UserID){
        return userService.listUserFollowed(UserID);
    }

    @PostMapping("/addUsers")
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody User user){
        return  userService.addUser(user);
    }



    @GetMapping("/users")
    public List<User> findAll(){
        return userService.findAll();
    }
    //public ResponseEntity<User> findAll(){
    //    return ResponseEntity.ok().body(User);
    //}
}
