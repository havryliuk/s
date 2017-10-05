package com.havryliuk.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.havryliuk.entity.Product;
import com.havryliuk.dao.DaoFactory;
import com.havryliuk.dao.ProductDao;

public class ProductService {
    private static final Logger LOG = Logger.getLogger(ProductService.class);
    private static ProductService instance;

    private ProductService() {}

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public int addProduct(Product product) {
        ProductDao dao = DaoFactory.getProductDao();
        int id = dao.save(product);
        LOG.info("Added product: " + product);
        return id;
    }

    public Optional<Product> getProductById(int id) {
        Optional<Product> product;
        ProductDao dao = DaoFactory.getProductDao();
        product = dao.find(id);
        LOG.info("Found product: " + product);
        return product;
    }

    public List<Product> getAllProducts() {
        ProductDao dao = DaoFactory.getProductDao();
        return dao.findAll();
    }

    public boolean updateProduct(Product product) {
        ProductDao dao = DaoFactory.getProductDao();
        boolean result = dao.update(product);
        LOG.info("Product updated: " + product);
        return result;
    }
}
