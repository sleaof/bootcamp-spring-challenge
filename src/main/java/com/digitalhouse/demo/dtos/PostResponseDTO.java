package com.digitalhouse.demo.dtos;

import java.util.List;

public class PostResponseDTO {
    private Integer userId;
    private List<PostDTO> posts;

    public PostResponseDTO() {
    }

    public PostResponseDTO(Integer userId, List<PostDTO> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}