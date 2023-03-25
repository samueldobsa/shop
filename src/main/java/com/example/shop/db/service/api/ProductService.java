package com.example.shop.db.service.api;

import com.example.shop.db.service.api.request.SaleProductRequest;
import com.example.shop.db.service.api.request.UpdateProductRequest;
import com.example.shop.domian.Product;
import org.springframework.lang.Nullable;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();

    @Nullable
    Product get(int id);

    @Nullable
    Integer add(Product product); //vracia vygenerovane id

    void delete(int id);

    void update(int id, UpdateProductRequest request);

    void updateAvailableInternal(int id, int newAvailable);

    List<Product> getAllProducts(int id);

    void sale(int id, SaleProductRequest request);

}
