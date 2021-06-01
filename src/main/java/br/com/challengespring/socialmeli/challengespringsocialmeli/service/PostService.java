package br.com.challengespring.socialmeli.challengespringsocialmeli.service;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Post;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    PostRepository postRepository;

    // Post
    // localhost:4200/products/newpost
    // US 0005: Cadastrar uma nova publicação
    //  Status Code 200 (tudo OK)
    //  Status Code 400 (Bad Request)
    public Post newPost(Post post){
        postRepository.save(post);
        return post;
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
