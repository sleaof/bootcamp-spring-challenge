package com.digitalhouse.demo.entities;

import lombok.AllArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isSeller = false;

    @OneToMany
    private List<Seller> followed = new ArrayList<>();

    public User(){
    }

    public User(Long id, String name, boolean isSeller, List<Seller> followed) {
        this.id = id;
        this.name = name;
        this.isSeller = isSeller;
        this.followed = followed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSeller() {
        return isSeller;
    }

    public List<Seller> getFollowed() {
        return followed;
    }

    public void setFollowed(List<Seller> followed) {
        this.followed = followed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isSeller == user.isSeller && id.equals(user.id) && name.equals(user.name) && followed.equals(user.followed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isSeller, followed);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seller=" + isSeller +
                ", followed=" + followed +
                '}';
    }
}
