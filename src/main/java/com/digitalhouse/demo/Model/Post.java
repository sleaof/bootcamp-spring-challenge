package com.digitalhouse.demo.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_post;
    private Date date;
    private List<Detail> detail = new ArrayList<>();
    private Integer category;
    private Double price;

    public Post(int userId, int id_post, Date date, List<Detail> detail, Integer category, Double price) {
        this.userId = userId;
        this.id_post = id_post;
        this.date = date;
        this.detail = detail;
        this.category = category;
        this.price = price;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return getUserId() == post.getUserId() && getId_post() == post.getId_post() && getDate().equals(post.getDate()) && getDetail().equals(post.getDetail()) && getCategory().equals(post.getCategory()) && getPrice().equals(post.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getId_post(), getDate(), getDetail(), getCategory(), getPrice());
    }
}
