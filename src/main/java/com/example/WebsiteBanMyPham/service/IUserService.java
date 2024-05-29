package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.CartItem;
import com.example.WebsiteBanMyPham.Entity.Category;
import com.example.WebsiteBanMyPham.Entity.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<User> getAll();
    User findByUserName(String userName);
    Boolean create(User user);

    User getUserLogin();

}
