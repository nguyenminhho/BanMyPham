package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "payment")

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double total;
    private String method;
    private String description;
    @OneToOne(mappedBy = "payment")
    private Order order;
}
