package com.example.shop.db.service.api;

import com.example.shop.db.service.api.request.UpdateMerchantRequest;
import com.example.shop.domian.Merchant;
import org.springframework.lang.Nullable;

import java.util.List;

public interface MerchantService {

    List<Merchant> getMerchants();
    @Nullable
    Merchant get(int id);
    @Nullable
    Integer add (Merchant merchant);

    void update(int id, UpdateMerchantRequest request);

    void delete(int id);
}
