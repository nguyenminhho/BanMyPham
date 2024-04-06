package com.example.WebsiteBanMyPham.controller;

import com.example.WebsiteBanMyPham.Entity.Category;
import com.example.WebsiteBanMyPham.Entity.Product;
import com.example.WebsiteBanMyPham.service.CategoryService;
import com.example.WebsiteBanMyPham.service.ProductService;
import com.example.WebsiteBanMyPham.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
//@RequestMapping("")
public class UserController {
    @GetMapping("/")
    public String ggg(Model model){
        List<Product> productList = this.productService.getAll();
        model.addAttribute("listProduct",productList);
        return "user/viewuser";
    }

    @GetMapping("/logon")
    public String lo(){
        return "admins/logon";
    }

    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;


}
