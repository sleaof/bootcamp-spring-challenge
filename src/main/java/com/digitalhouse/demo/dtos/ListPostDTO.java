package com.digitalhouse.demo.dtos;

import java.util.ArrayList;
import java.util.List;

public class ListPostDTO {

    private Long id;
    private List<PostDTO> posts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }

    public void setPosts(List<PostDTO> posts) {
        this.posts = posts;
    }
}
