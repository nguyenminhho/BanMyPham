package com.example.WebsiteBanMyPham.controller.user;

import com.example.WebsiteBanMyPham.Entity.*;
import com.example.WebsiteBanMyPham.service.AddressService;
import com.example.WebsiteBanMyPham.service.OrderService;
import com.example.WebsiteBanMyPham.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("")
public class AddressController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/address-add")
    public String add_address(Model model, @ModelAttribute("addressOne") Address address) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            Set<Address> userAddresses = user.getAddress();
            // Thiết lập quan hệ giữa địa chỉ và người dùng
            address.setUser_address(user);
            // Thêm địa chỉ mới vào danh sách
            userAddresses.add(address);
            // Cập nhật danh sách địa chỉ vào đối tượng người dùng
            user.setAddress(userAddresses);
            this.addressService.create(address);
        }
        return "redirect:/checkout";
        }

    @PostMapping("/address-edit")
    public String edit_address(Model model
                            ,@ModelAttribute("addressId") Long addressId
                            ,@ModelAttribute("addressContent") String addressContent){
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);

            System.out.println(addressContent);
            Address address = this.addressService.findById(addressId);
            address.setAddress(addressContent);
            this.addressService.create(address);
        }
        return "redirect:/checkout";
    }

    @GetMapping("/user/located-user")
    public String located(Model model){
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
             Set<Address> addressList = user.getAddress();
//            model.addAttribute("addressList",addressList);
            for(Address address: addressList){
//                System.out.println(address.getAddress()); System.out.println(address.getId());
                model.addAttribute("address1", address);
            }
        }else {
            return "redirect:/logon";
        }

        return "/detailUser/profile/address";
    }


    @PostMapping("/editLocated")
    public String editLocated(Model model, @ModelAttribute("address1") Address address1) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            address1.setUser_address(user);
            this.addressService.create(address1);

        } else {
            return "redirect:/logon";
        }


        return "detailUser/profile/address";
    }



//    @PostMapping("/note-add")
//    public String add_note(Model model, @ModelAttribute("note") Note note) {
//        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
//        if (userDetails != null) {
//            String username = userDetails.getUsername();
//            User user = userService.findByUserName(username);
//            System.out.println(note.getDescription());
//            // Lấy đơn hàng hiện tại từ session hoặc từ cơ sở dữ liệu (phụ thuộc vào cách bạn lưu đơn hàng)
//            Order currentOrder = orderService.findLatestOrderByUser(user);
//            System.out.println(currentOrder.getId());
//            // Thiết lập lưu ý cho đơn hàng
//            currentOrder.setNote(note);
//
//            // Lưu thay đổi vào cơ sở dữ liệu
//            orderService.create(currentOrder);
//
//            // In ra lưu ý để kiểm tra
//            System.out.println(note.getDescription());
//        }
//        return "redirect:/checkout"; // Chuyển hướng người dùng đến trang "checkout"
//    }

}

