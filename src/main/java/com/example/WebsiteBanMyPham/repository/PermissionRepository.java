package com.example.WebsiteBanMyPham.repository;

import com.example.WebsiteBanMyPham.Entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Roles, Long> {
}