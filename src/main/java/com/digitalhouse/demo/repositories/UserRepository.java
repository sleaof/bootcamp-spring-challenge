package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
