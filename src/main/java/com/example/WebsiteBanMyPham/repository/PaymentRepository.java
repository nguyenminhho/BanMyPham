package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
