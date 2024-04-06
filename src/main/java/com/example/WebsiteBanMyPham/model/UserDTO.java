package com.example.WebsiteBanMyPham.model;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String fullName;


    private String username;


    private String email;

    private String password;


    private Number phone;


}
