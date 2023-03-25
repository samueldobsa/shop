package com.example.shop.controller;

import com.example.shop.db.service.api.ProductService;
import com.example.shop.db.service.api.request.SaleProductRequest;
import com.example.shop.db.service.api.request.UpdateProductRequest;
import com.example.shop.domian.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product){
        Integer id = productService.add(product);
        if (id != null){
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED)
                .body("Oops, something is wrong with add");
    }

    @GetMapping("{id}")
    public ResponseEntity getProduct(@PathVariable(value = "id") int id){
        Product product = productService.get(id);
        if (product != null){
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Product with id " + id + "does not exist");
    }

    @GetMapping
    public ResponseEntity getAllProducts(){
        List<Product> productList = productService.getProducts();
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PatchMapping("{id}")
    public ResponseEntity updateProduct(@PathVariable(value = "id") int id, @RequestBody UpdateProductRequest request){
        if (productService.get(id) != null){
            productService.update(id, request);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Product with id: " + id + "does not exist");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable(value = "id") int id){
        if (productService.get(id) != null){
            productService.delete(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Product with id: " + id + "does not exist");
        }
    }

    @PatchMapping("/sale/{id}")
    public ResponseEntity sale(@PathVariable(value = "id") int id, @RequestBody SaleProductRequest request){
        if (productService.get(id) != null){
            productService.sale(id, request);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity
                    .status(HttpStatus.PRECONDITION_FAILED)
                    .body("Product with id: " + id + "does not exist");
        }
    }
}
