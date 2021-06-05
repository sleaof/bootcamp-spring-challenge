package com.digitalhouse.demo.entities;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


public class Post {

    private Long id;
    private Date date;
    List<Product> details = new ArrayList<>();
    private String category;
    private Double price;


}
