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
    public PostDTO createANewPost(Post post) {
        post.setUser(userRepository.findById(post.getUserId()).get());
        productRepository.save(post.getDetail());
        postRepository.save(post);

        return new PostDTO(
                post.getUser().getUserId(),
                post.getPostId(),
                post.getDate(),
                new ProductDTO(post.getDetail().getProductId(),
                        post.getDetail().getProductName(),
                        post.getDetail().getType(),
                        post.getDetail().getBrand(),
                        post.getDetail().getColor(),
                        post.getDetail().getNotes()
                        ),
                post.getCategory(),
                post.getPrice()
        );
    }

    @Override
    public PostResponseDTO listPostsBySeller(Integer userId) {
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

        return generatePostReponseDTO(postsBySellersDTO);
    }

    public PostResponseDTO generatePostReponseDTO(PostsBySellersDTO postsBySellersDTO) {
        PostResponseDTO postResponseDTO = new PostResponseDTO();
        List<Post> posts = postsBySellersDTO.getPosts();
        List<PostDTO> postsDTO = new ArrayList<>();

        for(Post p : posts) {
            postsDTO.add(new PostDTO(p.getUser().getUserId(),
                    p.getPostId(),
                    p.getDate(),
                    new ProductDTO(p.getDetail().getProductId(), p.getDetail().getProductName(), p.getDetail().getType(), p.getDetail().getBrand(), p.getDetail().getColor(), p.getDetail().getNotes()),
                    p.getCategory(),
                    p.getPrice()));
        }
        postResponseDTO.setUserId(postsBySellersDTO.getUserId());
        postResponseDTO.setPosts(postsDTO);
        return postResponseDTO;
    }

    @Override
    public List<PostDTO> sortFollowedByDataAsc(Integer userId) {
        PostResponseDTO postsBySellersDTO = listPostsBySeller(userId);
        return postsBySellersDTO.getPosts().stream()
                .sorted(Comparator.comparing(PostDTO::getDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> sortFollowedByDataDesc(Integer userId) {
        PostResponseDTO postsBySellersDTO = listPostsBySeller(userId);
        return postsBySellersDTO.getPosts().stream()
                .sorted(Comparator.comparing(PostDTO::getDate).reversed())
                .collect(Collectors.toList());
    }

}
