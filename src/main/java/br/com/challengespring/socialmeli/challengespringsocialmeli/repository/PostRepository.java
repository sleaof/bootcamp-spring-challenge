package br.com.challengespring.socialmeli.challengespringsocialmeli.repository;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
