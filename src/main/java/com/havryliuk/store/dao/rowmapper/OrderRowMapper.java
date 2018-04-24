package com.havryliuk.store.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Nonnull;

import org.springframework.jdbc.core.RowMapper;

import com.havryliuk.store.entity.Order;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(@Nonnull ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        boolean paid = resultSet.getBoolean("paid");
        return Order.builder().id(id).paid(paid).build();
    }
}