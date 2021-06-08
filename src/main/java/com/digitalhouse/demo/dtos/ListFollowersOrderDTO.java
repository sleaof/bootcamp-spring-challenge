package com.digitalhouse.demo.dtos;

import java.util.ArrayList;
import java.util.List;

public class ListFollowersOrderDTO {

    private Long id;
    private String name;
    private List<UserDTO> followers = new ArrayList<>();

    public ListFollowersOrderDTO(){
    }

    public ListFollowersOrderDTO(Long id, String name, List<UserDTO> followers) {
        this.id = id;
        this.name = name;
        this.followers = followers;
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

    public List<UserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(List<UserDTO> followers) {
        this.followers = followers;
    }
}
