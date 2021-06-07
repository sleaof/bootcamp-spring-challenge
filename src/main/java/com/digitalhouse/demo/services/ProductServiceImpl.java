package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.PostsBySellersDTO;
import com.digitalhouse.demo.entities.Post;
import com.digitalhouse.demo.entities.Seller;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.PostRepository;
import com.digitalhouse.demo.repositories.ProductRepository;
import com.digitalhouse.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Post createANewPost(Post post) {
        post.setUser(repository.findById(post.getUserId()).get());
        productRepository.save(post.getDetail());
        postRepository.save(post);
        return post;
    }

    @Override
    public PostsBySellersDTO listPostsBySeller(Optional<User> user) {
        PostsBySellersDTO postsBySellersDTO = new PostsBySellersDTO();
        postsBySellersDTO.setUserId(user.get().getUserId());

        Set<Integer> mapId = user.get().getFollowed().stream().map(Seller::getUserId).collect(Collectors.toSet());

        postsBySellersDTO.setPosts(
                postRepository.findAll().stream()
                        .filter(o1 -> {
                            return mapId.stream()
                                    .anyMatch(i2 -> i2.equals(o1.getUser().getUserId()));
                        }).collect(Collectors.toList()));

        return postsBySellersDTO;
    }

    @Override
    public List<Post> sortFollowedByDataAsc(PostsBySellersDTO postsDTO) {
        return postsDTO.getPosts().stream()
                .sorted(Comparator.comparing(Post::getDate))
                .collect(Collectors.toList());
        }

    @Override
    public List<Post> sortFollowedByDataDesc(PostsBySellersDTO postsDTO) {
        return postsDTO.getPosts().stream()
                .sorted(Comparator.comparing(Post::getDate).reversed())
                .collect(Collectors.toList());
    }

}
