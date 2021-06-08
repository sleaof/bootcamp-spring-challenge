package com.digitalhouse.demo.dtos;

import java.util.ArrayList;
import java.util.List;

public class ListFollowsOrderDTO {

    private Long id;
    private String name;
    private List<SellerDTO> follows = new ArrayList<>();

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

    public List<SellerDTO> getFollows() {
        return follows;
    }

    public void setFollows(List<SellerDTO> follows) {
        this.follows = follows;
    }
}
