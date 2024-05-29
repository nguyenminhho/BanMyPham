package com.example.WebsiteBanMyPham.controller.user;

import com.example.WebsiteBanMyPham.Entity.*;
import com.example.WebsiteBanMyPham.service.*;
import jakarta.servlet.http.HttpSession;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping()
public class OrderController {

    @Autowired
    private OrderService orderService;


    @Autowired
    private HttpSession httpSession;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemService cartItemService;
    @GetMapping("/checkout")
    public String checkOut(Model model){
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            Order order = new Order();

            Set<Address> addresses =  user.getAddress();
            if(addresses == null || addresses.isEmpty()){
                Address address = new Address();
                model.addAttribute("addressOne", address);
            }else{
                for(Address address : addresses){
                    String located = address.getAddress();
                    order.setAddress(located);
                }

            }
            List<OrderItem> orderItems = new ArrayList<>();

            List<CartItem> listCartItem = this.cartItemService.findByUsername(userDetails.getUsername());
            model.addAttribute("listCartItem", listCartItem);
            Integer countCartItem = this.cartItemService.totalAtyCartItem(userDetails.getUsername());
            model.addAttribute("countCartItem", countCartItem);
            Integer totalPriceCartItem = this.cartItemService.totalPriceCartItem(userDetails.getUsername());
            model.addAttribute("totalPriceCartItem", totalPriceCartItem);

            Integer totalAty = this.cartItemService.totalAtyCartItem(userDetails.getUsername());

            // Tạo danh sách các OrderItem từ danh sách CartItem
            for (CartItem cartItem : listCartItem) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrder_orderItem(order); // Thiết lập đơn hàng cho mỗi mục đơn hàng
                orderItem.setCartItem_orderItem(cartItem); // Thiết lập sản phẩm cho mỗi mục đơn hàng
                orderItems.add(orderItem);
            }



            order.setOrderItem(orderItems);
            order.setUser_order(user);
            order.setTotalOrder(totalPriceCartItem);
            order.setTotalAty(totalAty);
            // Lưu đối tượng Order vào cơ sở dữ liệu
            orderService.create(order);
            Order order1 = this.orderService.findLatestOrderByUser(user);
           model.addAttribute("order1",order1);
            // Xóa các CartItem sau khi chuyển thành đơn hàng
//            this.cartItemService.deleteAll();
        }
         else {
            return "redirect:/logon";
        }

        return "order/orderCart";
    }





}
