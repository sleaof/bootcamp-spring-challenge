package com.digitalhouse.demo.controllers;

import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.entities.*;
import com.digitalhouse.demo.exceptions.*;
import com.digitalhouse.demo.services.ProductService;
import com.digitalhouse.demo.validations.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService service;
    private Validation validation;

    public ProductController(ProductService service, Validation validation) {
        this.service = service;
        this.validation = validation;
    }

    @PostMapping("/newpost")
    public ResponseEntity<Post> createANewPost(@RequestBody Post post) {
        if (validation.validatePostIsEmpty(post))
            return new ResponseEntity(new NotFoundException("Post not found."), HttpStatus.NOT_FOUND);
        return new ResponseEntity(service.createANewPost(post), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/list")
    public ResponseEntity<PostResponseDTO> listofPublicationsMadeBySelles(@PathVariable Integer userId) {
        if (!validation.validateById(userId) || validation.validateIsEmpty(userId) )
            return new ResponseEntity(new NotFoundException("User not found."), HttpStatus.NOT_FOUND);

        if (validation.validateIfIsASeller(userId))
            return new ResponseEntity(new BadRequestException("Invalid operation. Only users can follow sellers."), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<PostResponseDTO>(service.listPostsBySeller(userId), HttpStatus.OK);
    }

    @GetMapping("/followed/{userId}/orderedList")
    public ResponseEntity<PostResponseDTO> sortFollowedByData(@PathVariable Integer userId, @RequestParam(value = "order") String order) {
        if (validation.validateIsEmpty(userId) || !validation.validateById(userId))
            return new ResponseEntity(new NotFoundException("User not found."), HttpStatus.NOT_FOUND);

        if (validation.validateIfIsASeller(userId))
            return new ResponseEntity(new BadRequestException("Invalid operation. If you're a seller, you don't have followed."), HttpStatus.BAD_REQUEST);

        PostResponseDTO postsBySellersDTO = new PostResponseDTO();
        postsBySellersDTO.setUserId(userId);
        if (order.equalsIgnoreCase("date_asc")) {
            postsBySellersDTO.setPosts(service.sortFollowedByDataAsc(userId));
            return new ResponseEntity(postsBySellersDTO, HttpStatus.OK);
        } else if (order.equalsIgnoreCase("date_desc")){
            postsBySellersDTO.setPosts(service.sortFollowedByDataDesc(userId));
            return new ResponseEntity(postsBySellersDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity(new BadRequestException("Your operation could not be completed. Check the information."), HttpStatus.BAD_REQUEST);
        }
    }
}
