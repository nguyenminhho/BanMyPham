package com.example.WebsiteBanMyPham.model;

import lombok.Data;

@Data
public class OrderDTO {
    private Long id;
    private String address;
    private Long total;
    private Integer orderStatus;
    /*
     * 0 : cho xac nhan
     * 1 : da xac nhan
     * 2 : that bai
     * 3 : thanh cong
     */
//    private Integer deliveryStatus;
//    /*
//     * 0 : cho xac nhan
//     * 1 : dang lay hang
//     * 2 : dang giao hang
//     * 3 : giao hang thanh cong
//     * 4 : giao hang that bai
//     */
   private Integer paymentStatus;
//    /*
//     * 0 : chua thanh toan
//     * 1 : da thanh toan
//     */
    private Integer paymentMethod;
//    /*
//     * 0 : COD
//     * 1 : Paypal
//     */




}
