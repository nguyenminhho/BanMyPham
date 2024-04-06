package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.User;

import java.util.Optional;

public interface IUserService {
    User findByUserName(String userName);



}
