package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
