package com.example.WebsiteBanMyPham.model;

import lombok.Data;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String image;
}
