package com.example.shop.db.service.api.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class SaleProductRequest {

    private double newPrice;

    public SaleProductRequest(double newPrice) {
        this.newPrice = newPrice;
    }
}
