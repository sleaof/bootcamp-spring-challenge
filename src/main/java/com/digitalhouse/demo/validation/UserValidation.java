package com.digitalhouse.demo.validation;

import com.digitalhouse.demo.entities.*;

import java.util.Optional;

public interface UserValidation {
    Boolean validateById(Integer userId);
    Boolean validateByEqualId(Integer userId, Integer otherUserId);
    Boolean validateRepeatFollower(Optional<User> user, Optional<Seller> userToFollow);
    Boolean validateIfIsASeller(Integer userIdToFollow);
    Boolean validateIsEmpty(Integer userId);
}
