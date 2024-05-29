package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.CartItem;

import com.example.WebsiteBanMyPham.Entity.Category;
import com.example.WebsiteBanMyPham.Entity.Product;
import com.example.WebsiteBanMyPham.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT c FROM CartItem c WHERE c.user_cartItem.userName = :username")
    List<CartItem> findByUsername(@Param("username") String username);

    @Query("SELECT c FROM CartItem c WHERE c.user_cartItem.userName = :username AND c.product_cartItem = :product")
    List<CartItem> findByUsernameAndProduct(@Param("username") String username, @Param("product") Product product);

    @Query("SELECT count(c.id) FROM CartItem c WHERE c.user_cartItem.userName = :username AND c.status = 1" )
    Integer countCartItem(@Param("username") String username);

    @Query("SELECT Sum(totalPrice) FROM CartItem c WHERE c.user_cartItem.userName = :username AND c.status = 1" )
    Integer totalPriceCartItem(@Param("username") String username);

    @Query("SELECT Sum(aty) FROM CartItem c WHERE c.user_cartItem.userName = :username AND c.status = 1" )
    Integer totalAtyCartItem(@Param("username") String username);

//    GROUP BY Users.user_id, Users.username;
}
