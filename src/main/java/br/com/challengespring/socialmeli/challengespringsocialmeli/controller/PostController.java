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
public class PostController {

    PostService service;

    @PostMapping("/products/newpost")
    public ResponseEntity<Post> newPost(@RequestBody @Validated Post post){
        if(post != null) {
            return new ResponseEntity<Post>(service.newPost(post), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get
    // localhost:4200/products/followed/{userId}/list
    // US 0006: Obter uma lista das publicações feitas pelos vendedores que um usuário
    // segue nas últimas duas semanas (para isso, ter em conta ordenação por data,
    // a maioria das publicações recentes primeiro).

    // Get
    // localhost:4200/users/{UserID}/followers/list?order=name_asc
    // /products/followed/{userId}/list?order=date_asc
    // /products/followed/{userId}/list?order=date_desc
    // US 0009: Classificar por data crescente e decrescente

}
