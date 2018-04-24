package com.havryliuk.store.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Nonnull;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.havryliuk.store.dao.ProductQuantity;

public class ProductQuantityRowMapper implements RowMapper<ProductQuantity> {
    @Nullable
    @Override
    public ProductQuantity mapRow(@Nonnull ResultSet resultSet, int i) throws SQLException {
        Integer productId = resultSet.getInt("product_id");
        Integer quantity = resultSet.getInt("quantity");
        return new ProductQuantity(productId, quantity);
    }
}
