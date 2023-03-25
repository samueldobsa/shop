package com.example.shop.domian;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@EqualsAndHashCode
public class Product {

    @Nullable
    private Integer id;
    @NonNull
    private int merchantId;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private double price;
    @Nullable
    private Timestamp createdAt;
    @NonNull
    private int available;

    public Product(){
    }

    public Product(int merchantId, @NonNull String name, @NonNull String description, double price, @NonNull int available) {
        this.merchantId = merchantId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = Timestamp.from(Instant.now());
        this.available = available;
    }


}
