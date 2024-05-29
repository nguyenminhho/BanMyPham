package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "longtext")
    private String content;
    private String image;
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt; // Trường thời gian tạo đơn hàng

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_blog;

    @OneToMany(mappedBy = "blog_comment", fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy = "blog_favorite", fetch = FetchType.EAGER)
    private List<Favorite> favorites;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }


    public Blog() {
    }

    public Blog(Long id, String title, String content, String image, Date createdAt, User user_blog, List<Comment> comments, List<Favorite> favorites) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
        this.user_blog = user_blog;
        this.comments = comments;
        this.favorites = favorites;
    }
}
