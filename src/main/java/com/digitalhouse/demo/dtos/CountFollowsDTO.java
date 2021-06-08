package com.digitalhouse.demo.dtos;

import java.util.ArrayList;
import java.util.List;

public class CountFollowsDTO {

    private Long id;
    private String name;
    private List<UserDTO> followers = new ArrayList<>();

    public CountFollowsDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<UserDTO> getFollows() {
        return followers;
    }

    public void setFollows(List<UserDTO> follows) {
        this.followers = follows;
    }
}
