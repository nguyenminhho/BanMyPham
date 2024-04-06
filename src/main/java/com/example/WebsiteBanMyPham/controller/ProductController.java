package com.example.WebsiteBanMyPham.controller;

import com.example.WebsiteBanMyPham.Entity.Category;
import com.example.WebsiteBanMyPham.Entity.Product;
import com.example.WebsiteBanMyPham.service.CategoryService;
import com.example.WebsiteBanMyPham.service.ProductService;
import com.example.WebsiteBanMyPham.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/product")
    public String viewProduct(Model model){
        List<Product> productList = this.productService.getAll();
        model.addAttribute("listProduct",productList);
        return "product/index";
    }

    @GetMapping("/product-add")
    public String add(Model model){
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
