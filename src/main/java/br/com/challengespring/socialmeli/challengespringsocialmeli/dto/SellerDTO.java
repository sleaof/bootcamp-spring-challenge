package br.com.challengespring.socialmeli.challengespringsocialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerDTO {

    private Long userId;
    private String userName;
    private Boolean isSeller;
    private Long followersCount;

}
