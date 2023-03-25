package com.example.shop.db.service.api;

import com.example.shop.db.service.api.request.BuyProductRequest;
import com.example.shop.db.service.api.response.BuyProductResponse;

public interface ShoppingService {

    BuyProductResponse buyProduct(BuyProductRequest request);
}
