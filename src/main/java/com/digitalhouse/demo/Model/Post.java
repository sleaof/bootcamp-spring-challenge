package com.digitalhouse.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Post implements Serializable {


    private int userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_post;
    private LocalDateTime date;

    @ManyToMany
    @JoinTable
    private List<Detail> detail = new ArrayList<>();
    private Integer category;

    @JsonIgnore
    @ManyToMany(mappedBy = "posts")
    private List<Seller> seller;

    public Post(int userId, int id_post, LocalDateTime date, List<Detail> detail, Integer category, List<Seller> seller) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.seller = seller;
    }

    public Post() {

    }

    public List<Seller> getSeller() {
        return seller;
    }

    public void setSeller(List<Seller> seller) {
        this.seller = seller;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId_post() {
        return id_post;
    }

    public void setId_post(int id_post) {
        this.id_post = id_post;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public List<Detail> getDetail() {
        return detail;
    }

    public void setDetail(List<Detail> detail) {
        this.detail = detail;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return getUserId() == post.getUserId() && getId_post() == post.getId_post() && getDate().equals(post.getDate()) && getDetail().equals(post.getDetail()) && getCategory().equals(post.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getId_post(), getDate(), getDetail(), getCategory());
    }
}
