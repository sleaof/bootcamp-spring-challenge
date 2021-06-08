package com.digitalhouse.demo.controllers;

import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.services.SellerService;
import com.digitalhouse.demo.services.UserService;
import com.digitalhouse.demo.services.PostService;
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

    @Autowired
    PostService postService;


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
    public CountFollowsDTO listSellerFollowers(@PathVariable Long UserID){
        return sellerService.listSellerFollows(UserID);
    }

    //US 0004: Obter uma lista de todos os vendedores que um determinado usuário segue
    @GetMapping("/users/{UserID}/followed/list")
    public CountUserFollowsDTO listUserFollowed(@PathVariable Long UserID){
        return userService.listUserFollowed(UserID);
    }

    //US 0005: Cadastrar uma nova publicação
    @PostMapping("/products/newpost")
    public void newPost(@RequestBody PostDTO postDTO){
            postService.makeNewPost(postDTO);

    }

    //US 0006: Obter uma lista das publicações feitas pelos vendedores que um usuário segue
    //nas últimas duas semanas (para isso, ter em conta ordenação por data, a maioria das
    //publicações recentes primeiro).
    @GetMapping("/products/followed/{userId}/list")
    public ListPostDTO listPost(@PathVariable Long userId){
        return userService.listPosts(userId);
    }



    //US 0007: Ser capaz de realizar a ação de “Deixar de seguir” (parar de seguir) um
    //determinado vendedor.
    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow}")
    public void unfollowSeller(@PathVariable Long userId, @PathVariable Long userIdToUnfollow){
        userService.unfollow(userId,userIdToUnfollow);
    }

    //US 0008: Ordem alfabética crescente e decrescente
    @GetMapping("/users/{userID}/followers/listOrder")
    public ListFollowersOrderDTO listFollowersOrder(@PathVariable Long userID, @RequestParam(value = "order")String order){
        if(order.equalsIgnoreCase("name_asc")){
            return sellerService.listFollowersDesc(userID);
        }else{
            return sellerService.listFollowersAsc(userID);
        }
    }

    @GetMapping("/users/{userID}/follows/listOrder")
    public ListFollowsOrderDTO listFollowsOrder(@PathVariable Long userID, @RequestParam(value = "order") String order){
        if(order.equalsIgnoreCase("name_asc")){
            return userService.listFollowsAsc(userID);
        }else{
            return userService.listFollowsDesc(userID);
        }
    }

    //US 0009: Classificar por data crescente e decrescente
    @GetMapping("/products/followed/{userId}/listOrder")
    public ListPostDTO listPostOrder(@PathVariable Long userId, @RequestParam(value = "order") String order){
        if(order.equalsIgnoreCase("date_asc")){
            return userService.listPostAsc(userId);
        }
        return userService.listPostDesc(userId);
    }


    @PostMapping("/addUsers")
    @ResponseStatus(HttpStatus.CREATED)
    public User creatUser(@RequestBody User user){
        return  userService.addUser(user);
    }

}
