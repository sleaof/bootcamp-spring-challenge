package com.digitalhouse.demo.Model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Detail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int product_id;
    private String productName;
    private String type;
    private String brand;
    private String color;
    private String notes;
    private Double price;
    @ManyToMany(mappedBy = "detail",cascade = CascadeType.ALL)
    private List<Post> post;


    public Detail(int product_id, String productName, String type, String brand, String color, String notes, Double price) {
        this.product_id = product_id;
        this.productName = productName;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.notes = notes;
        this.price = price;
    }

    public Detail() {

    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
        Detail detail = (Detail) o;
        return product_id == detail.product_id && productName.equals(detail.productName) && type.equals(detail.type) && brand.equals(detail.brand) && color.equals(detail.color) && notes.equals(detail.notes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_id, productName, type, brand, color, notes);
    }
}
