package br.com.challengespring.socialmeli.challengespringsocialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailDTO {

    private Long idProduct;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
}
