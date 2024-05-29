package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Blog;
import com.example.WebsiteBanMyPham.Entity.Favorite;
import com.example.WebsiteBanMyPham.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService implements IFavoriteService{
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Override
    public Boolean save(Favorite favorite) {
        this.favoriteRepository.save(favorite);
        return true;
    }

    @Override
    public Favorite findByUsernameAndBlog(String username, Blog blog) {
        return this.favoriteRepository.findByUsernameAndBlog(username, blog);
    }

    @Override
    public Integer CountFavorite( Blog blog) {
        return this.favoriteRepository.CountFavorite(blog);
    }


}
