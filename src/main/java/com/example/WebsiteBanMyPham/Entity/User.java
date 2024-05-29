package com.example.WebsiteBanMyPham.Entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(name = "username")
    private String userName;

    private String email;

    private String password;



    private Integer phone;

    @OneToMany(mappedBy = "user_userRole", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRoles> userRoles = new HashSet<>();
//    @OneToMany(mappedBy ="user_userRole", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<UserRoles> userRoles;

    @OneToMany(mappedBy ="user_cartItem",fetch = FetchType.EAGER)
    private Set<CartItem> cartItems;

    @OneToMany(mappedBy ="user_favorite",fetch = FetchType.EAGER)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy ="user_comment", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy ="user_order",fetch = FetchType.EAGER)
    private List<Order> orders;

    @OneToMany(mappedBy ="user_address",fetch = FetchType.EAGER)
    private Set<Address> address;

    @OneToMany(mappedBy ="user_blog",fetch = FetchType.EAGER)
    private Set<Blog> blogs;

    public User(){

    }

    public User(Long id, String fullName, String userName, String email, String password, Integer phone, Set<UserRoles> userRoles, Set<CartItem> cartItems, List<Favorite> favorites, List<Comment> comments, List<Order> orders, Set<Address> address, Set<Blog> blogs) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userRoles = userRoles;
        this.cartItems = cartItems;
        this.favorites = favorites;
        this.comments = comments;
        this.orders = orders;
        this.address = address;
        this.blogs = blogs;
    }
}
