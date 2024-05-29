package com.example.WebsiteBanMyPham.service;

import ch.qos.logback.core.model.Model;
import com.example.WebsiteBanMyPham.Entity.Roles;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.Entity.UserRoles;
import com.example.WebsiteBanMyPham.repository.RolesRepository;
import com.example.WebsiteBanMyPham.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;

    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public Boolean create(User user) {
        try {
             this.userRepository.save(user);
             return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getUserLogin() {
        UserDetails userDetails = (UserDetails) httpSession.getAttribute("userDetails");
            String username = userDetails.getUsername();
            User user = this.findByUserName(username);
       return user;
    }


//    @Override
//    public void saveUser(User user) {
//
//        user.setUserName(user.getUserName());
//        user.setEmail(user.getEmail());
//        // encrypt the password using spring security
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        Roles role = rolesRepository.findByPermissions("USER");
//        Set<UserRoles> roles = user.getUserRoles() ;
//
//        for (UserRoles userRoles : roles) {
//
//            grantedAuthorityHashSet.add(new SimpleGrantedAuthority(userRoles.getRole_userRole().getPermissions()));
//        }
//        user.setUserRoles.(Arrays.asList(role));
//        userRepository.save(user);
//    }
}

