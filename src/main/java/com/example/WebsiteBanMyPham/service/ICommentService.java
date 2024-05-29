package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.CartItem;
import com.example.WebsiteBanMyPham.Entity.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> getAll();
    Boolean create(Comment comment);
    Comment findById(Long id);
    Boolean update(Comment comment);
//     Integer deleteCm(Long commentId);
    Boolean delete(Long id);
}
