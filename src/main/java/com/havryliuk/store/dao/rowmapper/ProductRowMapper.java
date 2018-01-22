package com.havryliuk.store.dao.rowmapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.havryliuk.store.entity.Product;
import com.havryliuk.store.entity.ProductCategory;

public class ProductRowMapper implements RowMapper<Product> {
    @Nullable
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        String description = resultSet.getString("description");
        BigDecimal price = resultSet.getBigDecimal("price");
        ProductCategory category = ProductCategory.valueOf(resultSet.getString("category").trim());
        return Product.builder().id(id).description(description).price(price).category
                (category).build();
    }
}
