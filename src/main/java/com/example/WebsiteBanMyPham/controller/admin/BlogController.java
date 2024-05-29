package com.example.WebsiteBanMyPham.controller.admin;


import com.example.WebsiteBanMyPham.Entity.Blog;
import com.example.WebsiteBanMyPham.Entity.Category;
import com.example.WebsiteBanMyPham.Entity.Product;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.service.BlogService;
import com.example.WebsiteBanMyPham.service.StorageService;
import com.example.WebsiteBanMyPham.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;

    @Autowired
    private BlogService blogService;



    @Autowired
    private StorageService storageService;
    @GetMapping("/blog")
    public String index(Model model){
        User user = this.userService.getUserLogin();
        model.addAttribute("user", user);
        List<Blog> blogList = this.blogService.getAll();
        model.addAttribute("blogList",blogList);
        return "blog/index";
    }

    @GetMapping("/blog-add")
    public String addBlog(Model model){
        User user = this.userService.getUserLogin();
        if (user == null) {
            return "redirect:/logon";
        }
        model.addAttribute("user", user);
        Blog blog = new Blog();
        model.addAttribute("blog",blog);
        return "blog/add";
    }

    @PostMapping("/blog-add")
    public String saveBlog(Model model,@ModelAttribute("blog") Blog blog, @RequestParam("file")MultipartFile file){
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            blog.setUser_blog(user);
        } else {
            return "redirect:/logon";
        }
        this.storageService.store(file);
        String fileName= file.getOriginalFilename();
        blog.setImage(fileName);
        this.blogService.create(blog);
        return "blog/index";
    }

    @GetMapping("/blog-delete/{id}")
    public String deleteBlog(Model model, @PathVariable("id") Long id){
        User user = this.userService.getUserLogin();
        if (user == null) {
            return "redirect:/logon";
        }
        model.addAttribute("user", user);
        this.blogService.delete(id);
        return "redirect:/admin/blog";
    }

    @GetMapping("/blog-edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        User user = this.userService.getUserLogin();
        if (user == null) {
            return "redirect:/logon";
        }
        model.addAttribute("user", user);
        Blog blog= this.blogService.findById(id);
        model.addAttribute("blog", blog);
        return "blog/edit";
    }

    @PostMapping("/edit-blog")
    public String update(@ModelAttribute("blog") Blog blog, @RequestParam("file")MultipartFile file) {

        String fileName= file.getOriginalFilename();
        boolean isEmpty= fileName == null || fileName.trim().length() ==0;
        if(!isEmpty){
            this.storageService.store(file);
            blog.setImage(fileName);
        }
        if(this.blogService.update(blog)){
            return "redirect:/admin/blog";
        }
         return "blog/add";

    }
}
