package com.example.WebsiteBanMyPham.model;

import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String type;
    private Boolean categoryStatus;
}
