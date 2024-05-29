package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String permissions;//tên của quyền, b
    @OneToMany(mappedBy ="role_userRole")
    private Set<UserRoles> userRoles;

    public Roles(){

    }

    public Roles(Long id, String permissions, Set<UserRoles> userRoles) {
        super();
        this.id = id;
        this.permissions = permissions;
        this.userRoles = userRoles;
    }

}
