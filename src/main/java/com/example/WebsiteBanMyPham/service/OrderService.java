package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Order;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }

    @Override
    public Boolean create(Order order) {
        try {
            this.orderRepository.save(order);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;

    }


    @Override
    public Order findById(Long id) {
        return this.orderRepository.findById(id).get();
    }

    @Override
    public Order findLatestOrderByUser(User user) {
        List<Order> orders = orderRepository.findOrdersByUserOrderByCreatedAtDesc(user);

        // Kiểm tra xem có đơn hàng nào không
        if (!orders.isEmpty()) {
            return orders.get(0); // Trả về đơn hàng đầu tiên trong danh sách (đơn hàng mới nhất)
        } else {
            return null; // Trả về null nếu không tìm thấy đơn hàng
        }
    }

}
