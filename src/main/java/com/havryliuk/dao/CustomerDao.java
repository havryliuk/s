package com.havryliuk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.havryliuk.entity.Customer;

public class CustomerDao implements GenericStoreDao<Customer> {
    private static final Logger LOG = Logger.getLogger(CustomerDao.class);
    private Connection connection;

    CustomerDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM CUSTOMER ORDER BY USER_ID")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String name = rs.getString("name");
                boolean blocked = rs.getBoolean("blocked");
                customers.add(Customer.builder().id(id).name(name).blocked(blocked).build());
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return customers;
    }

    @Override
    public int save(Customer customer) {
        return 0;
    }

    @Override
    public boolean delete(Customer customer) {
        return false;
    }

    @Override
    public Optional<Customer> find(int id) {
        Optional<Customer> customer = Optional.empty();
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM customer WHERE user_id = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                boolean blocked = rs.getBoolean("blocked");
                return Optional.ofNullable(Customer.builder().id(id).name(name).blocked(blocked).build());
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return customer;
    }

    @Override
    public boolean update(Customer customer) {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE customer SET blocked = ? WHERE user_id = ?")) {
            statement.setBoolean(1, customer.isBlocked());
            statement.setInt(2, customer.getId());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        }
        return count > 0;
    }
}
