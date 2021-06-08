package com.digitalhouse.demo.dtos;

import java.util.List;

public class PromoPostResponseDTO {
    private Integer userId;
    private List<PromoPostDTO> posts;

    public PromoPostResponseDTO() {
    }

    public PromoPostResponseDTO(Integer userId, List<PromoPostDTO> posts) {
        this.userId = userId;
        this.posts = posts;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<PromoPostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PromoPostDTO> posts) {
        this.posts = posts;
    }
}
