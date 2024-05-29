package com.example.WebsiteBanMyPham.service;

import com.example.WebsiteBanMyPham.Entity.Category;
import com.example.WebsiteBanMyPham.Entity.Product;
import com.example.WebsiteBanMyPham.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Boolean create(Product product) {
        try {
            this.productRepository.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Product findById(Long id) {
        return this.productRepository.findById(id).get();
    }

    @Override
    public Boolean update(Product product) {
        try {
            this.productRepository.save(product);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean delete(Long id) {

        try {
            this.productRepository.delete(findById(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Page<Product> getAll(Integer pageNo) {
        Pageable pageable = PageRequest.of(pageNo -1,4);
        return this.productRepository.findAll(pageable);
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return this.productRepository.searchProduct(keyword);
    }

//    @Override
//    public Page<Product> paginationProduct(String keyword, Integer pageNo) {
//        List list = this.productRepository.searchProduct(keyword);
//        Pageable pageable = PageRequest.of(pageNo -1, 4);
//        Integer start = (int) pageable.getOffset();
//        Integer end = (int) ((pageable.getOffset() + pageable.getPageSize()) > list.size() ? list.size() :pageable.getOffset() +pageable.getPageSize());
//        list= list.subList(start,end);
//        return new PageImpl<Product>(list,pageable,this.productRepository.searchProduct(keyword).size());
//    }
}
