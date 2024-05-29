package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer orderStatus = 0;
//    private Integer paymentStatus;
//    private Integer paymentMethod;
    private Integer totalOrder;
    private Integer totalAty;
    private  String note;
    private  String address;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_Id")
    private Payment payment;

    @OneToMany(mappedBy = "order_orderItem", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderItem> orderItem;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_order;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // Trường thời gian tạo đơn hàng

    @PrePersist
    protected void onCreate() {
        createdAt = new Date(); // Thiết lập thời gian tạo khi đơn hàng được tạo mới
    }

    public Order() {
    }

    public Order(Long id, Integer orderStatus, Integer totalOrder, Integer totalAty, String note, Payment payment, List<OrderItem> orderItem, User user_order, Date createdAt) {
        this.id = id;
        this.orderStatus = orderStatus;
        this.totalOrder = totalOrder;
        this.totalAty = totalAty;
        this.note = note;
        this.payment = payment;
        this.orderItem = orderItem;
        this.user_order = user_order;
        this.createdAt = createdAt;
    }
}
