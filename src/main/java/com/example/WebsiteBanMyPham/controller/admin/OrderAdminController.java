package com.example.WebsiteBanMyPham.controller.admin;

import com.example.WebsiteBanMyPham.Entity.Address;
import com.example.WebsiteBanMyPham.Entity.CartItem;
import com.example.WebsiteBanMyPham.Entity.Order;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.service.OrderService;
import com.example.WebsiteBanMyPham.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class OrderAdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String viewOrder(Model model){
        User user = this.userService.getUserLogin();
        model.addAttribute("user", user);
        List<Order> orderList = this.orderService.getAll();
        model.addAttribute("orderList",orderList);
        for (Order order: orderList){
            if (order.getOrderStatus() != null && order.getOrderStatus() == 1){

                for(CartItem cartItem: order.getUser_order().getCartItems()){
                    System.out.println();
                }
           }
        }
        return "order/viewOrder";
    }
}
