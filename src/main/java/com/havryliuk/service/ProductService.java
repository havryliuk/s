package com.havryliuk.service;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.havryliuk.entity.Product;
import com.havryliuk.dao.DaoFactory;
import com.havryliuk.dao.ProductDao;

public class ProductService {
    private static final Logger LOG = Logger.getLogger(ProductService.class);

    public Optional<Product> getProductById(int id) {
        Optional<Product> product;
        ProductDao dao = new DaoFactory().getProductDao();
        product = dao.find(id);
        LOG.info("Found product ID: " + product.toString());
        return product;
    }

    public List<Product> getAllProducts() {
        ProductDao dao = new DaoFactory().getProductDao();
        return dao.findAll();
    }

    public boolean updateProduct(Product product) {
        ProductDao dao = new DaoFactory().getProductDao();
        return dao.update(product);
    }
}
