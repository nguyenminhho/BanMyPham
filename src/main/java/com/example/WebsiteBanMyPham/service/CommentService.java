package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Comment;
import com.example.WebsiteBanMyPham.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService implements ICommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Override
    public List<Comment> getAll() {
        return this.commentRepository.findAll();
    }

    @Override
    public Boolean create(Comment comment) {
        try {
            this.commentRepository.save(comment);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Comment findById(Long id) {
        return this.commentRepository.findById(id).get();
    }

    @Override
    public Boolean update(Comment comment) {
        try {
            this.commentRepository.save(comment);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

//    @Override
//    public Integer deleteCm(Long commentId) {
//        int deletedCount = commentRepository.deleteCm(commentId);
//        if (deletedCount > 0) {
//            // Comment đã được xóa thành công
//            return 1;
//        } else {
//            // Không có comment nào được xóa
//           return 0;
//        }
//    }


    @Override
    public Boolean delete(Long id) {

        try {
            this.commentRepository.delete(findById(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
