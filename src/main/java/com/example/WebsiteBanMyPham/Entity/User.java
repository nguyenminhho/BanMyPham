package com.example.WebsiteBanMyPham.Entity;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="user")
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(name = "username")
    private String userName;

    private String email;

    private String password;

    private Number phone;

    @OneToMany(mappedBy ="user_userRole",fetch = FetchType.EAGER
//            ,cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH}
           )
    private Set<UserRoles> userRoles;

    @OneToMany(mappedBy ="user_favorite",fetch = FetchType.EAGER)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy ="user_comment",fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy ="user_order",fetch = FetchType.EAGER)
    private List<Order> orders;


   

}
