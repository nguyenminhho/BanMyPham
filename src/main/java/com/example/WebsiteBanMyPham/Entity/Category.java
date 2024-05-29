package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Boolean categoryStatus;

    @OneToMany(mappedBy = "category_product", fetch = FetchType.EAGER)
    private Set<Product> products;

    public Category() {
    }

    public Category(Long id, String type, Boolean categoryStatus, Set<Product> products) {
        super();
        this.id = id;
        this.type = type;
        this.categoryStatus = categoryStatus;
        this.products = products;
    }
}
