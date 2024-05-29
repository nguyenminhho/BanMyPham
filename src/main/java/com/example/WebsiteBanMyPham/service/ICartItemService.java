package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.CartItem;
import com.example.WebsiteBanMyPham.Entity.Product;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface ICartItemService {
    List<CartItem> getAll();
    Boolean create(CartItem cartItem);
    CartItem findById(Long id);
    Boolean update(CartItem cartItem);
    Boolean delete(Long id);

    double getAmount();
    int getCount();

    List<CartItem> findByUsername(String username);

    List<CartItem> findByUsernameAndProduct(String username,Product product);
    Integer countCartItem(String username);
    Integer totalPriceCartItem(String username);

    Integer totalAtyCartItem(String username);


    Boolean deleteAll();
}
