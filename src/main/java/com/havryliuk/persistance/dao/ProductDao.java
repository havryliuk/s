package com.havryliuk.persistance.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.havryliuk.entity.Product;
import com.havryliuk.entity.ProductCategory;

public class ProductDao implements GenericStoreDao<Product> {
    private Connection connection;

    ProductDao(Connection connection) {
        this.connection = connection;
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM PRODUCT ORDER BY ID");
            while(rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                ProductCategory category = ProductCategory.valueOf(rs.getString("category").trim());
                Product product = new Product(id, description, price, category);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public int save(Product object) {
        return 0;
    }

    @Override
    public boolean delete(Product object) {
        return false;
    }

    @Override
    public Product find(int id) {
        return null;
    }

    @Override
    public boolean update(Product object) {
        return false;
    }
}
