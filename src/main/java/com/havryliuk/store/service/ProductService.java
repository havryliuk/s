package com.havryliuk.store.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havryliuk.store.entity.Product;
import com.havryliuk.store.dao.ProductDao;

@Service
public class ProductService {
    private static final Logger LOG = Logger.getLogger(ProductService.class);
    @Autowired
    private ProductDao productDao;

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
}