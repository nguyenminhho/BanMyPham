package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

@Entity
@Data
@Table(name = "note")
@NoArgsConstructor
//Bang ghi chu nguoi nhan hang
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer phone;
    private String name;
    @OneToOne(mappedBy = "note")
    private Order order;
}
