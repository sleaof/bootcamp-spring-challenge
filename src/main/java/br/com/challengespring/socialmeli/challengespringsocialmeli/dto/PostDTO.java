package br.com.challengespring.socialmeli.challengespringsocialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class PostDTO {

    private Long seller;
    private Long idPost;
    private LocalDate date;
    private DetailDTO detail;
    private Long category;
    private Double price;

}
