package com.digitalhouse.demo.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Seller extends User{


    @OneToMany
    private List<User> followers = new ArrayList<>();


    public Seller(Long id, String name, boolean isSeller, List<Seller> followed, List<User> followers) {
        super(id, name, isSeller, followed);
        this.followers = followers;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Seller)) return false;
        if (!super.equals(o)) return false;
        Seller seller = (Seller) o;
        return followers.equals(seller.followers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), followers);
    }

    @Override
    public String toString() {
        return "Seller{" +
                "followers=" + followers +
                '}';
    }
}
