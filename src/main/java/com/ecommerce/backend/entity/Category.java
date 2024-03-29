package com.ecommerce.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="category", schema = "ecommerce")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id")
    private long id;

    @Column(name="code")
    private String code;

    @Column(name="title")
    private String title;

    @Column(name="image")
    private String image;

    @Column(name="rating")
    private double rating;

    @Column(name="gender")
    private String gender;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Product> products;

    //bidirectional
    public void addProduct(Product product) {
        if (products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
        if (product.getCategory() == null) {
            product.setCategory(this);
        }
    }
}
