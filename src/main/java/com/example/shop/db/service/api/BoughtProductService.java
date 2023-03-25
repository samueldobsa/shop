package com.example.shop.db.service.api;

import com.example.shop.domian.BoughtProduct;

import java.util.List;

public interface BoughtProductService {

    void add(BoughtProduct boughtProduct);

    List<BoughtProduct> getAllByCustomerId(int customerId);

}
