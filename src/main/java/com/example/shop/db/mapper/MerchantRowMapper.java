package com.example.shop.db.mapper;

import com.example.shop.domian.Merchant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MerchantRowMapper implements RowMapper<Merchant> {
    @Override
    public Merchant mapRow(ResultSet rs, int rowNum) throws SQLException {
        Merchant merchant = new Merchant();
        merchant.setId(rs.getInt("id"));
        merchant.setName(rs.getString("name"));
        merchant.setEmail(rs.getString("email"));
        merchant.setAddress(rs.getString("address"));
        merchant.setCompanyInfo(rs.getString("description"));
        merchant.setPhoneNumber(rs.getString("phone_number"));
        merchant.setDescription(rs.getString("description"));

        return merchant;
    }
}
