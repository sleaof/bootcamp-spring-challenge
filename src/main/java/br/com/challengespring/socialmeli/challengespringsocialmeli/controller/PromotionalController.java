package br.com.challengespring.socialmeli.challengespringsocialmeli.controller;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Post;
import br.com.challengespring.socialmeli.challengespringsocialmeli.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class PromotionalController {

    PostService service;

    // Post
    // US 0010: Realizar a publicação de um novo produto promocional
    // localhost:4200/products/newpromopost
    // Status Code 200 (OK)
    // Status Code 400 (Bad request)
    @PostMapping("/products/newpromopost")
    public ResponseEntity<Post> newPost(@RequestBody @Validated Post post){
        if(post != null) {
            return new ResponseEntity<Post>(service.newPost(post), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get
    // US 0011: Obtenha o quantidade de produtos promocionais de um vendedor específico
    // localhost:4200/products/{userId}/countPromo/
    // Status Code 200 (OK)
    // Status Code 400 (Bad request)

    // Get
    // localhost:4200/products/{userId}/list/
    // US 0012: Obter uma lista de todos os produtos promocionais de um vendedor específico
}
