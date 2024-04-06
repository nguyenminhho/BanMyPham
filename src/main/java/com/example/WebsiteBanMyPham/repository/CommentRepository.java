package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
