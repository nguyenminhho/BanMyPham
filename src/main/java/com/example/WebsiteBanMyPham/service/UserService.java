package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}

