package com.example.WebsiteBanMyPham.controller.user;

import com.example.WebsiteBanMyPham.Entity.Blog;
import com.example.WebsiteBanMyPham.Entity.Favorite;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.service.BlogService;
import com.example.WebsiteBanMyPham.service.FavoriteService;
import com.example.WebsiteBanMyPham.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
//@RequestMapping("")
//public class FavoriteController {
//
//    @PostMapping("/favorite-add")
//    public String favorite(Model model, @ModelAttribute("favorite")Favorite favorite){
//        System.out.println(favorite.getStatus());
//        return "user/detailBlog";
//    }
//}

@Controller
@RequestMapping("")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private BlogService blogService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private UserService userService;

//    @PostMapping("/favorite-add")
//    public ResponseEntity<Void> favorite(Model model, @ModelAttribute("favorite")Favorite favorite,@ModelAttribute("blogId")Long blogId)
//                                        {
//        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
//        if (userDetails != null) {
//            String username = userDetails.getUsername();
//            User user = userService.findByUserName(username);
//            model.addAttribute("user", user);
//            favorite.setUser_favorite(user);
//        }
//        Blog blog = this.blogService.findById(blogId);
//        System.out.println(blog.getTitle());
//        if(favorite.getStatus() ==0){
//            favorite.setStatus(1);
//        }
//        else {
//            favorite.setStatus(0);
//        }
//        this.favoriteService.save(favorite);
//
//        System.out.println(favorite.getStatus());
//        // Trả về một ResponseEntity mà không có nội dung
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/favorite-add")
    public String favorite(Model model, @ModelAttribute("favorite")Favorite favorite,
                           @ModelAttribute("blogId")Long blogId,
                           HttpServletRequest request )   {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        Blog blog = this.blogService.findById(blogId);
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);

            Favorite existingFavorite = favoriteService.findByUsernameAndBlog(username, blog);
            if (existingFavorite == null) {
                favorite.setUser_favorite(user);
                List<Favorite> favoriteList = user.getFavorites();
                favoriteList.add(favorite);
                user.setFavorites(favoriteList);

                favorite.setBlog_favorite(blog);
                List<Favorite> favoriteList1 = blog.getFavorites();
                favoriteList1.add(favorite);
                blog.setFavorites(favoriteList1);
                favorite.setStatus(1); // Đặt trạng thái mặc định là 1
            } else {
                // Nếu đã tồn tại, cập nhật trạng thái của Favorite
                favorite = existingFavorite; // Sử dụng Favorite đã tồn tại
                favorite.setStatus(favorite.getStatus() == 0 ? 1 : 0); // Đảo ngược trạng thái
            }

            favoriteService.save(favorite);
        }

        // Trả về một ResponseEntity mà không có nội dung
        String referer = request.getHeader("referer");
        // Chuyển hướng đến URL trang trước đó
        return "redirect:" + referer;
    }
}
