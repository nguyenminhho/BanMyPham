package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.Address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
