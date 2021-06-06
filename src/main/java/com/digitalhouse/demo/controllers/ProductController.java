package com.digitalhouse.demo.controllers;

import com.digitalhouse.demo.entities.Post;
import com.digitalhouse.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @PostMapping("/newpost")
    public Post createANewPost(@RequestBody Post post) {
        return service.createANewPost(post);
    }

    @GetMapping("/followed/{userId}/list")
    public void listofPublicationsMadeBySalles(@PathVariable Integer userId) {
        // Incrementar o response
        // Adicionar retorno ao método
        // Gerar o dto de retorno
    }
}
