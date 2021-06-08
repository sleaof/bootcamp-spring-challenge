package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.entities.*;

import java.util.List;

public interface ProductService {

    public CreateANewPostDTO createANewPost(Post post);
    public PostsBySellersDTO listPostsBySeller(Integer userId);
    public List<Post> sortFollowedByDataAsc(Integer userId);
    public List<Post> sortFollowedByDataDesc(Integer userId);
}
