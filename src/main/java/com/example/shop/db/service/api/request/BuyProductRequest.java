package com.example.shop.db.service.api.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode
public class BuyProductRequest {
    private int productId;
    private int customerId;
    private int quantity;

    public BuyProductRequest(int productId, int customerId, int quantity){
        this.productId = productId;
        this.customerId = customerId;
        this.quantity = quantity;
    }
}
