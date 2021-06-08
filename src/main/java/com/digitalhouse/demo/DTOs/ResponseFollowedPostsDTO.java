package com.digitalhouse.demo.DTOs;

import java.util.ArrayList;
import java.util.List;

public class ResponseFollowedPostsDTO {
    private Long userId;
    private List<PostDTO> posts = new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
