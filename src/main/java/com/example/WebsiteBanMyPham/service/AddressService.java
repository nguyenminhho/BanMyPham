package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Address;
import com.example.WebsiteBanMyPham.Entity.CartItem;
import com.example.WebsiteBanMyPham.Entity.Product;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.repository.AddressRepository;
import com.example.WebsiteBanMyPham.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService{
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Boolean create(Address address) {
        try {
            this.addressRepository.save(address);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Address findById(Long id) {
        return this.addressRepository.findById(id).get();
    }

    @Override
    public Boolean update(Address address) {
        try {
            this.addressRepository.save(address);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        try {
            this.addressRepository.delete(findById(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
