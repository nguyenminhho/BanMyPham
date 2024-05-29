package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "CartItem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalPrice;
    private int aty = 1;
    private Integer status =1;

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_cartItem;

    @OneToMany(mappedBy = "cartItem_orderItem",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItem;

    @ManyToOne()
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product_cartItem;




//    @Column(name = "created_at", nullable = false)
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;
//
//    @PrePersist
//    protected void onCreate() {
//        createdAt = new Date();
//    }



    public CartItem() {
    }

    public CartItem(Long id, double totalPrice, int aty, Integer status, User user_cartItem, List<OrderItem> orderItem, Product product_cartItem) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.aty = aty;
        this.status = status;
        this.user_cartItem = user_cartItem;
        this.orderItem = orderItem;
        this.product_cartItem = product_cartItem;

    }

    public double getTotalPrice() {
        return totalPrice = (aty * this.product_cartItem.getPrice());
    }

    public void setAty(int aty) {
        this.aty = aty;
        this.totalPrice = aty * this.product_cartItem.getPrice();
    }

}

