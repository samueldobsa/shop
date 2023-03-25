package com.example.shop;

import com.example.shop.domian.Customer;
import com.example.shop.domian.Merchant;
import com.example.shop.domian.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class DBInsertTest {

    String INSERT_CUSTOMER = "insert into customer(name, surname, email, address, age, phone_number) values (?, ?, ?, ?, ?, ?)";
    String INSERT_MERCHANT = "insert into merchant(name, email, address) values (?, ?, ?)";
    String INSERT_PRODUCT = "insert into product(merchant_id, name, description, price, created_at, available) values (?, ?, ?, ?, ?, ?)";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void createCustomer(){
                    Customer customer = new Customer();
                    customer.setName("Samuel");
                    customer.setSurname("Dobsa");
                    customer.setEmail("dobsasamuel5@gmail.com");
                    customer.setAddress("Bure Pico");
                    customer.setAge(21);
                    customer.setPhoneNumber("+421910126458");

        jdbcTemplate.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_CUSTOMER);
                ps.setString(1,customer.getName());
                ps.setString(2,customer.getSurname());
                ps.setString(3,customer.getEmail());
                ps.setString(4,customer.getAddress());
                ps.setInt(5,customer.getAge());
                ps.setString(6,customer.getPhoneNumber());
                return ps;
            }
        });
    }



    @Test
    public void createMerchant(){
        Merchant merchant = new Merchant();
        merchant.setName("Martin");
        merchant.setEmail("mechant@gmail.com");
        merchant.setAddress("Gbely");

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
               PreparedStatement ps = con.prepareStatement(INSERT_MERCHANT);
               ps.setString(1, merchant.getName());
               ps.setString(2, merchant.getEmail());
               ps.setString(3, merchant.getAddress());

                return ps;
            }
        });
    }

    @Test
    public void createProduct(){
        Product product = new Product();
        product.setMerchantId(1);
        product.setName("Klavesnica");
        product.setDescription("Krasna klavesnica");
        product.setPrice(20.99);
        product.setCreatedAt(Timestamp.from(Instant.now()));
        product.setAvailable(5);

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(INSERT_PRODUCT);
                ps.setInt(1, product.getMerchantId());
                ps.setString(2, product.getName());
                ps.setString(3, product.getDescription());
                ps.setDouble(4, product.getPrice());
                ps.setTimestamp(5, product.getCreatedAt());
                ps.setInt(6, product.getAvailable());

                return ps;
            }
        });

    }
    }

