package com.example.shop.domian;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
public class BoughtProduct {

    private int customerId;
    private int productId;
    private int quantity;
    private Timestamp boughtAt;

    public BoughtProduct(){
    }

    public BoughtProduct(int customerId, int productId, int quantity){
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.boughtAt = Timestamp.from(Instant.now());
    }
}
