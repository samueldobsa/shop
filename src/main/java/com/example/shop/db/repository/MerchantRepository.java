package com.example.shop.db.repository;

import com.example.shop.db.mapper.MerchantRowMapper;
import com.example.shop.db.mapper.ProductRowMapper;
import com.example.shop.domian.Merchant;
import com.example.shop.db.service.api.request.UpdateMerchantRequest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class MerchantRepository {

    private final JdbcTemplate jdbcTemplate;
    private final MerchantRowMapper merchantRowMapper = new MerchantRowMapper();
    private final ProductRowMapper productRowMapper = new ProductRowMapper();

    public MerchantRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Merchant get(int id){
        final String sql = "select * from merchant where merchant.id = " + id;
        try {
            return jdbcTemplate.queryForObject(sql, merchantRowMapper);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public Integer add(Merchant merchant) {
        final String sql = "insert into merchant (name, email, address, company_info, phone_number, description ) values (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                ps.setString(1, merchant.getName());
                ps.setString(2, merchant.getEmail());
                ps.setString(3, merchant.getAddress());
                ps.setString(4, merchant.getCompanyInfo());
                ps.setString(5, merchant.getPhoneNumber());
                ps.setString(6, merchant.getDescription());

                return ps;
            }
        }, keyHolder);

        if (keyHolder.getKey() != null) {
            return keyHolder.getKey().intValue();
        } else {
            return null;
        }
    }

    public List<Merchant> getAll(){
        final String sql = "select * from merchant";
        return jdbcTemplate.query(sql, merchantRowMapper);
    }

    public void update(int id, UpdateMerchantRequest request){
        final String sql = "update merchant set name = ?, email = ?, address = ?, company_info = ?, phone_number = ?, description = ? where id = ? ";
        jdbcTemplate.update(sql, request.getName(), request.getEmail(), request.getAddress(), request.getCompanyInfo(), request.getPhoneNumber(), request.getDescription(), id);
    }

    public void delete(int id){
        final String sql = "delete from merchant where id = ? ";
        jdbcTemplate.update(sql, id);
    }


}
