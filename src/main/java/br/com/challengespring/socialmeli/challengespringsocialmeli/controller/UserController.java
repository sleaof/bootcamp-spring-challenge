package br.com.challengespring.socialmeli.challengespringsocialmeli.controller;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.User;
import br.com.challengespring.socialmeli.challengespringsocialmeli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService service;

    // Post
    // localhost:4200/users/{userId}/follow/{userIdToFollow}
    //  US 0001: Ser capaz de realizar a ação de “Follow” (seguir) a um determinado vendedor
    //  Status Code 200 (tudo OK)
    //  Status Code 400 (Bad Request)
    @PostMapping("/users/newuser")
    public ResponseEntity<User> newUser(@RequestBody @Validated User user){
        System.out.println(user);
        if(user != null) {
        return new ResponseEntity<User>(service.newUser(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{idUser}")
    public Optional<User> getUser(@RequestHeader @Validated Long idUser){
        return service.getUser(idUser);
//        if(idUser != null) {
//            return new ResponseEntity<User>(service.getUser(idUser), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
//        }
    }

    // Get
    // localhost:4200/users/{userId}/followers/count/
    //  US 0002: Obter o resultado do número de usuários que seguem um determinado vendedor
    //  Status Code 200 (tudo OK)
    //  Status Code 400 (Bad Request)


    // Get
    // localhost:4200/users/{UserID}/followers/list
    //  US 0004: Obter uma lista de todos os vendedores que um determinado usuário segue (quem estou seguindo?)
    //  Status Code 200 (tudo OK)
    //  Status Code 400 (Bad Request)

    // Post
    // localhost:4200/users/{userId}/unfollow/{userIdToUnfollow}
    //  US 0007: Ser capaz de realizar a ação de “Deixar de seguir” (parar de seguir) um determinado vendedor.

    // Get
    // localhost:4200/users/{UserID}/followers/list?order=name_asc
    // /users/{UserID}/followers/list?order=name_desc
    // /users/{UserID}/followed/list?order=name_asc
    // /users/{UserID}/followed/list?order=name_desc
    // US 0008: Ordem alfabética crescente e decrescente

}
