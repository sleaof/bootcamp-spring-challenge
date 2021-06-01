package br.com.challengespring.socialmeli.challengespringsocialmeli.service;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Vendor;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.VendorRepository;

public class VendorService {

    VendorRepository repository;

    public Vendor newVendor(Vendor vendor){
        repository.save(vendor);
        return vendor;
    }
}
