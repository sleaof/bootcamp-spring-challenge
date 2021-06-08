package br.com.challengespring.socialmeli.challengespringsocialmeli.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @OneToOne
    @PrimaryKeyJoinColumn
    @JsonIgnoreProperties("detail")
    private Post idPost;

    @NotNull
    private String productName;

    @NotNull
    private String type;

    @NotNull
    private String brand;

    @NotNull
    private String color;

    @NotNull
    private String notes;

}
