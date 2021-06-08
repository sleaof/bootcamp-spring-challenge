package br.com.challengespring.socialmeli.challengespringsocialmeli.controller;

import br.com.challengespring.socialmeli.challengespringsocialmeli.dto.PostDTO;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Post;
import br.com.challengespring.socialmeli.challengespringsocialmeli.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostService service;

    // US 0005 - Cadastrar uma nova publicação
    @PostMapping("/products/newpost")
    public ResponseEntity<PostDTO> newPost(@RequestBody @Validated Post post){
        if(post != null) {
            return new ResponseEntity<PostDTO>(service.newPost(post), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<PostDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products/{idPost}")
    public ResponseEntity<Post> buscarPostPorId(@RequestHeader("idPost") Long idPost){
        Optional<Post> post = service.getPostById(idPost);
        if (!post.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<Post>(post.get(), HttpStatus.OK);
        }
    }

    // Get
    // localhost:4200/products/followed/{userId}/list
    // US 0006 - Obter uma lista das publicações feitas pelos vendedores que um usuário
    // segue nas últimas duas semanas (para isso, ter em conta ordenação por data,
    // a maioria das publicações recentes primeiro).


    // Get
    // localhost:4200/users/{UserID}/followers/list?order=name_asc
    // /products/followed/{userId}/list?order=date_asc
    // /products/followed/{userId}/list?order=date_desc
    // US 0009: Classificar por data crescente e decrescente


}
