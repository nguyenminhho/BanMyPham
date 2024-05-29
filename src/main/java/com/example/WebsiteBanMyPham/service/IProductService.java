package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Category;
import com.example.WebsiteBanMyPham.Entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    Boolean create(Product product);
    Product findById(Long id);
    Boolean update(Product product);
    Boolean delete(Long id);

    Page<Product> getAll(Integer pageNo);
    List<Product> searchProduct(String keyword);
//    Page<Product> paginationProduct(String keyword, Integer pageNo);

}
