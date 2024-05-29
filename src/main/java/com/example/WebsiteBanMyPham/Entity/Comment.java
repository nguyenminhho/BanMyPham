package com.example.WebsiteBanMyPham.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "comment")

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product_comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blog_id", referencedColumnName = "id")
    private Blog blog_comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_comment;

    public String getTimeAgo() {
        long diffInMillis = new Date().getTime() - createdAt.getTime();
        long diffInMinutes = diffInMillis / (1000 * 60);
        if (diffInMinutes < 60) {
            return diffInMinutes + " minutes ago";
        } else if (diffInMinutes < 1440) {
            return (diffInMinutes / 60) + " hours ago";
        } else {
            return (diffInMinutes / 1440) + " days ago";
        }
    }
}
