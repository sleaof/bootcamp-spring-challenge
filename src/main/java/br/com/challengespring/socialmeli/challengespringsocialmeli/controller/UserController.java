package br.com.challengespring.socialmeli.challengespringsocialmeli.controller;

import br.com.challengespring.socialmeli.challengespringsocialmeli.dto.FollowdSellerDTO;
import br.com.challengespring.socialmeli.challengespringsocialmeli.dto.UserDTO;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Post;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Seller;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.User;
import br.com.challengespring.socialmeli.challengespringsocialmeli.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/users/newuser")
    public ResponseEntity<UserDTO> newUser(@RequestBody @Validated User user){
        if(user != null) {
        return new ResponseEntity<UserDTO>(service.newUser(user), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/users/{idUser}")
    public ResponseEntity<UserDTO> buscarUserPorId(@RequestHeader("idUser") Long idUser){
        UserDTO userIn = service.getUserById(idUser);
        if (userIn!=null){
            return new ResponseEntity<>(userIn, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // US 0001 - Follow um determinado vendedor
    //TODO: Verificar porque vendedor seguir vendedor esta com erro
    @PutMapping("/users/{idUser}/follow/{userIdToFollow}")
    public ResponseEntity<FollowdSellerDTO> followUser(@RequestHeader @Validated Long idUser, @RequestHeader @Validated Long userIdToFollow){
        FollowdSellerDTO followdSellerDTO = service.follow(idUser, userIdToFollow);
        if (followdSellerDTO!=null){
            return new ResponseEntity<FollowdSellerDTO>(followdSellerDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<FollowdSellerDTO>(HttpStatus.BAD_REQUEST);
        }
    }

    // US 0007 - Ser capaz de realizar a ação de “Deixar de seguir” (parar de seguir) um determinado vendedor.
    //TODO: Verificar porque vendedor seguir vendedor esta com erro
    @PutMapping("/users/{idUser}/unfollow/{userIdToUnfollow}")
    public ResponseEntity<User> unfollowUser(@RequestHeader @Validated Long idUser, @RequestHeader @Validated Long userIdToUnfollow){
        Optional<User> userIn = Optional.ofNullable(service.unfollow(idUser, userIdToUnfollow));
        if (!userIn.isPresent()){
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<User>(userIn.get(), HttpStatus.OK);
        }
    }

    //  US 0004: Obter uma lista de todos os vendedores que um determinado usuário segue (quem estou seguindo?)
    //TODO: Adicionar exceptions
    @GetMapping("/users/{idUser}/followers/list")
    public List<Seller> listFollowers(@RequestHeader("idUser") @Validated Long idUser){
        List<Seller> sellers = service.listFollowers(idUser);
        return sellers;
    }

    //  US 0006 - Obter uma lista das publicações feitas pelos vendedores que um usuário
    //TODO: Adicionar exceptions
    @GetMapping("/products/followed/{idUser}/list")
    public List<Post> postListOfSellersThatUserFollows(@RequestHeader("idUser") @Validated Long idUser){
        List<Post> listPost = service.getPostListOfSellersThatUserFollows(idUser);
        return listPost;
    }

    // Get
    // localhost:4200/users/{userId}/followers/list?order=name_asc
    // /users/{UserID}/followers/list?order=name_desc
    // /users/{UserID}/followed/list?order=name_asc
    // /users/{UserID}/followed/list?order=name_desc
    // US 0008: Ordem alfabética crescente e decrescente

}
