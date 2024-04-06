package com.example.WebsiteBanMyPham.model;

import com.example.WebsiteBanMyPham.Entity.Order;
import com.example.WebsiteBanMyPham.Entity.Product;
import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;

    private Integer count;//so luong san pham

    private Integer size;


}
