package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="userRoles")

public class UserRoles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_userRole;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Roles role_userRole;

    public UserRoles() {
    }

    public UserRoles(Long id, User user_userRole, Roles role_userRole) {
        super();
        this.id = id;
        this.user_userRole = user_userRole;
        this.role_userRole = role_userRole;
    }

}

