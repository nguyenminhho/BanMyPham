package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.Blog;
import com.example.WebsiteBanMyPham.Entity.Comment;
import com.example.WebsiteBanMyPham.Entity.Favorite;
import com.example.WebsiteBanMyPham.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("SELECT f FROM Favorite f WHERE f.user_favorite.userName = :username AND f.blog_favorite = :blog")
    Favorite findByUsernameAndBlog(String username, Blog blog);

    @Query("SELECT COUNT(f) FROM Favorite f WHERE f.blog_favorite = :blog And f.status=1")
    Integer CountFavorite(Blog blog);
}
