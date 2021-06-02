package br.com.challengespring.socialmeli.challengespringsocialmeli.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seller extends User {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long idSeller;

    @OneToMany
    private List<User> followers;

    @NotNull
    private Long followersCount;

    @OneToMany
    private List<Post> post;

}
