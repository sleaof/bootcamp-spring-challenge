package br.com.challengespring.socialmeli.challengespringsocialmeli.service;

import br.com.challengespring.socialmeli.challengespringsocialmeli.dto.SellerDTO;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Seller;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.User;
import br.com.challengespring.socialmeli.challengespringsocialmeli.exceptions.ErroListarUsuariosExceptions;
import br.com.challengespring.socialmeli.challengespringsocialmeli.exceptions.IdNaoPodeSerNuloException;
import br.com.challengespring.socialmeli.challengespringsocialmeli.exceptions.RegistroNaoEncontradoException;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.SellerRepository;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    UserRepository repository;

    @Autowired
    SellerRepository vendorRepository;

    //TODO: Adicionar exceptions
    public SellerDTO newSeller(Seller seller){
        seller.setIsSeller(true);
        repository.save(seller);
        return new SellerDTO( seller.getIdUser(), seller.getUserName(), seller.getIsSeller(), seller.getFollowersCount());
    }

    //TODO: Tratar caso o usuario pesquisado nao seja um vendedor e se tiver erro na execucao
    public User getSeller(Long user) {
        Optional<Seller> seller = vendorRepository.findById(user);
        return seller.get();
    }

    //TODO: Adicionar exceptions
    public SellerDTO countFollow(Long idSeller){
        validSellerIsPresent(idSeller);
        Optional<Seller> seller = vendorRepository.findById(idSeller);
        return new SellerDTO( seller.get().getIdUser(), seller.get().getUserName(), seller.get().getIsSeller(), seller.get().getFollowersCount());
    }

    //  US 0003 - Obter uma lista de todos os usuários que seguem um determinado vendedor (quem me segue?)
    //TODO: Definir como retornar quando a lista de usuarios seguindo estiver vazia
    public List<User> userListFollowingSeller (Long idSeller){
        Seller seller = validSellerIsPresent(idSeller);
        if (seller.getFollowers().isEmpty()){
            throw new ErroListarUsuariosExceptions("Nao existem usuarios para listar");
        }
        return seller.getFollowers();
    }

    private Seller validSellerIsPresent(Long idSeller) {
        validIdNotNull(idSeller);
        Optional<Seller> seller = vendorRepository.findById(idSeller);
        if (seller.isPresent()){
            return seller.get();
        }
        throw new RegistroNaoEncontradoException("Registro de vendedor nao existe ou nao foi encontrado.");
    }

    private Boolean validIdNotNull(Long idUser){
        if (Objects.isNull(idUser)){
            throw new IdNaoPodeSerNuloException("Campo Id não pode estar vazio");
        }
        return true;
    }

}
