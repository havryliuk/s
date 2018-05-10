package com.havryliuk.store.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.havryliuk.store.dao.rowmapper.ProductQuantityRowMapper;
import com.havryliuk.store.entity.CartEntry;

public class CartDao implements GenericStoreDao<CartEntry> {
    private static final String QUANTITY = "quantity";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public CartDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductQuantity> findAllByCustomerId(int id) {
        String query = "SELECT * FROM cart where customer_id = ? ORDER BY product_id";
        try {
            return jdbcTemplate.query(query, new ProductQuantityRowMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    public boolean recordForProductAndCustomerExists(int customerId, int productId) {
        String query = "SELECT * FROM cart where product_id = ? AND customer_id = ?";
        return !jdbcTemplate.queryForList(query, productId, customerId).isEmpty();
    }

    @Override
    public List<CartEntry> findAll() {
        return new ArrayList<>();
    }

    @Override
    public int save(CartEntry cartEntry) {
        String query = "INSERT INTO cart (quantity, customer_id, product_id) VALUES(?, ?, ?)";
        return jdbcTemplate.update(query, cartEntry.getQuantity(), cartEntry.getCustomer().getId(), cartEntry
                .getProduct().getId());
    }

    @Override
    public CartEntry find(int id) {
        return null;
    }

    @Override
    public boolean update(CartEntry cartEntry) {
        int customerId = cartEntry.getCustomer().getId();
        int productId = cartEntry.getProduct().getId();
        int quantity = cartEntry.getQuantity();
        String query = "UPDATE cart SET quantity = ? WHERE customer_id = ? AND product_id = ?";
        int result = jdbcTemplate.update(query, quantity, customerId, productId);
        return result > 0;
    }

    public Map<String, Integer> findByProductId(int id) {
        String query = "SELECT * FROM cart where product_id = ?";
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(query, id);

        Map<String, Integer> cartEntryMap = new HashMap<>();
        for (Map<String, Object> row : rows) {
            int customerId = (int) row.get("customer_id");
            int productId = (int) row.get("product_id");
            int quantity = (int) row.get("quantity");
            cartEntryMap.put("customerId", customerId);
            cartEntryMap.put("productId", productId);
            cartEntryMap.put(QUANTITY, quantity);
        }
        return cartEntryMap;
    }
}
