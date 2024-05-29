package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.CartItem;
import com.example.WebsiteBanMyPham.Entity.Comment;
import com.example.WebsiteBanMyPham.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {


//    @Modifying
//    @Query("DELETE FROM Comment c WHERE c.id = :commentId")
//    Integer deleteCm(@Param("commentId") Long commentId);
}
