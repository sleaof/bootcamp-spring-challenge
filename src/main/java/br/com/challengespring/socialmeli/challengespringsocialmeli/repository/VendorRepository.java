package br.com.challengespring.socialmeli.challengespringsocialmeli.repository;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

}
