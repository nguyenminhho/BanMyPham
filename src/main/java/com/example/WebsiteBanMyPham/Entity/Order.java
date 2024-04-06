package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private Long total;
    private Integer orderStatus;
    private Integer paymentStatus;
    private Integer paymentMethod;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "note_id")
    private Note note;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_Id")
    private Payment payment;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_order;

    @OneToMany(mappedBy = "order_orderItem", fetch = FetchType.EAGER)
    private List<OrderItem> orderItems = new ArrayList<>();


}
