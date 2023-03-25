package com.example.shop.controller;

import com.example.shop.db.service.api.CustomerAccountService;
import com.example.shop.db.service.api.CustomerService;
import com.example.shop.domian.CustomerAccount;
import com.example.shop.domian.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    private final CustomerService customerService;

    private final CustomerAccountService customerAccountService;

    public CustomerController(CustomerService customerService, CustomerAccountService customerAccountService) {
        this.customerService = customerService;
        this.customerAccountService = customerAccountService;
    }

    @PostMapping
    public ResponseEntity add(@RequestBody Customer customer){
       Integer id = customerService.add(customer);
       if (id != null){
           return new ResponseEntity<>(id, HttpStatus.CREATED);
       }
       return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("{id}")
    public ResponseEntity get(@PathVariable(value = "id") int mark){
        Customer customer = customerService.get(mark);
        if (customer != null){
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Customer> customersList = customerService.getCustomers();
        return new ResponseEntity<>(customersList, HttpStatus.OK);
    }

    @PostMapping("/account")
    public ResponseEntity addAccount(@RequestBody CustomerAccount customerAccount){
        customerAccountService.addCustomerAccount(customerAccount);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


}
