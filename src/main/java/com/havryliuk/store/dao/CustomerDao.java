package com.havryliuk.store.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.havryliuk.store.dao.rowmapper.CustomerRowMapper;
import com.havryliuk.store.entity.Customer;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomerDao implements GenericStoreDao<Customer> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * FROM CUSTOMER ORDER BY USER_ID", new CustomerRowMapper());
    }

    @Override
    public int save(Customer customer) {
        return 0;
    }

    @Override
    public Customer find(int id) {
        String query = "SELECT * FROM customer WHERE user_id = ?";
        return jdbcTemplate.queryForObject(query, new CustomerRowMapper(), id);
    }

    @Override
    public boolean update(Customer customer) {
        jdbcTemplate.update("UPDATE customer SET blocked = ? WHERE user_id = ?", customer.isBlocked(), customer.getId());
        return true;
    }
}
