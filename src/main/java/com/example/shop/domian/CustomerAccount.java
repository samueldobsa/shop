package com.example.shop.domian;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Setter
@Getter
@EqualsAndHashCode
public class CustomerAccount {

    private int customerId;
    private double money;

    public CustomerAccount(int customerId, double money) {
        this.customerId = customerId;
        this.money = money;
    }

    public CustomerAccount() {
    }


}
