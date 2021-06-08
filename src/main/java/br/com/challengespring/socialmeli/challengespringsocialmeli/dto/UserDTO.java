package br.com.challengespring.socialmeli.challengespringsocialmeli.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Long userId;
    private String userName;

}
