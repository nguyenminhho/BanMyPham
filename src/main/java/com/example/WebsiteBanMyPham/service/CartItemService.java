package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.CartItem;
import com.example.WebsiteBanMyPham.Entity.Category;

import com.example.WebsiteBanMyPham.Entity.Product;
import com.example.WebsiteBanMyPham.Entity.User;
import com.example.WebsiteBanMyPham.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartItemService implements ICartItemService{

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public List<CartItem> getAll() {
        return this.cartItemRepository.findAll();
    }

    @Override
    public Boolean create(CartItem cartItem) {
        Product product = cartItem.getProduct_cartItem();
        User user = cartItem.getUser_cartItem();

        // Tìm kiếm sản phẩm trong giỏ hàng của người dùng
        List<CartItem> cartItems = cartItemRepository.findByUsernameAndProduct(user.getUserName(), product);

        if (cartItems.isEmpty()) {
            cartItem.setTotalPrice(cartItem.getTotalPrice());
            // Nếu sản phẩm không tồn tại trong giỏ hàng, thêm mới
            cartItemRepository.save(cartItem);
        } else {
            for(CartItem cartItem1: cartItems){
                // Nếu sản phẩm đã tồn tại trong giỏ hàng và chưa check out, tăng số lượng lên 1
                if(cartItem1.getStatus() == 1){
                    CartItem existingCartItem = cartItems.get(0);
                    existingCartItem.setAty(existingCartItem.getAty() + 1);
                    cartItem.setTotalPrice(cartItem.getTotalPrice());
                    cartItemRepository.save(existingCartItem);
                }

                else{
                    cartItem.setTotalPrice(cartItem.getTotalPrice());
                    // Nếu sản phẩm tồn tại trong giỏ hàng và đã check out thì sẽ tạo mới sàn phẩm đó
                    cartItemRepository.save(cartItem);

                }



            }

        }

        return true;
    }

    @Override
    public CartItem findById(Long id) {
        return this.cartItemRepository.findById(id).get();
    }

    @Override
    public Boolean update(CartItem cartItem) {
        try {
            this.cartItemRepository.save(cartItem);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {
        try {
//            CartItem cartItem = this.cartItemService.findById(id);
//            cartItem.setStatus(0);
            this.cartItemRepository.delete(findById(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean deleteAll() {
        try {
            this.cartItemRepository.deleteAll();
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public double getAmount() {
        return 0;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public List<CartItem> findByUsername(String username) {
        return this.cartItemRepository.findByUsername(username);
    }

    @Override
    public List<CartItem> findByUsernameAndProduct(String username, Product product) {
        return this.cartItemRepository.findByUsernameAndProduct(username, product);
    }

    @Override
    public Integer countCartItem(String username) {
        return this.cartItemRepository.countCartItem(username);
    }

    @Override
    public Integer totalPriceCartItem(String username) {
        return this.cartItemRepository.totalPriceCartItem(username);
    }

    @Override
    public Integer totalAtyCartItem(String username) {
        return this.cartItemRepository.totalAtyCartItem(username);
    }


//    @Override
//    public Collection<CartItem> getAll() {
//        return maps.values();
//    }
//
//    @Override
//
//    public void create(CartItem item) {
//        CartItem cartItem = maps.get(item.getProduct_cartItem().getId());
//        System.out.println(cartItem);
//        if(cartItem == null){
//            maps.put(item.getId(), item);
//            this.cartItemRepository.save(item);
//        }else {
//            cartItem.setAty(cartItem.getAty() + 1);
//            this.cartItemRepository.save(cartItem);
//        }
//
//    }
//
//
//    @Override
//    public CartItem update(long id, int aty) {
//        CartItem cartItem = maps.get(id);
//        cartItem.setAty(aty);
//        return cartItem;
//    }
//
//    @Override
//    public void delete(Long id) {
//       maps.remove(id);
//    }
//
//    @Override
//    public double getAmount() {
//        return maps.values().stream().mapToDouble(item -> item.getAty() * item.getProduct_cartItem().getPrice()).sum();
//    }
//
//    @Override
//    public int getCount() {
//        return maps.values().size();
//    }
}
