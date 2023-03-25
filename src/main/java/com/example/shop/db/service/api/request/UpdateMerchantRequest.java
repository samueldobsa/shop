package com.example.shop.db.service.api.request;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@EqualsAndHashCode
public class UpdateMerchantRequest {

    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String address;
    @Nullable
    private String companyInfo;
    @NonNull
    private String phoneNumber;
    @Nullable
    private String description;

    public UpdateMerchantRequest(@NonNull String name, @NonNull String email, @NonNull String address, @Nullable String companyInfo, @NonNull String phoneNumber, @Nullable String description) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.companyInfo = companyInfo;
        this.phoneNumber = phoneNumber;
        this.description = description;
    }
}
