package com.digitalhouse.demo.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "SELLER")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId", scope = Long.class)
public class Seller extends User{

    @ManyToMany(mappedBy = "followed")
    @JsonIdentityReference(alwaysAsId = true)
    private List<User> followers;

    public Seller() {};

    public Seller(List<User> followers) {
        this.followers = followers;
    }

    public Seller(Integer userId, String userName, List<Seller> followed, Boolean isSeller, List<User> followers) {
        super(userId, userName, followed, isSeller);
        this.followers = followers;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }
}
