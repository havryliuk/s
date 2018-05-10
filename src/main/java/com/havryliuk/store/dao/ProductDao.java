package com.havryliuk.store.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.havryliuk.store.dao.rowmapper.ProductRowMapper;
import com.havryliuk.store.entity.Product;

public class ProductDao implements GenericStoreDao<Product> {
    private static final Logger LOG = Logger.getLogger(ProductDao.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findAll() {
        String query = "SELECT * FROM PRODUCT ORDER BY ID";
        return jdbcTemplate.query(query, new ProductRowMapper());
    }

    @Override
    public int save(Product product) {
        String query = "INSERT INTO PRODUCT (description, price, category) VALUES (?, ?, ?)";
        final PreparedStatementCreator preparedStatementCreator = connection -> {
            final PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getDescription());
            preparedStatement.setBigDecimal(2, product.getPrice());
            preparedStatement.setString(3, product.getCategory().name());
            return preparedStatement;
        };
        final GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(preparedStatementCreator, holder);
        LOG.info("Product created: " + product.toString());
        return Integer.parseInt(holder.getKeys().get("id").toString());
    }

    @Override
    public Product find(int id) {
        String query = "SELECT * FROM PRODUCT WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new ProductRowMapper(), id);
    }

    @Override
    public boolean update(Product product) {
        int count;
        count = jdbcTemplate.update("UPDATE PRODUCT SET description = ?, price = ?, category = ? WHERE id = ?",
                product.getDescription(),
                product.getPrice(),
                product.getCategory().toString(),
                product.getId());
        return count > 0;
    }
}
