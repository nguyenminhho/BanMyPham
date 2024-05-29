package com.example.WebsiteBanMyPham.controller.user;

import com.example.WebsiteBanMyPham.Entity.*;
import com.example.WebsiteBanMyPham.service.CartItemService;
import com.example.WebsiteBanMyPham.service.ProductService;
import com.example.WebsiteBanMyPham.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class ShoppingCartController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/view")
    public String viewCart(Model model) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
        } else {
            return "redirect:/logon";
        }

        List<CartItem> listCartItem = this.cartItemService.findByUsername(userDetails.getUsername());
        model.addAttribute("listCartItem", listCartItem);
        Integer countCartItem = this.cartItemService.countCartItem(userDetails.getUsername());
        model.addAttribute("countCartItem", countCartItem);
        Integer totalPriceCartItem = this.cartItemService.totalPriceCartItem(userDetails.getUsername());
        model.addAttribute("totalPriceCartItem", totalPriceCartItem);
        Integer anty = this.cartItemService.totalAtyCartItem(userDetails.getUsername());
        System.out.println(anty);
        return "cart/shoppingCart";
    }
//    @GetMapping("/add")
//    public String view(Model model){
//
//        return "redirect:/cart/view";
//    }

    @GetMapping("/add/{id}")
    public String addCCart(
//            Model model, @ModelAttribute("cartItem") CartItem cartItem,
            @PathVariable("id") Long id) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            // Lấy sản phẩm từ ID
            Product product = productService.findById(id);
            if (product != null && user != null) {
                // Tạo một đối tượng CartItem mới
                CartItem cartItem = new CartItem();
                cartItem.setProduct_cartItem(product);
                cartItem.setUser_cartItem(user); // Gán người dùng vào CartItem
                cartItem.setTotalPrice(cartItem.getTotalPrice());
                // Lưu CartItem vào cơ sở dữ liệu
                cartItemService.create(cartItem);

            }
        }
        return "redirect:/product-detail/{id}";
    }
    @GetMapping("/addRedirect/{id}")
    public String buyRedirectCart(
//            Model model, @ModelAttribute("cartItem") CartItem cartItem,
            @PathVariable("id") Long id) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);

            // Lấy sản phẩm từ ID
            Product product = productService.findById(id);
            if (product != null && user != null) {
                // Tạo một đối tượng CartItem mới
                CartItem cartItem = new CartItem();
                cartItem.setProduct_cartItem(product);
                cartItem.setUser_cartItem(user); // Gán người dùng vào CartItem
                cartItem.setTotalPrice(cartItem.getTotalPrice());
                // Lưu CartItem vào cơ sở dữ liệu
                cartItemService.create(cartItem);

            }
        }
        return "redirect:/cart/view";
    }

    @GetMapping("/update")
    public String updateCart(
            @RequestParam("id") Long id,
            @RequestParam("aty") Integer aty,
            Model model
    ) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
        }

        // Tìm kiếm mục trong giỏ hàng dựa trên cartItemId
        CartItem cartItemToUpdate = cartItemService.findById(id);

        if (cartItemToUpdate != null) {
            // Cập nhật số lượng mới cho cartItem cụ thể
            cartItemToUpdate.setAty(aty);

            // Tính toán lại giá trị total price dựa trên số lượng mới
            cartItemToUpdate.setTotalPrice(cartItemToUpdate.getProduct_cartItem().getPrice() * aty);

            // Lưu cập nhật vào cơ sở dữ liệu
            cartItemService.update(cartItemToUpdate);



        }
        return "redirect:/cart/view";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, Model mo) {

        CartItem cartItem = this.cartItemService.findById(id);
        if(this.cartItemService.delete(id)) {
            return "redirect:/cart/view";
        } else {
            return "redirect:/cart/view";
        }
    }

    @GetMapping("/deleteAll")
    public String deleteAll() {
        if(this.cartItemService.deleteAll()) {
            return "redirect:/cart/view";
        } else {
            return "redirect:/cart/view";
        }
    }


}
