package com.example.shop.db.service.impl;

import com.example.shop.db.repository.MerchantRepository;
import com.example.shop.db.repository.ProductRepository;
import com.example.shop.db.service.api.MerchantService;
import com.example.shop.domian.Merchant;
import com.example.shop.db.service.api.request.UpdateMerchantRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MerchantServiceImpl implements MerchantService {

    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;

    public MerchantServiceImpl(MerchantRepository merchantRepository, ProductRepository productRepository) {
        this.merchantRepository = merchantRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Merchant> getMerchants() {
        return merchantRepository.getAll();
    }

    @Override
    public Merchant get(int id) {
        return merchantRepository.get(id);
    }

    @Override
    public Integer add(Merchant merchant) {
        return merchantRepository.add(merchant);
    }

    @Override
    public void update(int id, UpdateMerchantRequest request) {
        merchantRepository.update(id, request);
    }

    @Override
    public void delete(int id) {
        merchantRepository.delete(id);
    }


}
