package com.example.WebsiteBanMyPham.controller;

import com.example.WebsiteBanMyPham.Entity.Category;
import com.example.WebsiteBanMyPham.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.SortedSet;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/category")
    public String index(Model model, @Param("keyword") String keyword, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){

        Page<Category> categories = this.categoryService.getAll(pageNo);
        if(keyword != null){
            categories = this.categoryService.searchCategory(keyword,pageNo);
            model.addAttribute("keyword",keyword);
        }
        model.addAttribute("totalPage", categories.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("list", categories);
        return "category/indexCategory";
    }

    @GetMapping("/add-category")
    public String add(Model model){
        Category category = new Category();
        category.setCategoryStatus(true);
        model.addAttribute("category", category);
        return "category/add";
    }

    @PostMapping("/add-category")
    public String save(@ModelAttribute("category") Category category){
        if(this.categoryService.create(category)){
            return "redirect:/admin/category";
        }else {
            return "category/add";
        }

    }

    @GetMapping("/edit-category/{id}")
    public String edit(Model model,@PathVariable("id") Long id){
        Category category = this.categoryService.findById(id);
        model.addAttribute("category",category);
            return "category/edit";

    }

    @PostMapping("/edit-category")
    public String update(@ModelAttribute("category") Category category){
        if(this.categoryService.create(category)){
            return "redirect:/admin/category";
        }else {
            return "category/add";
        }

    }

    @GetMapping("/delete-category/{id}")
    public String delete(@PathVariable("id") Long id){
        Category category = this.categoryService.findById(id);
        if(this.categoryService.delete(id)){
            return "redirect:/admin/category";
        }else {
            return "redirect:/admin/category";
        }

    }

}
