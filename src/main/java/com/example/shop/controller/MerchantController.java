package com.example.shop.controller;

import com.example.shop.db.service.api.MerchantService;
import com.example.shop.db.service.api.ProductService;
import com.example.shop.db.service.api.request.UpdateMerchantRequest;
import com.example.shop.domian.Merchant;
import com.example.shop.domian.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("merchant")
public class MerchantController {

    private final MerchantService merchantService;
    private final ProductService productService;


    public MerchantController(MerchantService merchantService, ProductService productService) {
        this.merchantService = merchantService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Merchant merchant){
        Integer id = merchantService.add(merchant);
        if (id != null){
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable(value = "id") int merchantId){
        Merchant merchant = merchantService.get(merchantId);
        if (merchant != null){
            return new ResponseEntity<>(merchant, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Merchant> merchantList = merchantService.getMerchants();
        return new ResponseEntity<>(merchantList, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity getAllProductsByMerchant(@PathVariable(value = "id") int id){
        List<Product> productsByMerchantId = productService.getAllProducts(id);
        return new ResponseEntity<>(productsByMerchantId,HttpStatus.OK);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable(value = "id") int id, @RequestBody UpdateMerchantRequest request){
        if (merchantService.get(id) != null){
            merchantService.update(id, request);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Merchant with this ID does not exist");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable(value = "id") int id){
        if (merchantService.get(id) != null){
            merchantService.delete(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Merchant with this id does not exist");
        }
    }


}
