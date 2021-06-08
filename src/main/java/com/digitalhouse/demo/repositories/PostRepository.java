package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.DTOs.PostDTO;
import com.digitalhouse.demo.DTOs.ResponseFollowedPostsDTO;

import java.util.List;

public interface PostRepository {
    List<PostDTO> getAll();
    PostDTO getById(Long idPost);
    void createPost(PostDTO postDTO);
    List<PostDTO> getPostsById(Long userId);
}
