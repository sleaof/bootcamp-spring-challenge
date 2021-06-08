package com.digitalhouse.demo.validations;

import com.digitalhouse.demo.entities.*;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface Validation {

    Boolean validateById(Integer userId);
    Boolean validateByEqualId(Integer userId, Integer otherUserId);
    Boolean validateRepeatFollower(Optional<User> user, Optional<Seller> userToFollow);
    Boolean validateIfIsASeller(Integer userId);
    Boolean validateIsEmpty(Integer userId);
    Boolean validatePostIsEmpty(Post post);
}
