package br.com.challengespring.socialmeli.challengespringsocialmeli.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "SELLER_FOLLOWERS", joinColumns = {@JoinColumn(name = "SELLERS_ID_USER")}, inverseJoinColumns = {@JoinColumn(name = "FOLLOWERS_ID_USER")})
    @JsonIgnoreProperties("followed")
    private List<User> followers;

    @NotNull
    private Long followersCount = Long.valueOf(0);

    @OneToMany(mappedBy="seller", cascade = CascadeType.ALL)
    private List<Post> post;

}
