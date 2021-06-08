package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.models.User;

import java.util.List;

public interface UserRepository {
    List<User> getAll();
    User getById(Long userId);
    void follow(User user, Long userIdToFollow);
    void unfollow(User user, Long userIdToUnfollow);
}
