package com.havryliuk.service;

import java.util.Optional;

import com.havryliuk.entity.Product;
import com.havryliuk.dao.DaoFactory;
import com.havryliuk.dao.ProductDao;

public class ProductService {

    public Optional<Product> getProductById(int id) {
        Optional<Product> product;
        ProductDao dao = new DaoFactory().getProductDao();
        product = dao.find(id);
        return product;
    }
}
