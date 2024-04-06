package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private Boolean categoryStatus;

    @OneToMany(mappedBy = "category_product")
    private Set<Product> products;

}
