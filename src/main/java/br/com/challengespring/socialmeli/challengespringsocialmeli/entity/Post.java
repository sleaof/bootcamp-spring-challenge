package br.com.challengespring.socialmeli.challengespringsocialmeli.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private Long seller;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPost;

    @JsonFormat(shape = Shape.STRING,pattern = "dd.MM.yyyy")
    private LocalDate date = LocalDate.now();

    @OneToOne(mappedBy = "idPost", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("idPost")
    private Detail detail;

    @NotNull
    private Long category;

    @NotNull
    private Double price;

    @NotNull
    private Boolean hasPromo = false;

    private Double discount;

}
