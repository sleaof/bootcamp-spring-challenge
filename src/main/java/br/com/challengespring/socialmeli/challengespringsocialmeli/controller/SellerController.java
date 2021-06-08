package br.com.challengespring.socialmeli.challengespringsocialmeli.controller;

import br.com.challengespring.socialmeli.challengespringsocialmeli.dto.SellerDTO;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Seller;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.User;
import br.com.challengespring.socialmeli.challengespringsocialmeli.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SellerController {

    @Autowired
    private SellerService service;

    @PostMapping("/sellers/newseller")
    public ResponseEntity<SellerDTO> newSeller(@RequestBody @Validated Seller seller){
        if(seller != null) {
            return new ResponseEntity<SellerDTO>(service.newSeller(seller), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<SellerDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/sellers/{idSeller}")
    public ResponseEntity<User> buscarSellerPorId(@RequestHeader("idSeller") Long idSeller){
        Optional<User> seller = Optional.ofNullable(service.getSeller(idSeller));
        if (!seller.isPresent()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<User>(seller.get(), HttpStatus.OK);
        }
    }

    //  US 0002: Obter o resultado do número de usuários que seguem um determinado vendedor
    @GetMapping("/sellers/{idSeller}/followers/count/")
    public ResponseEntity<SellerDTO> countFollowed(@RequestHeader("idSeller") @Validated Long idSeller){
        if(idSeller != null) {
            return new ResponseEntity<SellerDTO>(service.countFollow(idSeller), HttpStatus.OK);
        } else {
            return new ResponseEntity<SellerDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    //  US 0003 - Obter uma lista de todos os usuários que seguem um determinado vendedor (quem me segue?)
    //TODO: Adicionar exceptions
    @GetMapping("/sellers/{idSeller}/followers/list")
    public List<User> listFollowers(@RequestHeader("idSeller") @Validated Long idSeller){
        List<User> sellers = service.userListFollowingSeller(idSeller);
        return sellers;
    }
}
