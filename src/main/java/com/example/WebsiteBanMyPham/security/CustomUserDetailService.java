package com.example.WebsiteBanMyPham.security;

import com.example.WebsiteBanMyPham.Entity.Roles;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.Entity.UserRoles;
import com.example.WebsiteBanMyPham.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService; // Use constructor injection
    public CustomUserDetailService(UserService userService) {
        this.userService = userService;
    }
    @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found"); // More informative message
        }
        Collection<GrantedAuthority> grantedAuthoritySet= new HashSet<>();
        Set<UserRoles> roles = user.getUserRoles() ;
        for (UserRoles userRoles : roles) {
            grantedAuthoritySet.add(new SimpleGrantedAuthority(userRoles.getRole_userRole().getPermissions()));
        }
        // Lưu thông tin người dùng vào session
        httpSession.setAttribute("userDetails", new CustomUserDetails(user, grantedAuthoritySet));
        return new CustomUserDetails(user, grantedAuthoritySet);
    }

}
