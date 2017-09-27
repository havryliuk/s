package com.havryliuk.persistance.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.havryliuk.entity.Customer;

public class CustomerDao implements GenericStoreDao<Customer> {
    private Connection connection;

    CustomerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM CUSTOMER")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean blocked = rs.getBoolean("blocked");
                customers.add(Customer.builder().id(id).name(name).blocked(blocked).build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public int save(Customer object) {
        return 0;
    }

    @Override
    public boolean delete(Customer object) {
        return false;
    }

    @Override
    public Customer find(int id) {
        return null;
    }

    @Override
    public boolean update(Customer object) {
        return false;
    }
}
