package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Boolean create(Category category);
    Category findById(Long id);
    Boolean update(Category category);
    Boolean delete(Long id);
    List<Category> searchCategory(String keyword);
    Page<Category> getAll(Integer pageNo);
    Page<Category> searchCategory(String keyword, Integer pageNo);
}
