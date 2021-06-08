package com.digitalhouse.demo.Service;

import com.digitalhouse.demo.DTOs.SellerPostDTO;
import com.digitalhouse.demo.DTOs.SellerProductsDTO;
import com.digitalhouse.demo.Model.Post;
import com.digitalhouse.demo.Model.Seller;
import com.digitalhouse.demo.Model.User;
import com.digitalhouse.demo.Repository.DetailRepository;
import com.digitalhouse.demo.Repository.PostRepository;
import com.digitalhouse.demo.Repository.SellerRepository;
import com.digitalhouse.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.Comparator;
import java.util.List;


@Service
public class ProductsServices {
    @Autowired
    private UserRepository user;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private DetailRepository detailRepository;

    public Seller post(Integer postId, Integer product, Integer sellerId) {

        Post post = postRepository.findById(postId).get();
        post.getDetail().add(detailRepository.findById(product).get());
        postRepository.findById(postId).get().setUserId(sellerId);
        LocalDateTime agora = LocalDateTime.now();
        postRepository.findById(postId).get().setDate(agora);
        postRepository.save(post);

        Seller seller = sellerRepository.findById(sellerId).get();
        seller.getPosts().add(postRepository.findById(postId).get());
        sellerRepository.save(seller);

        System.out.println(seller);
        return seller;
    }

    public SellerPostDTO listPost(Integer userId) {

        SellerPostDTO sellerPostDTO = null;
        SellerProductsDTO sellerProductsDTO = null;
        List<SellerProductsDTO> list = new ArrayList<>();
       /// list.sort(Comparator.comparing();
        if (user.findById(userId).get().getSeller().equals(false)) {
            int size = user.findById(userId).get().getFollows().size();

            for (int i = 0; i < size; i++) {
                User user1 = user.findById(userId).get().getFollows().get(i);
                Seller seller = sellerRepository.findById(user1.getUserId()).get();
                sellerProductsDTO = new SellerProductsDTO(user1.getUserId(), user1.getUserName(), seller.getPosts());
                list.add(sellerProductsDTO);
                User userDTO = user.findById(userId).get();
                sellerPostDTO = new SellerPostDTO(userDTO.getUserId(), userDTO.getUserName(), list);
            }
        } else {
            System.out.println("usuario é vendendor");
        }

        return sellerPostDTO;
    }

}


