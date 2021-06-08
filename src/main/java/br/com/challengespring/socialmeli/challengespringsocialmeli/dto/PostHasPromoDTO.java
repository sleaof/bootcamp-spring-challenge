package br.com.challengespring.socialmeli.challengespringsocialmeli.dto;

import br.com.challengespring.socialmeli.challengespringsocialmeli.entity.Detail;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PostHasPromoDTO {

    private Long seller;
    private Long idPost;
    private LocalDate date;
    private DetailDTO detail;
    private Long category;
    private Double price;
    private Boolean hasPromo = true;
    private Double discount;

}
