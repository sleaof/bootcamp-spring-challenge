package com.digitalhouse.demo.dtos;

import java.util.List;

public class SellerDTO {

    private Long id;
    private String name;

    public SellerDTO(){
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
}
