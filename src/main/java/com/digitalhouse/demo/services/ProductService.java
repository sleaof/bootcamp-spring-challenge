package com.digitalhouse.demo.services;

import com.digitalhouse.demo.dtos.*;
import com.digitalhouse.demo.entities.*;

import java.util.List;

public interface ProductService {

    public PostDTO createANewPost(Post post);
    public PostResponseDTO listPostsBySeller(Integer userId);
    public List<PostDTO> sortFollowedByDataAsc(Integer userId);
    public List<PostDTO> sortFollowedByDataDesc(Integer userId);
    public PromoPostDTO createAPromoPost(Post post);
}
