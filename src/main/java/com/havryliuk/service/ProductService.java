package com.havryliuk.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.havryliuk.entity.Product;
import com.havryliuk.dao.ProductDao;

public class ProductService {
    private static final Logger LOG = Logger.getLogger(ProductService.class);
    private static ProductService instance;
    private ProductDao productDao;

    private ProductService() {}

    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    public int addProduct(Product product) {
        int id = productDao.save(product);
        LOG.info("Added product: " + product);
        return id;
    }

    public Optional<Product> getProductById(int id) {
        Optional<Product> product;
        product = productDao.find(id);
        LOG.info("Found product: " + product);
        return product;
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public boolean updateProduct(Product product) {
        boolean result = productDao.update(product);
        LOG.info("Product updated: " + product);
        return result;
    }

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}
