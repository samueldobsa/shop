package com.example.shop.db.service.api;

import com.example.shop.domian.Customer;
import org.springframework.lang.Nullable;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    @Nullable
    Customer get(int id);

    @Nullable
    Integer add(Customer customer); // vrati vygenerovane id
}
