package com.digitalhouse.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Seller extends User{


    @ManyToMany(mappedBy = "followed")
    private List<User> followers = new ArrayList<>();

    @OneToMany(mappedBy = "seller")
    private List<Post> posts = new ArrayList<>();

    public Seller(){
    }

    public Seller(Long id, String name, boolean seller, List<Seller> followed, List<User> followers, List<Post> posts) {
        super(id, name, seller, followed);
        this.followers = followers;
        this.posts = posts;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
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
