package com.example.shop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.shop.db.service.api.request.SaleProductRequest;
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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@AutoConfigureMockMvc
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private Merchant merchant;

    @Before
    @Test
    public void createMerchant() throws Exception{
        if (merchant == null){
            merchant = new Merchant("Jozef", "jozef@mail.com", "Bure", "dobra firma", "554545", "majstri");

            String id = mockMvc.perform(post("/merchant")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(merchant)))
                    .andExpect(status().isCreated())
                    .andReturn().getResponse().getContentAsString();
//        vlozenie id do merchanta
            merchant.setId(objectMapper.readValue(id, Integer.class));
        }
    }

    @Test
    public void product() throws Exception{
        Product product = new Product(merchant.getId(), "klavesnica", "moc pekna", 18.5, 10);

//        add product
//        pretipovanie product do Jsonu
        String id = mockMvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        product.setId(objectMapper.readValue(id, Integer.class));
        System.out.println("ID new product " + id);

//        Get one product by ID
        String productJson = mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

//        pretipovanie Jsonu na Product triedu / objekt
        Product returnedProduct = objectMapper.readValue(productJson, Product.class);
//        Assert.assertEquals(product, returnedProduct);
        System.out.println("Get product as Json - " + productJson);
        System.out.println("Get product as Object - " + returnedProduct);

        String listJson = mockMvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println("List of Json all product - " + listJson);

        List<Product> returnedList = objectMapper.readValue(listJson, new TypeReference<>() {});
        System.out.println("List of Object all products " + returnedList);
        System.out.println("Size of List products - " + returnedList.size());
        System.out.println("Size before delete product - " + returnedList.size());

//        update product
        double updatePrice = product.getPrice() + 1;
        int updatedAvailable = product.getAvailable() + 5;
        UpdateProductRequest updateProductRequest = new UpdateProductRequest("product.getName()", product.getDescription(), updatePrice, updatedAvailable);

        mockMvc.perform(patch("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateProductRequest)))
                .andExpect(status().isOk());

//        returned update product
        String returnedUpdatedProduct = mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

//        pretipovanie Jsonu na Product triedu / objekt
        Product updatedProduct = objectMapper.readValue(returnedUpdatedProduct, Product.class);
        assert updatePrice == updatedProduct.getPrice();
        assert updatedAvailable == updatedProduct.getAvailable();

        System.out.println("Update product " + returnedUpdatedProduct);

//        update price product
        double salePrice = product.getPrice();
        System.out.println("Price before salePrice " + salePrice);
        SaleProductRequest saleProductRequest = new SaleProductRequest(3.5);

        mockMvc.perform(patch("/product/sale/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saleProductRequest)))
                .andExpect(status().isOk());

        String returnedSaleProduct = mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                                .andReturn().getResponse().getContentAsString();

        Product updatePriceProduct = objectMapper.readValue(returnedSaleProduct, Product.class);
        System.out.println("Update Sale product " + returnedSaleProduct);

        double afterSalePrice = product.getPrice();
        System.out.println("Cena po update " + afterSalePrice);

//        delete product
        mockMvc.perform(delete("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform(get("/product/" + product.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        String listJson2 = mockMvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<Product> listDeleteProducts = objectMapper.readValue(listJson2, new TypeReference<>() {});

        System.out.println("List after delete product " + listDeleteProducts.size());


    }

    @Test
    public void customer() throws Exception{
        Customer customer = new Customer("Samuel", "Dobsa", "email", "address", 21, "0944");

        //Add customer
        //v tomto stringu je Json pridania jednoho customera
        String id = mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        customer.setId(objectMapper.readValue(id, Integer.class));

        //Get customer
        //v tomto stringu je Json jednoho customera
       String customerJson = mockMvc.perform(get("/customer/" + customer.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
//       pretipovanie customer Json na customer triedu / objekt
       Customer returnedCustomer = objectMapper.readValue(customerJson, Customer.class);
        Assert.assertEquals(customer, returnedCustomer);

        System.out.println(customerJson);
        System.out.println(returnedCustomer.getId());


        //Get all customer
        //v tomto stringu je Json List customerov
        String listJson = mockMvc.perform(get("/customer")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

//        pretipovanie Stringu na List customerov
        List<Customer> customers = new ObjectMapper().readValue(
                listJson, new TypeReference<>() { }
        );
//        assert readValues.size() == 63;
        System.out.println(customers);
    }

    @Test
    public void merchant() throws Exception{
//        merchant is alerady created on up code

//        Get mercahnt
//        v tomto stringu je Json getnutia merchanta podla id
        String merchantJson = mockMvc.perform(get("/merchant/" + merchant.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

//        Pretipovanie merchant Json na Merchant triedu / Objekt
        Merchant returnedMerchant = objectMapper.readValue(merchantJson, Merchant.class);
        Assert.assertEquals(merchant, returnedMerchant);

//        Updated merchant
        UpdateMerchantRequest updateMerchantRequest = new UpdateMerchantRequest("More", "nasrany", "SEM", "dufam ze", "40407", "funguje");

        mockMvc.perform(patch("/merchant/update/" + merchant.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateMerchantRequest)))
                        .andExpect(status().isOk());

        String returnedUpdateMerchant = mockMvc.perform(get("/merchant/" + merchant.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        Merchant updatedMerchant = objectMapper.readValue(returnedUpdateMerchant, Merchant.class);
        System.out.println("Updated merchant " + returnedUpdateMerchant);

//        double updatePrice = product.getPrice() + 1;
//        int updatedAvailable = product.getAvailable() + 5;
//        UpdateProductRequest updateProductRequest = new UpdateProductRequest(product.getName(), product.getDescription(), updatePrice, updatedAvailable);
//
//        mockMvc.perform(patch("/product/" + product.getId())
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(updateProductRequest)))
//                .andExpect(status().isOk());
//
////        returned update product
//        String returnedUpdatedProduct = mockMvc.perform(get("/product/" + product.getId())
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn().getResponse().getContentAsString();
//
////        pretipovanie Jsonu na Product triedu / objekt
//        Product updatedProduct = objectMapper.readValue(returnedUpdatedProduct, Product.class);
//        assert updatePrice == updatedProduct.getPrice();
//        assert updatedAvailable == updatedProduct.getAvailable();
//
//        System.out.println("Update product " + returnedUpdatedProduct);

//        Get all merchants
       String listJson = mockMvc.perform(get("/merchant")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andReturn().getResponse().getContentAsString();
//        pretipovanie Stringu na List merchantov
       List<Merchant> merchantList =  objectMapper.readValue(listJson, new TypeReference<>(){});

//       Assert.assertEquals(merchant, merchantList.get(154));
//       assert merchantList.size() == 155;
        System.out.println(merchantList);
        System.out.println(listJson);
    }


}






