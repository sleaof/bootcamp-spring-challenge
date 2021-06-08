package com.digitalhouse.demo.validations.impl;

import com.digitalhouse.demo.entities.*;
import com.digitalhouse.demo.repositories.UserRepository;
import com.digitalhouse.demo.validations.Validation;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidationImpl implements Validation {
    private UserRepository repository;

    public ValidationImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean validateById(Integer userId) {
        return repository.findById(userId).isPresent();
    }

    @Override
    public Boolean validateByEqualId(Integer userId, Integer otherUserId) {
        return userId.equals(otherUserId);
    }

    @Override
    public Boolean validateRepeatFollower(Optional<User> user, Optional<Seller> userToFollow) {
        for (Seller seller : user.get().getFollowed()) {
            if (seller.getUserId().equals(userToFollow.get().getUserId()))
                return true;
        }
        return false;
    }

    @Override
    public Boolean validateIfIsASeller(Integer userId) {
        Optional<User> user = repository.findById(userId);
        return user.get().getSeller() == Boolean.TRUE;
    }

    @Override
    public Boolean validateIsEmpty(Integer userId) {
        return repository.findById(userId).isEmpty();
    }

    public Boolean validatePostIsEmpty(Post post) {
        return post==null;
    }
}
