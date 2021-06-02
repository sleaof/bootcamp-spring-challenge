package br.com.challengespring.socialmeli.challengespringsocialmeli.service;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.User;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Seller;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.UserRepository;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository repository;

    @Autowired
    SellerRepository vendorRepository;

    public User newUser(User user){
        repository.save(user);
        return user;
    }

    // Post
    // localhost:4200/users/{userId}/follow/{userIdToFollow}
    //  US 0001: Ser capaz de realizar a ação de “Follow” (seguir) a um determinado vendedor
    //  Status Code 200 (tudo OK)
    //  Status Code 400 (Bad Request)
    public User follow(Long userId, Long userIdToFollow){
        Optional<User> user = repository.findById(userId);
        Optional<Seller> seller = vendorRepository.findById(userIdToFollow);
        validIds(user, seller);
        return repository.save(user.get());
    }

    private void validIds(Optional<User> user, Optional<Seller> vendor) {
        if (user.isPresent() && vendor.isPresent()) {
            user.get().getFollowed().add(vendor.get());
        }
    }

    public Optional<User> getUser(Long user) {
        return repository.findById(user);
    }

    // Get
    // localhost:4200/users/{userId}/followers/count/
    //  US 0002: Obter o resultado do número de usuários que seguem um determinado vendedor
    //  Status Code 200 (tudo OK)
    //  Status Code 400 (Bad Request)

    // Get
    // localhost:4200/users/{UserID}/followers/list
    //  US 0003: Obter uma lista de todos os usuários que seguem um determinado vendedor (quem me segue?)
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
