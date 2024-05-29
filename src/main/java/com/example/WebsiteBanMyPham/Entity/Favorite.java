package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "favorite")

public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer status = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog_favorite;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_favorite;

}
