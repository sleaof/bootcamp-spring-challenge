package com.digitalhouse.demo.services;

import com.digitalhouse.demo.entities.Post;
import com.digitalhouse.demo.entities.Product;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.PostRepository;
import com.digitalhouse.demo.repositories.ProductRepository;
import com.digitalhouse.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
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
}
