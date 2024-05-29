package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Address;
import com.example.WebsiteBanMyPham.Entity.Order;
import com.example.WebsiteBanMyPham.Entity.User;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public interface IOrderService {

    List<Order> getAll();
    Boolean create(Order order);

    Order findById(Long id);
    Order findLatestOrderByUser(User user);
}
