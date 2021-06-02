package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
