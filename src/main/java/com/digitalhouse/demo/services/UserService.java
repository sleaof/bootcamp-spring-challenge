package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.entities.Post;
import com.digitalhouse.demo.entities.Product;
import com.digitalhouse.demo.entities.Seller;
import com.digitalhouse.demo.entities.User;
import com.digitalhouse.demo.repositories.SellerRepository;
import com.digitalhouse.demo.repositories.UserRepository;
import com.digitalhouse.demo.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    SellerService sellerService;

    @Autowired
    PostService postService;


    public User addUser(User user) {
        Seller seller = new Seller();
        if (user.isSeller()) {
            seller.setId(user.getId());
            seller.setName(user.getName());
            seller.setSeller(user.isSeller());
            return sellerRepository.save(seller);
        }
        return userRepository.save(user);

    }


    public void followNew(Long userId, Long userIdToFollow) {
        User user = userFindById(userId);
        Seller seller = sellerService.sellerFindById(userIdToFollow);

        if (!alreadyFollowing(user, seller)) {
            //Adiciona o Seguidor na Lista de Followers
            seller.getFollowers().add(user);
            sellerRepository.save(seller);
            //Adiciona o Vendedor na Lista de Followed
            user.getFollowed().add(seller);
            userRepository.save(user);
        }
    }

    public void unfollow(Long userId, Long userIdToUnfollow) {
        User user = userFindById(userId);
        Seller seller = sellerService.sellerFindById(userIdToUnfollow);
        if (alreadyFollowing(user, seller)) {
            user.getFollowed().remove(seller);
            userRepository.save(user);
            seller.getFollowers().remove(user);
            sellerRepository.save(seller);
        }
    }

    public ListPostDTO listPosts(Long userId) {
        User user = userFindById(userId);
        ListPostDTO listPostDTO = new ListPostDTO();
        listPostDTO.setId(user.getId());

        for (Seller s : user.getFollowed()) {
            for (Post p : s.getPosts()) {
                if (p.getDate().isAfter(LocalDate.now().minusDays(15))) {
                    ProductDTO productDTO = new ProductDTO();
                    PostDTO postDTO = new PostDTO();
                    postDTO.setUserID(s.getId());
                    postDTO.setCategory(p.getCategory());
                    postDTO.setDate(p.getDate());
                    postDTO.setPrice(p.getPrice());
                    listPostDTO.getPosts().add(postDTO);
                    Post post = postService.postFindById(p.getPostId());
                    for (Product p2 : post.getDetails()) {
                        productDTO.setProductName(p2.getProductName());
                        productDTO.setBrand(p2.getBrand());
                        productDTO.setColor(p2.getColor());
                        productDTO.setType(p2.getType());
                        productDTO.setNotes(p2.getNotes());
                        postDTO.getProducts().add(productDTO);

                    }
                }
            }
        }
        return listPostDTO;
    }

    public ListPostDTO listPostAsc(Long id) {
        User user = userFindById(id);
        ListPostDTO listPostDTO = new ListPostDTO();
        listPostDTO.setId(user.getId());

        for (Seller s : user.getFollowed()) {
            for (Post p : s.getPosts()) {
                ProductDTO productDTO = new ProductDTO();
                PostDTO postDTO = new PostDTO();
                postDTO.setUserID(s.getId());
                postDTO.setCategory(p.getCategory());
                postDTO.setDate(p.getDate());
                postDTO.setPrice(p.getPrice());
                listPostDTO.getPosts().add(postDTO);
                Post post = postService.postFindById(p.getPostId());
                for (Product p2 : post.getDetails()) {
                    productDTO.setProductName(p2.getProductName());
                    productDTO.setBrand(p2.getBrand());
                    productDTO.setColor(p2.getColor());
                    productDTO.setType(p2.getType());
                    productDTO.setNotes(p2.getNotes());
                    postDTO.getProducts().add(productDTO);
                }
            }
        }
        List<PostDTO> orderAsc = listPostDTO.getPosts().stream()
                .sorted(Comparator.comparing(PostDTO::getDate))
                .collect(Collectors.toList());
        listPostDTO.setPosts(orderAsc);
        return listPostDTO;
    }

    public ListPostDTO listPostDesc(Long id) {
        User user = userFindById(id);
        ListPostDTO listPostDTO = new ListPostDTO();
        listPostDTO.setId(user.getId());

        for (Seller s : user.getFollowed()) {
            for (Post p : s.getPosts()) {
                ProductDTO productDTO = new ProductDTO();
                PostDTO postDTO = new PostDTO();
                postDTO.setUserID(s.getId());
                postDTO.setCategory(p.getCategory());
                postDTO.setDate(p.getDate());
                postDTO.setPrice(p.getPrice());
                listPostDTO.getPosts().add(postDTO);
                Post post = postService.postFindById(p.getPostId());
                for (Product p2 : post.getDetails()) {
                    productDTO.setProductName(p2.getProductName());
                    productDTO.setBrand(p2.getBrand());
                    productDTO.setColor(p2.getColor());
                    productDTO.setType(p2.getType());
                    productDTO.setNotes(p2.getNotes());
                    postDTO.getProducts().add(productDTO);
                }
            }
        }
        List<PostDTO> orderDesc = listPostDTO.getPosts().stream()
                .sorted(Comparator.comparing(PostDTO::getDate)
                        .reversed())
                .collect(Collectors.toList());
        listPostDTO.setPosts(orderDesc);
        return listPostDTO;
    }

    public ListFollowsOrderDTO listFollowsAsc(Long id) {
        User user = userFindById(id);
        ListFollowsOrderDTO listFollows = new ListFollowsOrderDTO();


        List<SellerDTO> list = new ArrayList<>();

        listFollows.setId(user.getId());
        listFollows.setName(user.getName());

        for (Seller s : user.getFollowed()) {
            SellerDTO sellerDTO = new SellerDTO();
            sellerDTO.setName(s.getName());
            sellerDTO.setId(s.getId());
            list.add(sellerDTO);
        }

        Collections.sort(list, new SortedFollowsDTO());
        for (int i = 0; i < list.size(); i++) {
            listFollows.getFollows().add(list.get(i));
        }
        return listFollows;
    }

    public ListFollowsOrderDTO listFollowsDesc(Long id) {
        User user = userFindById(id);
        ListFollowsOrderDTO listFollows = new ListFollowsOrderDTO();

        List<SellerDTO> list = new ArrayList<>();

        listFollows.setId(user.getId());
        listFollows.setName(user.getName());

        for (Seller s : user.getFollowed()) {
            SellerDTO sellerDTO = new SellerDTO();
            sellerDTO.setName(s.getName());
            sellerDTO.setId(s.getId());
            list.add(sellerDTO);
        }

        Collections.sort(list, new SortedFollowsDTO());
        for (int i = 0; i < list.size(); i++) {
            listFollows.getFollows().add(list.get(i));
        }
        listFollows.setFollows(listFollows.getFollows().stream().sorted(Comparator.comparing(SellerDTO::getName).reversed()).collect(Collectors.toList()));
        return listFollows;

    }


    public boolean alreadyFollowing(User user, Seller seller) {
        return user.getFollowed().contains(seller);
    }

    //Arrumar esse metodo;
    public CountUserFollowsDTO listUserFollowed(Long id) {
        CountUserFollowsDTO countUserFollowsDTO = new CountUserFollowsDTO();
        User user = userFindById(id);
        countUserFollowsDTO.setId(user.getId());
        countUserFollowsDTO.setName(user.getName());
        for (User s : user.getFollowed()) {
            SellerDTO sellerDTO = new SellerDTO();
            sellerDTO.setId(s.getId());
            sellerDTO.setName(s.getName());
            countUserFollowsDTO.getFollowed().add(sellerDTO);
        }
        return countUserFollowsDTO;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User userFindById(Long id) {
        Optional<User> obj = userRepository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }


}
