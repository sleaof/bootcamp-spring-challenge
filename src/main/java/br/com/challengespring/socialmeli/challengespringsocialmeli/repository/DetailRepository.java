package br.com.challengespring.socialmeli.challengespringsocialmeli.repository;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {

}
