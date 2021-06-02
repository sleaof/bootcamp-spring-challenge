package br.com.challengespring.socialmeli.challengespringsocialmeli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

import static com.fasterxml.jackson.annotation.JsonFormat.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @JsonFormat(shape = Shape.STRING,pattern = "dd.MM.yyyy")
    private LocalDate date;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProduct", referencedColumnName = "idProduct")
    private Detail detail;

    @NotNull
    private Long category;

    @NotNull
    private Double price;

    @NotNull
    private Boolean hasPromo = false;

    private Double discount;

}
