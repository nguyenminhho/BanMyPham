package com.example.WebsiteBanMyPham.controller.user;

import com.example.WebsiteBanMyPham.Entity.*;
import com.example.WebsiteBanMyPham.repository.RolesRepository;
import com.example.WebsiteBanMyPham.repository.UserRepository;
import com.example.WebsiteBanMyPham.service.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Controller
@RequestMapping("")
public class UserController {
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private FavoriteService favoriteService;

    @GetMapping("/")
    public String ggg(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo, HttpServletRequest request){

        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
        }
        List<Blog> blogList = this.blogService.getAll();
        model.addAttribute("blogList",blogList);
        Page<Product> productList = this.productService.getAll(pageNo);
        model.addAttribute("listProduct",productList);
        model.addAttribute("totalPage",productList.getTotalPages());
        model.addAttribute("currentPage",pageNo);


        if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
            return "user/view :: product-grid";
        }
        return "user/view";
    }


//    @GetMapping("/products")
//    public String getProducts(Model model, @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo) {
//        Page<Product> productList = productService.getAll(pageNo);
//        model.addAttribute("listProduct", productList);
//        model.addAttribute("totalPage", productList.getTotalPages());
//        model.addAttribute("currentPage", pageNo);
//        return "user/product-fragment :: product-grid";
//    }
    @GetMapping("/search")
    public String search(Model model, @Param("keyword") String keyword){
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            if (keyword != null){
                List<Product> productList = this.productService.searchProduct(keyword);
                model.addAttribute("productList",productList);
                model.addAttribute("keyword",keyword);

            }
        }
        return "user/searchProduct";
    }


    @GetMapping("/logon")
    public String lo(){
        return "user/logon";
    }
    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "user/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        if (userService.findByUserName(user.getUserName()) != null) {
            return "redirect:/register?error";
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        Roles role = rolesRepository.findByPermissions("USER");
//        Set<UserRoles> userRolesRole = role.getUserRoles();
//        Set<UserRoles> userRolesUser = user.getUserRoles();
        UserRoles userRole = new UserRoles();
        userRole.setRole_userRole(role);
        userRole.setUser_userRole(user);

        // Add the UserRoles to the user
        user.getUserRoles().add(userRole);

        // Save the user (which will also save the UserRoles)
         this.userService.create(user);


        return "redirect:/logon";
    }

    @GetMapping("/product-detail/{id}")
    public String detail(Model model, @PathVariable("id") Long id) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
        }
        List<User> userList = this.userService.getAll();
        model.addAttribute("userList", userList);
//        for(User user : userList){
//            for(Comment comment : user.getComments()) {
//                System.out.println(comment.getContent());
//            }
//        }
//        List<Comment> commentList = this.commentService.getAll();
//        model.addAttribute("commentList", commentList);
        Comment comment  = new Comment();
        model.addAttribute("comment", comment);
        Product product= this.productService.findById(id);
        model.addAttribute("product", product);

            return "user/detailProduct";
    }

    @GetMapping("/blog-detail/{id}")
    public String detailBlog(Model model, @PathVariable("id") Long id) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        Blog blog = this.blogService.findById(id);
        model.addAttribute("blog",blog);
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            Integer countFavorite = this.favoriteService.CountFavorite(blog);
            model.addAttribute("countFavorite", countFavorite);
            Favorite favorite1 = favoriteService.findByUsernameAndBlog(username, blog);
            if (favorite1 != null) {
                model.addAttribute("favoriteStatus", favorite1.getStatus());
            } else {
                model.addAttribute("favoriteStatus", 0); // Trạng thái mặc định nếu không có yêu thích
            }
        }
        List<User> userList = this.userService.getAll();
        model.addAttribute("userList", userList);
        Favorite favorite  = new Favorite();
        model.addAttribute("favorite", favorite);

        Comment comment  = new Comment();
        model.addAttribute("comment", comment);

//        System.out.println(blog.getTitle());

        return "user/detailBlog";
    }

    @GetMapping("/user")
    public String detailUser(Model model) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
        } else {
            return "redirect:/logon";
        }
        return "detailUser/userInf";
    }

    @GetMapping("/user/detail-infoUser")
    public String detailViewUser(Model model) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
        } else {
            return "redirect:/logon";
        }

        return "detailUser/profile/profile";
    }

    @PostMapping("/editUser")
    public String editUser(Model model, @ModelAttribute("user") User user1
                          ) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            user1.setUserName(user.getUserName());
            user1.setPassword(user.getPassword());
            this.userService.create(user1);

        } else {
            return "redirect:/logon";
        }


        return "detailUser/profile/profile";
    }

    @GetMapping("/user/purchase")
    public String purchase(Model model) {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
        if (userDetails != null) {
            String username = userDetails.getUsername();
            User user = userService.findByUserName(username);
            model.addAttribute("user", user);
            List<Order> orderList = this.orderService.getAll();
            model.addAttribute("orderList",orderList);
//            for (Order order: orderList){
//                if (order.getOrderStatus() != null && order.getOrderStatus() == 1){
//
//                    for(CartItem cartItem: order.getUser_order().getCartItems()){
//                        System.out.println();
//                    }
//                }
//            }

        } else {
            return "redirect:/logon";
        }

        return "detailUser/profile/purchase";
    }


}
