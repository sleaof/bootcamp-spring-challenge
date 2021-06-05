package com.digitalhouse.demo.dtos;

import java.util.ArrayList;
import java.util.List;

public class CountUserFollowsDTO {

    private Long id;
    private String name;
    private List<SellerDTO> followed = new ArrayList<>();

    public CountUserFollowsDTO(){
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

    public List<SellerDTO> getFollowed() {
        return followed;
    }

    public void setFollowed(List<SellerDTO> followed) {
        this.followed = followed;
    }
}
