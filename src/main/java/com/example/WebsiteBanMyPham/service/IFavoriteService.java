package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Blog;
import com.example.WebsiteBanMyPham.Entity.Favorite;
import org.springframework.data.repository.query.Param;

public interface IFavoriteService {
    Boolean save(Favorite favorite);
    Favorite findByUsernameAndBlog(String username, Blog blog);

    Integer CountFavorite(Blog blog);
}

