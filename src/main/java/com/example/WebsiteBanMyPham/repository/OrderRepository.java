package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.Order;
import com.example.WebsiteBanMyPham.Entity.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o FROM Order o WHERE o.user_order = :user ORDER BY o.createdAt DESC")
    List<Order> findOrdersByUserOrderByCreatedAtDesc(User user);

}
