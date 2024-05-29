package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Blog;
import com.example.WebsiteBanMyPham.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService implements IBlogService{

    @Autowired
    private BlogRepository blogRepository;
    @Override
    public List<Blog> getAll() {
        return this.blogRepository.findAll();
    }

    @Override
    public Boolean create(Blog blog) {
        try {
            this.blogRepository.save(blog);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Blog findById(Long id) {
        return this.blogRepository.findById(id).get();
    }

    @Override
    public Boolean update(Blog blog) {
        try {
            this.blogRepository.save(blog);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean delete(Long id) {
        try {
            this.blogRepository.delete(findById(id));
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
