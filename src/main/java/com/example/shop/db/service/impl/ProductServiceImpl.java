package com.example.shop.db.service.impl;

import com.example.shop.db.repository.ProductRepository;
import com.example.shop.db.service.api.ProductService;
import com.example.shop.domian.Product;
import com.example.shop.db.service.api.request.SaleProductRequest;
import com.example.shop.db.service.api.request.UpdateProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getProducts() {
        return productRepository.getAll();
    }

    @Override
    public Product get(int id) {
        return productRepository.get(id);
    }

    @Override
    public Integer add(Product product) {
        return productRepository.add(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }

    @Override
    public void update(int id, UpdateProductRequest request) {
        productRepository.update(id, request);
    }

    @Override
    public void updateAvailableInternal(int id, int newAvailable) {
        productRepository.updateAvailable(id, newAvailable);
    }

    @Override
    public List<Product> getAllProducts(int id) {
        return productRepository.getAllProducts(id);
    }

    @Override
    public void sale(int id, SaleProductRequest request) {
       productRepository.sale(id, request);
    }

}
