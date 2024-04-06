package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("Select c from Category c where c.type like %?1%")
    List<Category> searchCategory(String keyword);
}
