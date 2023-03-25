package com.example.shop.domian;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@EqualsAndHashCode
public class Merchant {

    @Nullable
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String address;
    @Nullable
    private String companyInfo;
    @Nullable
    private String phoneNumber;
    @Nullable
    private String description;

    public Merchant(){
    }

    public Merchant(@NonNull String name, @NonNull String email, @NonNull String address, @Nullable String companyInfo, @Nullable String phoneNumber, @Nullable String description ) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.companyInfo = companyInfo;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }



}
