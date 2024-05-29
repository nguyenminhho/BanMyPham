package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String trademark;
    private String skinType;
    private String shape;
    private Float weight;
    private Integer volume;
    private Integer price;
    private String image;
    @Column(columnDefinition = "longtext")
    private String description;



    @OneToMany(mappedBy = "product_comment", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "product_cartItem", fetch = FetchType.EAGER)
    private Set<CartItem> cartItems;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category_product;

    public Product() {
    }

    public Product(Long id, String name, String trademark, String skinType, String shape, Float weight, Integer volume, Integer price, String image, String description, List<Comment> comments, Set<CartItem> cartItems, Category category_product) {
        this.id = id;
        this.name = name;
        this.trademark = trademark;
        this.skinType = skinType;
        this.shape = shape;
        this.weight = weight;
        this.volume = volume;
        this.price = price;
        this.image = image;
        this.description = description;
        this.comments = comments;
        this.cartItems = cartItems;
        this.category_product = category_product;
    }
}
