package br.com.challengespring.socialmeli.challengespringsocialmeli.service;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Seller;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.SellerRepository;

public class SellerService {

    SellerRepository repository;

    public Seller newVendor(Seller vendor){
        repository.save(vendor);
        return vendor;
    }
}
