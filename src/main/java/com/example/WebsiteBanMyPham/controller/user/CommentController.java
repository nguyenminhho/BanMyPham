package com.example.WebsiteBanMyPham.controller.user;

import com.example.WebsiteBanMyPham.Entity.*;
import com.example.WebsiteBanMyPham.repository.CommentRepository;
import com.example.WebsiteBanMyPham.service.BlogService;
import com.example.WebsiteBanMyPham.service.CommentService;
import com.example.WebsiteBanMyPham.service.ProductService;
import com.example.WebsiteBanMyPham.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class CommentController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;

    //comment-product
    @PostMapping("/comment-add")
    public String add_comment(Model model,
                              @ModelAttribute("comment") Comment comment,
                              @ModelAttribute("productId") Long productId,
                              HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            Product product = productService.findById(productId);
            List<Comment> userComment = user.getComments();
            List<Comment> productComment = product.getComments();

            comment.setProduct_comment(product);
            comment.setUser_comment(user);
            // Thêm địa chỉ mới vào danh sách

            productComment.add(comment);
            userComment.add(comment);
            // Cập nhật danh sách địa chỉ vào đối tượng người dùng
            product.setComments(productComment);
            user.setComments(userComment);
            this.commentService.create(comment);
        }
        String referer = request.getHeader("referer");
        // Chuyển hướng đến URL trang trước đó
        return "redirect:" + referer;
    }

//delete-comment-product
    @PostMapping("/comment-delete")
    public String deleteComment(Model model,
                              @ModelAttribute("commentId") Long commentId,
                              HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            System.out.println(commentId);
            Comment comment = this.commentService.findById(commentId);
            for (Comment comment1 : user.getComments()){
                if(comment != null && user.getComments().contains(comment) && comment1.getId() == commentId){
                    this.commentService.delete(commentId);
                }
            }
        }
            String referer = request.getHeader("referer");
            // Chuyển hướng đến URL trang trước đó
            return "redirect:" + referer;
    }

    //edit-comment-product
    @PostMapping("/comment-edit")
    public String editComment(Model model,
                              @ModelAttribute("commentId") Long commentId,
                              @ModelAttribute("commentContent") String commentContent,
                                HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
           Comment comment = this.commentService.findById(commentId);
           comment.setContent(commentContent);
          this.commentService.update(comment);

        }
        String referer = request.getHeader("referer");
        // Chuyển hướng đến URL trang trước đó
        return "redirect:" + referer;
    }

    @PostMapping("/commentBlog-add")
    public String add_commentBlog(Model model,
                              @ModelAttribute("comment") Comment comment,
                              @ModelAttribute("blogId") Long blogId,
                              HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            Blog blog = blogService.findById(blogId);

            List<Comment> userComment = user.getComments();
            List<Comment> blogComment = blog.getComments();

            comment.setBlog_comment(blog);
            comment.setUser_comment(user);
            // Thêm địa chỉ mới vào danh sách
            blogComment.add(comment);
            userComment.add(comment);
            // Cập nhật danh sách địa chỉ vào đối tượng người dùng
            blog.setComments(blogComment);
            user.setComments(userComment);
            this.commentService.create(comment);
        }
        String referer = request.getHeader("referer");
        // Chuyển hướng đến URL trang trước đó
        return "redirect:" + referer;
    }

    //delete-comment-product
    @PostMapping("/commentBlog-delete")
    public String deleteCommentBlog(Model model,
                                @ModelAttribute("commentId") Long commentId,
                                HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            Comment comment = this.commentService.findById(commentId);
            for (Comment comment1 : user.getComments()){
                if(comment != null && user.getComments().contains(comment) && comment1.getId() == commentId){
                    this.commentService.delete(commentId);
                }
            }
        }
        String referer = request.getHeader("referer");
        // Chuyển hướng đến URL trang trước đó
        return "redirect:" + referer;
    }

    @PostMapping("/commentBlog-edit")
    public String editCommentBlog(Model model,
                              @ModelAttribute("commentId") Long commentId,
                              @ModelAttribute("commentContent") String commentContent,
                              HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            Comment comment = this.commentService.findById(commentId);
            comment.setContent(commentContent);
            this.commentService.update(comment);
        }
        String referer = request.getHeader("referer");
        // Chuyển hướng đến URL trang trước đó
        return "redirect:" + referer;
    }
}
