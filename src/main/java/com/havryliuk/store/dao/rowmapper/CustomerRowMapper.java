package com.havryliuk.store.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Nonnull;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;

import com.havryliuk.store.entity.Customer;

public class CustomerRowMapper implements RowMapper<Customer> {
    @Nullable
    @Override
    public Customer mapRow(@Nonnull ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("user_id");
        String name = resultSet.getString("name");
        boolean blocked = resultSet.getBoolean("blocked");
        return Customer.builder().id(id).name(name).blocked(blocked).build();
    }
}
