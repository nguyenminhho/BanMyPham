package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Boolean create(Product product);
    Product findById(Long id);
    Boolean update(Product product);
    Boolean delete(Long id);
}
