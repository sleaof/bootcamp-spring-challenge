package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.PostsBySellersDTO;
import com.digitalhouse.demo.entities.Post;
import com.digitalhouse.demo.entities.User;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Post createANewPost(Post post);
    public PostsBySellersDTO listPostsBySeller(Optional<User> user);
    public List<Post> sortFollowedByDataAsc(PostsBySellersDTO postsDTO);
    public List<Post> sortFollowedByDataDesc(PostsBySellersDTO DTO);
}
