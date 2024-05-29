package com.example.WebsiteBanMyPham.controller.admin;

import com.example.WebsiteBanMyPham.Entity.CartItem;
import com.example.WebsiteBanMyPham.Entity.Category;
import com.example.WebsiteBanMyPham.Entity.Product;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private UserService userService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartItemService cartItemService;
    @RequestMapping("/product")
    public String viewProduct(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        User user = this.userService.getUserLogin();
        model.addAttribute("user", user);
        Page<Product> productList = this.productService.getAll(pageNo);
        model.addAttribute("listProduct",productList);
        model.addAttribute("totalPage",productList.getTotalPages());
        model.addAttribute("currentPage",pageNo);
        return "product/index";
    }

    @GetMapping("/product-add")
    public String add(Model model){
        User user = this.userService.getUserLogin();
        model.addAttribute("user", user);
        Product product = new Product();
        model.addAttribute("product", product);
        List<Category> list = this.categoryService.getAll();
        model.addAttribute("list", list);
        return "product/add";
    }

    @PostMapping("/product-add")
    public String save(@ModelAttribute("product") Product product, @RequestParam("file")MultipartFile file) {
     this.storageService.store(file);
       String fileName= file.getOriginalFilename();

        product.setImage(fileName);
        if(this.productService.create(product)) {
            return "redirect:/admin/product";
        } else {
            return "product/add";
        }
    }

    @GetMapping("/product-edit/{id}")
    public String edit(Model model, @PathVariable("id") Long id){
        User user = this.userService.getUserLogin();
        model.addAttribute("user", user);
        Product product= this.productService.findById(id);
        model.addAttribute("product", product);
        List<Category> list = this.categoryService.getAll();
        model.addAttribute("list", list);
        return "product/edit";
    }

    @PostMapping("/edit-product")
    public String update(@ModelAttribute("product") Product product, @RequestParam("file")MultipartFile file) {

        String fileName= file.getOriginalFilename();
        boolean isEmpty= fileName == null || fileName.trim().length() ==0;
        if(!isEmpty){
            this.storageService.store(file);
            product.setImage(fileName);
        }
        if(this.productService.update(product)) {
            return "redirect:/admin/product";
        } else {
            return "product/add";
        }
    }
    @GetMapping("/product-delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Product product = this.productService.findById(id);
        if(this.productService.delete(id)) {
            return "redirect:/admin/product";
        } else {
            return "redirect:/admin/product";
        }
    }


}
