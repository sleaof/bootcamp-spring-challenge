package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
