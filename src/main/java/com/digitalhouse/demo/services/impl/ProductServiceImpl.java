package com.digitalhouse.demo.services.impl;

import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.entities.*;
import com.digitalhouse.demo.repositories.*;
import com.digitalhouse.demo.services.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private UserRepository userRepository;
    private PostRepository postRepository;
    private ProductRepository productRepository;

    public ProductServiceImpl(UserRepository userRepository, PostRepository postRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CreateANewPostDTO createANewPost(Post post) {
        post.setUser(userRepository.findById(post.getUserId()).get());
        productRepository.save(post.getDetail());
        postRepository.save(post);

        return new CreateANewPostDTO(
                post.getUser().getUserId(),
                post.getPostId(),
                post.getDate(),
                post.getDetail(),
                post.getCategory(),
                post.getPrice()
        );
    }

    @Override
    public PostsBySellersDTO listPostsBySeller(Integer userId) {
        Optional<User> user = userRepository.findById(userId);
        PostsBySellersDTO postsBySellersDTO = new PostsBySellersDTO();
        postsBySellersDTO.setUserId(user.get().getUserId());

        Set<Integer> mapId = user.get().getFollowed().stream().map(Seller::getUserId).collect(Collectors.toSet());

        postsBySellersDTO.setPosts(
                postRepository.findAll().stream()
                        .filter(o1 -> {
                            return mapId.stream()
                                    .anyMatch(i2 -> i2.equals(o1.getUser().getUserId()));
                        }).collect(Collectors.toList()));

        postsBySellersDTO.setPosts(postsBySellersDTO.getPosts().stream()
                .filter(p -> p.getDate().isAfter(LocalDate.now().minusDays(15)))
                .collect(Collectors.toList()));

        return postsBySellersDTO;
    }

    @Override
    public List<Post> sortFollowedByDataAsc(Integer userId) {
        PostsBySellersDTO postsBySellersDTO = listPostsBySeller(userId);
        return postsBySellersDTO.getPosts().stream()
                .sorted(Comparator.comparing(Post::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> sortFollowedByDataDesc(Integer userId) {
        PostsBySellersDTO postsBySellersDTO = listPostsBySeller(userId);
        return postsBySellersDTO.getPosts().stream()
                .sorted(Comparator.comparing(Post::getDate).reversed())
                .collect(Collectors.toList());
    }

}
