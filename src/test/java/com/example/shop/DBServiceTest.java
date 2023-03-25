package com.example.shop;

import com.example.shop.db.service.api.CustomerService;
import com.example.shop.db.service.api.MerchantService;
import com.example.shop.db.service.api.ProductService;
import com.example.shop.db.service.api.request.UpdateMerchantRequest;
import com.example.shop.db.service.api.request.UpdateProductRequest;
import com.example.shop.domian.Customer;
import com.example.shop.domian.Merchant;
import com.example.shop.domian.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@RestController
public class DBServiceTest {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private ProductService productService;

    private Merchant merchant;

    @Before
    public void createMerchant(){
        if (merchant == null){
        merchant = new Merchant("Samuel", "merchantemail", "Bure", "frajeri", "4585", "majsteri");
        Integer id = merchantService.add(merchant);
        assert id != null;
        merchant.setId(id);
        }
    }

    @Test
    public void customer(){
        Customer customer = new Customer("Samko", "Dobsa", "email@fdmf.sk", "Bure", 18, "452155");
        Integer id = customerService.add(customer);
        assert id != null;
        customer.setId(id);


        Customer fromDB = customerService.get(id);
        Assert.assertEquals(customer, fromDB);
        System.out.println("toto je konkretny customer z DB " + fromDB);


        List<Customer> customers = customerService.getCustomers();
        System.out.println("toto su vsetci customeri " + customers);

        System.out.println("toto je novy customer " + id );



    }

    @Test
    public void merchant(){
        Merchant merchant = new Merchant("Samuel", "merchantemail", "Bure", "merchant frajer", "123456789", "majsterko");
        Integer id = merchantService.add(merchant);
        assert id != null;
        merchant.setId(id);

        Merchant fromDB = merchantService.get(merchant.getId());
        Assert.assertEquals(merchant, fromDB);
        System.out.println("toto je konkretny merchant z DB " + fromDB);


        List<Merchant> merchants = merchantService.getMerchants();
        System.out.println("toto su vsetci merchanti " + merchants);

        List<Product> products = productService.getAllProducts(1);
        System.out.println("toto je merchant 1 " + merchant.getId() + " a jeho produkty " + products);

        System.out.println("toto je merchant " + id );

        merchant.setName("Maros");
        UpdateMerchantRequest request = new UpdateMerchantRequest(merchant.getName(), merchant.getEmail(), merchant.getAddress(), merchant.getCompanyInfo(), merchant.getPhoneNumber(), merchant.getDescription());
        System.out.println("toto je Update merchanta" + request);
    }

    @Test
    public void product(){
        Product product = new Product(merchant.getId(), "lopta", "pekna", 19.99, 5);
        Integer id = productService.add(product);
        assert id != null;
        product.setId(id);

        Product fromDB = productService.get(5);
        System.out.println("jeden konkretny produkt " + fromDB);


        List<Product> products = productService.getProducts();
        System.out.println("vsetky produkty " + products);

        product.setAvailable(10);
        UpdateProductRequest productRequest = new UpdateProductRequest(product.getName(), product.getDescription(), product.getPrice(), product.getAvailable());
        System.out.println("toto je updateRequest product " + productRequest);

        productService.update(id, productRequest);

        Product updateDb = productService.get(id);

        if (updateDb.getAvailable() == productRequest.getAvailable()){
            System.out.println("Available sa rovna");
        }else {
            System.out.println("Available sa nerovna " + updateDb);
        }




        productService.delete(12);
        if (productService.get(15) == null){
            System.out.println("produkt je uz vymazany");
        }

        System.out.println("novy product " + id);
    }


}
