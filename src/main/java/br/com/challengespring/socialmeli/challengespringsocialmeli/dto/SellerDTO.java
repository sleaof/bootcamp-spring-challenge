package br.com.challengespring.socialmeli.challengespringsocialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SellerDTO {

    private Long sellerId;
    private String sellerName;
    private Boolean isSeller;
    private Long followersCount;

}
