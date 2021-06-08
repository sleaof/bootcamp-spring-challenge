package br.com.challengespring.socialmeli.challengespringsocialmeli.dto;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Seller;
import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FollowdSellerDTO {

    private Long idUser;
    private String userName;
    private SellerDTO followed;

    public FollowdSellerDTO getFollowdSellerDTO(Optional<User> user, Optional<Seller> seller) {
        return new FollowdSellerDTO(user.get().getIdUser(), user.get().getUserName(),
                new SellerDTO(seller.get().getIdUser(), seller.get().getUserName(), seller.get().getIsSeller(), seller.get().getFollowersCount()));
    }

}
