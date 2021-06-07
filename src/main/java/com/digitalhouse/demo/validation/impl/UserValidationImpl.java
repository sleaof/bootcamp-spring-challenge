package com.digitalhouse.demo.validation.impl;

import com.digitalhouse.demo.entities.*;
import com.digitalhouse.demo.repositories.UserRepository;
import com.digitalhouse.demo.validation.UserValidation;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidationImpl implements UserValidation {

    private UserRepository repository;

    public UserValidationImpl(UserRepository repository) {
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
    public Boolean validateIfIsASeller(Integer userIdToFollow) {
        Optional<User> user = repository.findById(userIdToFollow);
        return user.get().getSeller() == Boolean.TRUE;
    }

    @Override
    public Boolean validateIsEmpty(Integer userId) {
        return repository.findById(userId).isEmpty();
    }
}
