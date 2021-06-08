package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
