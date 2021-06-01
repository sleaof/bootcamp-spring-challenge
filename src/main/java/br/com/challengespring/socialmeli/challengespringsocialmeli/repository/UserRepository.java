package br.com.challengespring.socialmeli.challengespringsocialmeli.repository;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
