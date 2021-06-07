package com.digitalhouse.demo.controllers;

import com.digitalhouse.demo.dtos.PostsBySellersDTO;
import com.digitalhouse.demo.entities.Post;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.UserRepository;
import com.digitalhouse.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/newpost")
    public Post createANewPost(@RequestBody Post post) {
        return service.createANewPost(post);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostsBySellersDTO> listofPublicationsMadeBySalles(@PathVariable Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        return new ResponseEntity<PostsBySellersDTO>(service.listPostsBySeller(user), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/orderedList")
    public PostsBySellersDTO sortFollowedByData(@PathVariable Integer userId, @RequestParam(value = "order") String order) {
        PostsBySellersDTO postsBySellersDTO = service.listPostsBySeller(userRepository.findById(userId));
        if(order.equalsIgnoreCase("date_asc")) {
            postsBySellersDTO.setPosts(service.sortFollowedByDataAsc(postsBySellersDTO));
            return postsBySellersDTO;
        } else {
            postsBySellersDTO.setPosts(service.sortFollowedByDataDesc(postsBySellersDTO));
            return postsBySellersDTO;
        }
    }
}
