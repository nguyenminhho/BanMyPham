package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Blog;

import java.util.List;

public interface IBlogService {
     List<Blog> getAll();
     Boolean create(Blog blog);
    Blog findById(Long id);
    Boolean update(Blog blog);
    Boolean delete(Long id);

}
