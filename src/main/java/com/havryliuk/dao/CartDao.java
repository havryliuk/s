package com.havryliuk.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.havryliuk.entity.CartEntry;

public class CartDao implements GenericStoreDao<CartEntry> {
    private Connection connection;
    CartDao(Connection connection) {
        this.connection = connection;
    }

    public Map<Integer, Integer> findAllByCustomerId(int id) {
        Map<Integer, Integer> entries = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cart where customer_id=?" +
                " ORDER BY product_id")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                entries.put(productId, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public boolean recordForProductIdExists(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cart where product_id=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CartEntry> findAll() {
        return null;
    }

    @Override
    public int save(CartEntry cartEntry) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO cart (quantity, customer_id, product_id)" +
                " VALUES(?, ? , ?)")) {
            statement.setInt(1, cartEntry.getQuantity());
            statement.setInt(2, cartEntry.getCustomer().getId());
            statement.setInt(3, cartEntry.getProduct().getId());
            return statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean delete(CartEntry cartEntry) {
        return false;
    }

    @Override
    public Optional<CartEntry> find(int id) {
        return Optional.empty();
    }

    @Override
    public boolean update(CartEntry cartEntry) {
        int customerId = cartEntry.getCustomer().getId();
        int productId = cartEntry.getProduct().getId();
        int quantity = cartEntry.getQuantity();
        try (PreparedStatement statement = connection.prepareStatement("UPDATE cart SET quantity=?" +
                " WHERE customer_id=? AND product_id=?")) {
            statement.setInt(1, quantity);
            statement.setInt(2, customerId);
            statement.setInt(3, productId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<String, Integer> findByProductId(int id) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM cart where product_id=?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("customer_id");
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                Map<String, Integer> cartEntryMap = new HashMap<>();
                cartEntryMap.put("customerId", customerId);
                cartEntryMap.put("productId", productId);
                cartEntryMap.put("quantity", quantity);
                return cartEntryMap;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }
}
