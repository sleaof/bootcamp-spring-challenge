package com.digitalhouse.demo.repositories;

import com.digitalhouse.demo.models.Seller;

import java.util.List;

public interface SellerRepository {
    List<Seller> getAll();
    Seller getById(Long userId);
    void follow(Seller seller, Long userId);
    void unfollow(Seller seller, Long userId);
}
