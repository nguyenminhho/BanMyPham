package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "orderItem")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order_orderItem;

    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "cartItem_id", referencedColumnName = "id")
    private CartItem cartItem_orderItem;

    public OrderItem() {
    }

    public OrderItem(Long id, Order order_orderItem, CartItem cartItem_orderItem) {
        this.id = id;
        this.order_orderItem = order_orderItem;
        this.cartItem_orderItem = cartItem_orderItem;
    }
}
