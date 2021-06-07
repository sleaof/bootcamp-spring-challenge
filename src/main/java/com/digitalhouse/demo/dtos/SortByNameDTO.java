package com.digitalhouse.demo.dtos;

import com.digitalhouse.demo.entities.User;

import java.util.List;

public class SortByNameDTO {

    private List<User> users;

    public SortByNameDTO() {
    }

    public SortByNameDTO(List<User> users) {
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
