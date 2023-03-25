package com.example.shop.controller;

import com.example.shop.db.service.api.ShoppingService;
import com.example.shop.db.service.api.request.BuyProductRequest;
import com.example.shop.db.service.api.response.BuyProductResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shop")
public class ShoppingController {

    private final ShoppingService shoppingServices;

    public ShoppingController(ShoppingService shoppingServices) {
        this.shoppingServices = shoppingServices;
    }

    @PostMapping("/buy")
    public ResponseEntity buy(@RequestBody BuyProductRequest request){
        BuyProductResponse buyProductResponse = shoppingServices.buyProduct(request);
        if (buyProductResponse.isSuccess()){
            return ResponseEntity.ok().build();
        }else {
            return new ResponseEntity<>(buyProductResponse.getErrorMessage(), HttpStatus.PRECONDITION_FAILED);
        }
    }
}
