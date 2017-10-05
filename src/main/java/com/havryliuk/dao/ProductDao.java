package com.havryliuk.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.havryliuk.entity.Product;
import com.havryliuk.entity.ProductCategory;

public class ProductDao implements GenericStoreDao<Product> {
    private static final Logger LOG = Logger.getLogger(ProductDao.class);
    private Connection connection;

    ProductDao(Connection connection) {
        this.connection = connection;
    }

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT ORDER BY ID")) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                ProductCategory category = ProductCategory.valueOf(rs.getString("category").trim());
                Product product = Product.builder().id(id).description(description).price(price).category(category)
                        .build();
                products.add(product);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return products;
    }

    @Override
    public int save(Product product) {
        int id = -1;
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO PRODUCT (description, price, category) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, product.getDescription());
            statement.setBigDecimal(2, product.getPrice());
            statement.setString(3, product.getCategory().name());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
                product.setId(id);
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        return id;
    }

    @Override
    public boolean delete(Product product) {
        return false;
    }

    @Override
    public Optional<Product> find(int id) {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM PRODUCT WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                String description = rs.getString("description");
                BigDecimal price = rs.getBigDecimal("price");
                ProductCategory category = ProductCategory.valueOf(rs.getString("category").trim());
                return Optional.ofNullable(Product.builder().id(id).description(description).price(price).category
                        (category).build());
            }
        } catch (SQLException e) {
            LOG.error(e);
        }
        LOG.info("Product ID not found: " + id);
        return Optional.empty();
    }

    @Override
    public boolean update(Product product) {
        int count = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE PRODUCT SET description = ?, price = ?, category = ? WHERE id = ?")) {
            statement.setString(1, product.getDescription());
            statement.setBigDecimal(2, product.getPrice());
            statement.setString(3, product.getCategory().toString());
            statement.setInt(4, product.getId());
            count = statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e);
        }
        return count > 0;
    }
}
