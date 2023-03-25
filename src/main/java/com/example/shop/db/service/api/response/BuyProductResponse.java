package com.example.shop.db.service.api.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
@EqualsAndHashCode
public class BuyProductResponse {
    private boolean success;
    @Nullable
    private String errorMessage;

    public BuyProductResponse(boolean success){
        this.success = success;
    }

    public BuyProductResponse(boolean success, String errorMessage){
        this.success = success;
        this.errorMessage = errorMessage;
    }
}
