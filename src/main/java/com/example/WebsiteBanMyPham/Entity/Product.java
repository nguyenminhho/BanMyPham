package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private Integer price;
    private String image;
    @Column(name = "description", length = 40000)
    private String description;

    @OneToMany(mappedBy = "product_favorite", fetch = FetchType.EAGER)
    private List<Favorite> favorites= new ArrayList<>();

    @OneToMany(mappedBy = "product_comment", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category_product;

    @OneToMany(mappedBy = "product_orderItem", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();


}
