package br.com.challengespring.socialmeli.challengespringsocialmeli.service;

import br.com.challengespring.socialmeli.challengespringsocialmeli.dto.DetailDTO;
import br.com.challengespring.socialmeli.challengespringsocialmeli.dto.PostDTO;
import br.com.challengespring.socialmeli.challengespringsocialmeli.dto.PostHasPromoDTO;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Post;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Seller;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.User;
import br.com.challengespring.socialmeli.challengespringsocialmeli.exceptions.*;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.PostRepository;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.SellerRepository;
import br.com.challengespring.socialmeli.challengespringsocialmeli.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    SellerRepository sellerRepository;

    public PostDTO newPost(Post post){
        try {
            postRepository.save(post);
            return new PostDTO(post.getSeller(), post.getIdPost(), post.getDate(),
                    new DetailDTO(
                        post.getDetail().getIdProduct(), post.getDetail().getProductName(),
                            post.getDetail().getType(), post.getDetail().getBrand(),
                            post.getDetail().getColor(), post.getDetail().getNotes()),
                    post.getCategory(), post.getPrice());
        } catch (ErroCriarPostException error){
            throw new ErroCriarPostException("Erro ao criar um novo post.");
        }
    }

    public PostHasPromoDTO newPostHasPromo(Post post){
        try {
            postRepository.save(post);
            return new PostHasPromoDTO(post.getSeller(), post.getIdPost(), post.getDate(),
                    new DetailDTO(
                            post.getDetail().getIdProduct(), post.getDetail().getProductName(),
                            post.getDetail().getType(), post.getDetail().getBrand(),
                            post.getDetail().getColor(), post.getDetail().getNotes()),
                    post.getCategory(), post.getPrice(), post.getHasPromo(), post.getDiscount());
        } catch (ErroCriarPostException error){
            throw new ErroCriarPostException("Erro ao criar um novo post.");
        }
    }

    public Optional<Post> getPostById(Long idPost) {
        validIdNotNull(idPost);
        Optional<Post> post = postRepository.findById(idPost);
        if(!post.isPresent()){
            throw new RegistroNaoEncontradoException("Registro de post nao existe ou nao foi encontrado.");
        }
        return post;
    }

    public Optional<List<Post>> getListProductsHasPromo(Long idUser){
        validIdNotNull(idUser);
        Optional<Seller> seller = sellerRepository.findById(idUser);
        Optional<List<Post>> postList = Optional.ofNullable(seller.get().getPost());
        return postList;
    }

    public List<Seller> getPostListOfSellersThatUserFollows (Long idUser){
        Optional<User> user = userRepository.findById(idUser);
        if (user.get().getFollowed().isEmpty()){
            throw new ErroListarUsuariosExceptions("Nao existem usuarios para listar");
        }
        return user.get().getFollowed();
    }

    //TODO: Criar o pacote de utils
    private Boolean validIdNotNull(Long idPost){
        if (Objects.isNull(idPost)){
            throw new IdNaoPodeSerNuloException("Campo Id não pode estar vazio");
        }
        return true;
    }

    // Get
    // localhost:4200/products/followed/{userId}/list
    // US 0006: Obter uma lista das publicações feitas pelos vendedores que um usuário
    // segue nas últimas duas semanas (para isso, ter em conta ordenação por data,
    // a maioria das publicações recentes primeiro).

    // Get
    // localhost:4200/users/{UserID}/followers/list?order=name_asc
    // /products/followed/{userId}/list?order=date_asc
    // /products/followed/{userId}/list?order=date_desc
    // US 0009: Classificar por data crescente e decrescente

}
