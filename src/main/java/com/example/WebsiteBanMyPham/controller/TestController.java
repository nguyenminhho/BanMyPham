package com.example.WebsiteBanMyPham.controller;

import com.example.WebsiteBanMyPham.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private StorageService storageService;
    @GetMapping
    public String upload(){
        return "upload";
    }

    @PostMapping()
    public String save(@RequestParam("file")MultipartFile file) {
        this.storageService.store(file);
        return "upload";
    }
}
