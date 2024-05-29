package com.example.WebsiteBanMyPham.controller.admin;

import com.example.WebsiteBanMyPham.Entity.Product;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;
    @GetMapping
    public String index(){
        return "redirect:/admin/";
    }

    @RequestMapping("/")
    public String view(Model model){
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            System.out.println(user.getUserName());
            model.addAttribute("user", user);
        }

        System.out.println("111");
        return "admins/index";
    }
}
