package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.PostDTO;
import com.digitalhouse.demo.dtos.ProductDTO;
import com.digitalhouse.demo.entities.Post;
import com.digitalhouse.demo.entities.Product;
import com.digitalhouse.demo.entities.Seller;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.PostRepository;
import com.digitalhouse.demo.repositories.ProductRepository;
import com.digitalhouse.demo.repositories.SellerRepository;
import com.digitalhouse.demo.services.SellerService;
import com.digitalhouse.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    SellerService sellerService;
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    PostRepository postRepository;
    @Autowired
    ProductRepository productRepository;

    public void makeNewPost(PostDTO postDTO){
        Seller seller = sellerService.sellerFindById(postDTO.getUserID());
        Post post = new Post();
        Product product = new Product();
        List<Product> productList = new ArrayList<>();

        for(ProductDTO p : postDTO.getProducts()){
            product.setProductName(p.getProductName());
            product.setBrand(p.getBrand());
            product.setColor(p.getColor());
            product.setNotes(p.getNotes());
            product.setType(p.getType());

            productList.add(product);
        }
        post.setCategory(postDTO.getCategory());
        post.setDate(postDTO.getDate());
        post.setPrice(postDTO.getPrice());

        product.setPost(post);

        post.getDetails().add(product);
        post.setSeller(seller);
        post.setDetails(productList);

        seller.getPosts().add(post);

        postRepository.save(post);
        productRepository.save(product);


    }


    public Post postFindById(Long id) {
        Optional<Post> obj = postRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
