package com.example.WebsiteBanMyPham.service;



import com.example.WebsiteBanMyPham.Entity.Address;
import com.example.WebsiteBanMyPham.Entity.Product;

import java.util.List;

public interface IAddressService {

    Boolean create(Address address);
    Address findById(Long id);
    Boolean update(Address address);
    Boolean delete(Long id);
}
